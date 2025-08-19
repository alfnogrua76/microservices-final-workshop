package bancolombia.com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("transactions")
public class Transaction {
    @Id
    private Long id;
    private String tid;//RETIRP; DEPOSTTO ; TRANSFERENCIA
    private String type;
    private String pagador;
    private Long accountP;
    private double monto;
    private String beneficiary;
    private Long accountB;
    private String data;

    public Transaction(Long id, String tid, String type,
                       String pagador, Long accountP, double monto,
                       String beneficiary, Long accountB, String data) {
        this.id = id;
        this.tid = tid;
        this.type = type;
        this.pagador = pagador;
        this.accountP = accountP;
        this.monto = monto;
        this.beneficiary = beneficiary;
        this.accountB = accountB;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction() {
    }


    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPagador() {
        return pagador;
    }

    public void setPagador(String pagador) {
        this.pagador = pagador;
    }

    public Long getAccountP() {
        return accountP;
    }

    public void setAccountP(Long accuntP) {
        this.accountP = accuntP;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Long getAccountB() {
        return accountB;
    }

    public void setAccountB(Long accountB) {
        this.accountB = accountB;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
