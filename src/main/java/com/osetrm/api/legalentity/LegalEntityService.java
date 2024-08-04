package com.osetrm.api.legalentity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class LegalEntityService {

    private final Map<String, LegalEntity> legalEntities = new HashMap<>();

    public List<LegalEntity> findAll() {
        return List.copyOf(legalEntities.values());
    }

    public Optional<LegalEntity> findByLegalEntityIdentifier(@NotEmpty String legalEntityIdentifier) {
        return Optional.ofNullable(legalEntities.get(legalEntityIdentifier));
    }

    public void create(@Valid LegalEntity legalEntity) {
        legalEntities.put(legalEntity.legalEntityIdentifier().value(), legalEntity);
    }

    public void update(@Valid LegalEntity legalEntity) {
        legalEntities.put(legalEntity.legalEntityIdentifier().value(), legalEntity);
    }

}
