package com.osetrm.api.legalentity;

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

    private LegalEntityV1 random() {
        return new LegalEntityV1(
                RandomStringUtils.insecure().nextAlphanumeric(20),
                RandomStringUtils.insecure().nextAlphanumeric(20)
        );
    }

}
