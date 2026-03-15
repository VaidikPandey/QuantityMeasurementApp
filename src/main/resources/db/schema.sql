CREATE TABLE IF NOT EXISTS quantity_measurement_entity (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation       VARCHAR(50)  NOT NULL,
    measurement_type VARCHAR(50) NOT NULL,
    result          VARCHAR(255) NOT NULL,
    is_error        BOOLEAN      DEFAULT FALSE,
    message         VARCHAR(255),
    created_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);