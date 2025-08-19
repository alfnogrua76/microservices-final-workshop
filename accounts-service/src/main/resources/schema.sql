CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    banco INT NOT NULL,
    number INT NOT NULL,
    titular VARCHAR(50) NOT NULL,
    saldo NUMERIC NOT NULL
);

INSERT INTO accounts(banco, number,  titular, saldo) VALUES ( 1, 1234, 'ALFREDO NOGUERA', 500000);
INSERT INTO accounts(banco, number, titular, saldo) VALUES ( 1, 1235, 'JUNIOR NOGUERA', 1000000);
INSERT INTO accounts(banco, number, titular, saldo) VALUES ( 2, 1233, 'JUNIOR NOGUERA', 300000);