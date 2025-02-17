package com.osetrm.api.legalentity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class LegalEntityRepository implements PanacheRepositoryBase<LegalEntityEntity, Integer> {

    public Optional<LegalEntityEntity> findByLegalEntityIdentifier(String legalEntityIdentifier) {
        return find("legalEntityIdentifier", legalEntityIdentifier).firstResultOptional();
    }

}
