CREATE TABLE legal_entity
(
    legal_entity_id SERIAL PRIMARY KEY,
    legal_entity_identifier TEXT NOT NULL,
    legal_name  TEXT NOT NULL
);
ALTER SEQUENCE legal_entity_legal_entity_id_seq RESTART 1000000000;
CREATE INDEX legal_entity_legal_entity_identifier_idx ON legal_entity (legal_entity_identifier);