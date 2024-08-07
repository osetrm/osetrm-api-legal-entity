package com.osetrm.api.legalentity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface LegalEntityMapper {

    @Mapping(source = ".", target = "legalEntityIdentifier")
    LegalEntity toDomain(LegalEntityEntity entity);

    @Mapping(source = "legalEntityIdentifier.legalEntityIdentifierType", target = "legalEntityIdentifierType")
    LegalEntityEntity toEntity(LegalEntity domain);

    @Mapping(source = "legalEntityIdentifier.legalEntityIdentifierType", target = "legalEntityIdentifierType")
    void copy(LegalEntity source, @MappingTarget LegalEntityEntity target);

    default LegalEntityIdentifier map(LegalEntityEntity entity){
        return new LegalEntityIdentifier(entity.legalEntityIdentifierType, entity.legalEntityIdentifier);
    }

    default String map(LegalEntityIdentifier legalEntityIdentifier) {
        return legalEntityIdentifier.value();
    }
    
}