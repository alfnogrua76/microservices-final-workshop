package bancolombia.com.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("accounts")
public class Account {
    @Id
    private Long id;
    private Long banco;
    private Long number;
    private String titular;
    private double saldo;

    public Account(Long id, Long acc, Long number, String titular, double saldo) {
        this.id = id;
        this.banco = banco;
        this.number = number;
        this.titular = titular;
        this.saldo = saldo;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBanco() {
        return banco;
    }

    public void setBanco(Long acc) {
        this.banco = banco;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
