--liquibase formatted sql

--changeset developer:4
--comment: Добавление колонки created_at
ALTER TABLE units_models
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
--rollback ALTER TABLE units_models DROP COLUMN created_at;