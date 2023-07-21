DROP TABLE IF EXISTS carriers CASCADE;
DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;

CREATE TABLE carriers (
                          id          BIGSERIAL NOT NULL,
                          name        VARCHAR(30) not null unique,
                          description VARCHAR(150),
                          location    VARCHAR(100)
);

ALTER TABLE carriers ADD CONSTRAINT carriers_pk PRIMARY KEY (id);

CREATE TABLE clients (
                         id              BIGSERIAL NOT NULL,
                         name            VARCHAR(30) not null unique,
                         first_name      VARCHAR(30),
                         last_name       VARCHAR(30),
                         email           VARCHAR(50),
                         address         VARCHAR(150),
                         pay_day	     date default CURRENT_DATE,
                         monthly_fee     NUMERIC(5,2),
                         carrier_id      BIGINT NOT NULL
);

ALTER TABLE clients ADD CONSTRAINT clients_pk PRIMARY KEY ( id );

CREATE TABLE accounts (
                          id             BIGSERIAL NOT NULL,
                          account_type   VARCHAR(30),
                          create_date    date default CURRENT_DATE,
                          client_id      BIGINT NOT NULL
);

ALTER TABLE accounts ADD CONSTRAINT accounts_pk PRIMARY KEY ( id );

ALTER TABLE accounts
    ADD CONSTRAINT accounts_clients_fk FOREIGN KEY ( client_id )
        REFERENCES clients ( id );


ALTER TABLE clients
    ADD CONSTRAINT clients_carriers_fk FOREIGN KEY ( carrier_id )
        REFERENCES carriers ( id );