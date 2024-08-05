package com.osetrm.api.legalentity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity(name = "LegalEntity")
@Table(name = "legal_entity")
public class LegalEntityEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "legal_entity_id")
    public Long legalEntityId;

    @Column(name = "legal_entity_identifier")
    @NotEmpty(message = "{LegalEntity.legalEntityIdentifier.required}")
    public String legalEntityIdentifier;

    @Column(name = "legal_entity_identifier_type")
    @NotNull(message = "{LegalEntity.legalEntityIdentifierType.required}")
    @Enumerated(EnumType.STRING)
    public LegalEntityIdentifierType legalEntityIdentifierType;

    @Column(name = "legal_name")
    @NotEmpty(message = "{LegalEntity.legalName.required}")
    public String legalName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegalEntityEntity that = (LegalEntityEntity) o;
        return Objects.equals(legalEntityId, that.legalEntityId) && Objects.equals(legalEntityIdentifier, that.legalEntityIdentifier) && legalEntityIdentifierType == that.legalEntityIdentifierType && Objects.equals(legalName, that.legalName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(legalEntityId, legalEntityIdentifier, legalEntityIdentifierType, legalName);
    }

    @Override
    public String toString() {
        return "LegalEntityEntity{" +
                "legalEntityId=" + legalEntityId +
                ", legalEntityIdentifier='" + legalEntityIdentifier + '\'' +
                ", legalEntityIdentifierType=" + legalEntityIdentifierType +
                ", legalName='" + legalName + '\'' +
                '}';
    }

}