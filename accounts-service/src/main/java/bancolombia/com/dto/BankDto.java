package bancolombia.com.dto;

public class BankDto {
    private String name;
    private String description;

    public BankDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public BankDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
