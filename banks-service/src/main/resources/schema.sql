CREATE TABLE IF NOT EXISTS banks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200) NOT NULL
);

INSERT INTO banks(name, description) VALUES ('Coolpatria', 'Coolpatria');
INSERT INTO banks(name, description) VALUES ('ITAU', 'ITAU');
