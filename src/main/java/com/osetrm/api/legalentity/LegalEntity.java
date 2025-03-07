package com.osetrm.api.legalentity;

import jakarta.validation.constraints.NotEmpty;

public record LegalEntity(
        @NotEmpty(message = "{LegalEntity.legalEntityIdentifier.required}") String legalEntityIdentifier,
        @NotEmpty(message = "{LegalEntity.legalName.required}") String legalName
) {
}
