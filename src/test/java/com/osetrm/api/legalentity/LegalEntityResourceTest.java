package com.osetrm.api.legalentity;

import com.osetrm.api.Role;
import com.osetrm.api.error.ErrorResponse;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class LegalEntityResourceTest {

    @Test
    @TestSecurity(user = "testUser", roles = {Role.OSETRM_LEGAL_ENTITY_READ, Role.OSETRM_LEGAL_ENTITY_WRITE})
    void updateLegalEntity() {
        LegalEntity legalEntity = createLegalEntity();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(legalEntity)
                .post("/v1/legal-entities")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();
        LegalEntity got = given()
                .when()
                .get(response.getHeader(HttpHeaders.LOCATION))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(LegalEntity.class);
        assertEquals(legalEntity, got);
        LegalEntity updated = new LegalEntity(got.legalEntityIdentifier(), RandomStringUtils.randomAlphabetic(20));
        given()
                .contentType(ContentType.JSON)
                .body(updated)
                .put("/v1/legal-entities/{legalEntityIdentifier}", updated.legalEntityIdentifier().value())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        got = given()
                .when()
                .get("/v1/legal-entities/{legalEntityIdentifier}", updated.legalEntityIdentifier().value())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(LegalEntity.class);
        assertEquals(updated, got);
    }

    @Test
    @TestSecurity(user = "testUser", roles = Role.OSETRM_LEGAL_ENTITY_WRITE)
    public void postFailNoLegalName() {
        LegalEntity legalEntity = new LegalEntity(createLegalEntityIdentifier(), null);
        ErrorResponse errorResponse = given()
                .contentType(ContentType.JSON)
                .body(legalEntity)
                .post("/v1/legal-entities")
                .then()
                .statusCode(jakarta.ws.rs.core.Response.Status.BAD_REQUEST.getStatusCode())
                .extract().as(ErrorResponse.class);
        assertNotNull(errorResponse);
        assertEquals(errorResponse.errorMessages().get(1).message(), "Legal Entity's legal name is required");
    }


    LegalEntity createLegalEntity() {
        return new LegalEntity(createLegalEntityIdentifier(), RandomStringUtils.randomAlphabetic(20));
    }

    LegalEntityIdentifier createLegalEntityIdentifier() {
        return new LegalEntityIdentifier(LegalEntityIdentifierType.LEI, "TEST" + RandomStringUtils.randomAlphabetic(16));
    }

}
