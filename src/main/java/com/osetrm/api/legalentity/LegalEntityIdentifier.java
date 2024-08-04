package com.osetrm.api.legalentity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LegalEntityIdentifier(
        @NotNull(message = "{LegalEntityIdentifier.legalEntityIdentifierType.required}") LegalEntityIdentifierType type,
        @NotEmpty(message = "{LegalEntityIdentifier.value.required}") String value
) {
}
