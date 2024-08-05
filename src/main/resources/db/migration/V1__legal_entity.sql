CREATE TYPE legal_entity_identifier_type AS ENUM ('LEID', 'NPID', 'PLID');
CREATE CAST (CHARACTER VARYING as legal_entity_identifier_type) WITH INOUT AS IMPLICIT;

CREATE TABLE legal_entity
(
    legal_entity_id BIGSERIAL PRIMARY KEY,
    legal_entity_identifier TEXT NOT NULL UNIQUE,
    legal_entity_identifier_type legal_entity_identifier_type NOT NULL,
    legal_name TEXT NOT NULL
);
ALTER SEQUENCE legal_entity_legal_entity_id_seq RESTART 1000000;