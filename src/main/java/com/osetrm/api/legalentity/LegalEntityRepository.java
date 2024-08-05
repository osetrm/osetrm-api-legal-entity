package com.osetrm.api.legalentity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotEmpty;

import java.util.Optional;

@ApplicationScoped
public class LegalEntityRepository implements PanacheRepositoryBase<LegalEntityEntity, Long> {

    public Optional<LegalEntityEntity> findByLegalEntityIdentifier(@NotEmpty String legalEntityIdentifier) {
        return find("legalEntityIdentifier", legalEntityIdentifier).firstResultOptional();
    }

}