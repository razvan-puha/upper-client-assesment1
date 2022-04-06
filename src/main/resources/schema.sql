CREATE TABLE staff
(
    id                INT AUTO_INCREMENT NOT NULL,
    name              VARCHAR(255),
    uuid              UUID,
    registration_date date,
    CONSTRAINT pk_staff PRIMARY KEY (id)
);
CREATE TABLE patient
(
    id              INT AUTO_INCREMENT NOT NULL,
    name            VARCHAR(255),
    age             INT,
    last_visit_date date,
    CONSTRAINT pk_patient PRIMARY KEY (id)
);