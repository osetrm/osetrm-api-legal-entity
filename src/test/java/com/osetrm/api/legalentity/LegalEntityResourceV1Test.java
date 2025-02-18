package com.osetrm.api.legalentity;

import com.osetrm.api.common.ErrorMessage;
import com.osetrm.api.common.ErrorResponse;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class LegalEntityResourceV1Test {

    @Test
    @TestSecurity(user = "test", roles = {Role.LEGAL_ENTITY_WRITE})
    void createLegalEntity() {
        LegalEntityV1 legalEntity = random();
        LegalEntityV1 saved = given()
                .contentType(ContentType.JSON)
                .body(legalEntity)
                .post("/api/v1/legal-entities")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().as(LegalEntityV1.class);
        assertThat(saved).isEqualTo(legalEntity);
    }

    @Test
    @TestSecurity(user = "test", roles = {Role.LEGAL_ENTITY_WRITE})
    void failCreateLegalEntityInvalidLegalEntityIdentifier() {
        LegalEntityV1 legalEntity = new LegalEntityV1(
                RandomStringUtils.insecure().nextAlphanumeric(10),
                RandomStringUtils.insecure().nextAlphanumeric(20)
        );
        ErrorResponse errorResponse = given()
                .contentType(ContentType.JSON)
                .body(legalEntity)
                .post("/api/v1/legal-entities")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .extract().as(ErrorResponse.class);
        assertThat(errorResponse).isNotNull()
                .extracting(ErrorResponse::errorId).isNull();
        assertThat(errorResponse.errorMessages())
                .extracting(ErrorMessage::message)
                .contains("The Legal Entity's legal entity identifier (LEI) must be twenty characters");
    }

    private LegalEntityV1 random() {
        return new LegalEntityV1(
                RandomStringUtils.insecure().nextAlphanumeric(20),
                RandomStringUtils.insecure().nextAlphanumeric(20)
        );
    }

}
