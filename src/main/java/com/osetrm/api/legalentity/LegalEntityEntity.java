package com.osetrm.api.legalentity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity(name = "LegalEntity")
@Table(name = "legal_entity")
public class LegalEntityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "legal_entity_id")
    public Integer legalEntityId;

    @Column(name = "legal_entity_identifier")
    @NotEmpty(message = "{LegalEntity.legalEntityIdentifier.required}")
    public String legalEntityIdentifier;

    @Column(name = "legal_name")
    @NotEmpty(message = "{LegalEntity.legalName.required}")
    public String legalName;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LegalEntityEntity that = (LegalEntityEntity) o;
        return Objects.equals(legalEntityId, that.legalEntityId) && Objects.equals(legalEntityIdentifier, that.legalEntityIdentifier) && Objects.equals(legalName, that.legalName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(legalEntityId, legalEntityIdentifier, legalName);
    }

    @Override
    public String toString() {
        return "LegalEntityEntity{" +
                "legalEntityId=" + legalEntityId +
                ", legalEntityIdentifier='" + legalEntityIdentifier + '\'' +
                ", legalName='" + legalName + '\'' +
                '}';
    }

}
