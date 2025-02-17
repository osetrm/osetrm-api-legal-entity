package com.osetrm.api.legalentity;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.net.URI;

@Path("/api/v1/legal-entities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "legal-entity", description = "Legal Entity Operations")
public class LegalEntityResourceV1 {

    private LegalEntityService legalEntityService;

    public LegalEntityResourceV1(LegalEntityService legalEntityService) {
        this.legalEntityService = legalEntityService;
    }

    @POST
    @APIResponse(responseCode = "201", description = "Legal Entity Created",
            content = @Content(schema = @Schema(implementation = LegalEntityV1.class))
    )
    @APIResponse(responseCode = "400", description = "Invalid Legal Entity")
    @RolesAllowed(Role.LEGAL_ENTITY_WRITE)
    public Response post(@NotNull @Valid LegalEntityV1 legalEntityV1, @Context UriInfo uriInfo) {
        LegalEntity legalEntity = new LegalEntity(legalEntityV1.legalEntityIdentifier(), legalEntityV1.legalName());
        legalEntityService.create(legalEntity);
        URI uri = uriInfo.getAbsolutePathBuilder().path(legalEntityV1.legalEntityIdentifier()).build();
        return Response.created(uri).entity(legalEntityV1).build();
    }

}
