CREATE TABLE account (
     id             BIGSERIAL PRIMARY KEY,
     account_number VARCHAR(18) NOT NULL UNIQUE,
     first_name     VARCHAR(100) NOT NULL,
     last_name      VARCHAR(100) NOT NULL,
     balance        BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_account_last_name ON account(last_name);