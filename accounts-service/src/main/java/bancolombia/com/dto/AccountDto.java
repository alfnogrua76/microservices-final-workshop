package bancolombia.com.dto;

public class AccountDto {
    private Long id;
    private Long banco;
    private Long number;
    private String titular;
    private double saldo;

    public AccountDto(Long id, Long number, String titular, double saldo) {
        this.id = id;
        this.banco = banco;
        this.number = number;
        this.titular = titular;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountDto() {
    }

    public Long getBanco() {
        return banco;
    }

    public void setBanco(Long banco) {
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
