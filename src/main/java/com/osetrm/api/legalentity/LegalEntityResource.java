package com.osetrm.api.legalentity;

import com.osetrm.api.Role;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.net.URI;
import java.util.Objects;

@Path("/v1/legal-entities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "legal-entity", description = "Legal Entity Operations")
public class LegalEntityResource {

    private final LegalEntityService legalEntityService;

    public LegalEntityResource(LegalEntityService legalEntityService) {
        this.legalEntityService = legalEntityService;
    }

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Get All Legal Entities",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = LegalEntity.class)
            )
    )
    @APIResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @RolesAllowed(Role.OSETRM_LEGAL_ENTITY_READ)
    public Response get() {
        return Response.ok(legalEntityService.findAll()).build();
    }

    @GET
    @Path("/{legalEntityIdentifier}")
    @APIResponse(
            responseCode = "200",
            description = "Get Legal Entity by legalEntityIdentifier",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = LegalEntity.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Legal Entity does not exist for legalEntityIdentifier",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @RolesAllowed(Role.OSETRM_LEGAL_ENTITY_READ)
    public Response getByLegalEntityIdentifier(
            @Parameter(name = "legalEntityIdentifier", required = true)
            @PathParam("legalEntityIdentifier")
            String legalEntityIdentifier) {
        return legalEntityService.findByLegalEntityIdentifier(legalEntityIdentifier)
                .map(legalEntity -> Response.ok(legalEntity).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }


    @POST
    @APIResponse(
            responseCode = "201",
            description = "Legal Entity Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = LegalEntity.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Legal Entity",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Legal Entity already exists for legalEntityIdentifier",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @RolesAllowed(Role.OSETRM_LEGAL_ENTITY_WRITE)
    public Response post(@Valid LegalEntity legalEntity, @Context UriInfo uriInfo) {
        legalEntityService.create(legalEntity);
        URI uri = uriInfo.getAbsolutePathBuilder().path(legalEntity.legalEntityIdentifier().value()).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("/{legalEntityIdentifier}")
    @APIResponse(
            responseCode = "204",
            description = "Legal Entity updated",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = LegalEntity.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Legal Entity",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Legal Entity does not have legalEntityIdentifier",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Path variable legalEntityIdentifier does not match LegalEntity.legalEntityIdentifier.value",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "No Legal Entity found for legalEntityIdentifier provided",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @RolesAllowed(Role.OSETRM_LEGAL_ENTITY_WRITE)
    public Response put(
            @Parameter(name = "legalEntityIdentifier", required = true)
            @PathParam("legalEntityIdentifier") String legalEntityIdentifier, @Valid LegalEntity legalEntity) {
        if (!Objects.equals(legalEntityIdentifier, legalEntity.legalEntityIdentifier().value())) {
            throw new WebApplicationException("Path variable legalEntityIdentifier does not match LegalEntity.legalEntityIdentifier", Response.Status.BAD_REQUEST);
        }
        legalEntityService.update(legalEntity);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
