CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_P INT,
    account_B INT,
    tid VARCHAR(20) NOT NULL,
    type VARCHAR(40) NOT NULL,
    pagador VARCHAR(20),
    beneficiary VARCHAR(20),
    monto NUMERIC NOT NULL,
    data VARCHAR(30) NOT NULL
);

INSERT INTO transactions(account_P, account_B, tid, type, pagador, beneficiary, monto, data) VALUES ( 1234, 1235, 'ABC123', 'TANFERENCIA', 'ALFREDO NOGUERA', 'JUNIOR NOGUERA' , 10000, '10-05-2024');