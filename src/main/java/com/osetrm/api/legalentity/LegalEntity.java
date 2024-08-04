package com.osetrm.api.legalentity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record LegalEntity(
        @Valid LegalEntityIdentifier legalEntityIdentifier,
        @NotEmpty(message = "{LegalEntity.legalName.required}") String legalName
        ) {
}
