package com.osetrm.api.legalentity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LegalEntityService {

    private final LegalEntityRepository legalEntityRepository;

    public LegalEntityService(LegalEntityRepository legalEntityRepository) {
        this.legalEntityRepository = legalEntityRepository;
    }

    @Transactional
    public void create(LegalEntity legalEntity) {
        legalEntityRepository.findByLegalEntityIdentifier(legalEntity.legalEntityIdentifier())
                .ifPresent(legalEntityEntity -> {
                    throw new RuntimeException("Legal Entity already exists");
                });
        LegalEntityEntity legalEntityEntity = new LegalEntityEntity();
        legalEntityEntity.legalEntityIdentifier = legalEntity.legalEntityIdentifier();
        legalEntityEntity.legalName = legalEntity.legalName();
        legalEntityRepository.persist(legalEntityEntity);
    }

}
