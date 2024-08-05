package com.osetrm.api.legalentity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class LegalEntityService {

    private final LegalEntityRepository legalEntityRepository;
    private final LegalEntityMapper legalEntityMapper;

    public LegalEntityService(LegalEntityRepository legalEntityRepository, LegalEntityMapper legalEntityMapper) {
        this.legalEntityRepository = legalEntityRepository;
        this.legalEntityMapper = legalEntityMapper;
    }

    public List<LegalEntity> findAll() {
        return legalEntityRepository.findAll().stream()
                .map(legalEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<LegalEntity> findByLegalEntityIdentifier(@NotEmpty String legalEntityIdentifier) {
        return legalEntityRepository.findByLegalEntityIdentifier(legalEntityIdentifier)
                .map(legalEntityMapper::toDomain);
    }

    @Transactional
    public void create(@Valid LegalEntity legalEntity) {
        legalEntityRepository.findByLegalEntityIdentifier(legalEntity.legalEntityIdentifier().value()).ifPresent(legalEntityEntity -> {
            throw new RuntimeException("Legal entity already exists");
        });
        LegalEntityEntity entity = this.legalEntityMapper.toEntity(legalEntity);
        this.legalEntityRepository.persist(entity);
    }

    @Transactional
    public void update(@Valid LegalEntity legalEntity) {
        LegalEntityEntity entity = this.legalEntityRepository.findByLegalEntityIdentifier(legalEntity.legalEntityIdentifier().value())
                .orElseThrow(() -> new RuntimeException("Legal entity not found"));
        legalEntityMapper.copy(legalEntity, entity);
        this.legalEntityRepository.persist(entity);
    }

}