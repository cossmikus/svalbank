CREATE TABLE transfer (
      id              BIGSERIAL PRIMARY KEY,
      from_account_id BIGINT NOT NULL REFERENCES account(id),
      to_account_id   BIGINT NOT NULL REFERENCES account(id),
      amount          BIGINT NOT NULL CHECK (amount > 0),
      status          VARCHAR(20) NOT NULL,
      created_at      TIMESTAMPTZ NOT NULL DEFAULT now()
);

