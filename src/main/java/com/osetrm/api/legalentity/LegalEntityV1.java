package com.osetrm.api.legalentity;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record LegalEntityV1(
        @NotEmpty(message = "{LegalEntity.legalEntityIdentifier.required}")
        @Length(min = 20, max = 20, message = "{LegalEntity.legalEntityIdentifier.length}")
        String legalEntityIdentifier,
        @NotEmpty(message = "{LegalEntity.legalName.required}") String legalName
) {
}
