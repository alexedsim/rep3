package model;

public class Account {
    private String accountCode;
    private String bicCode;

    public Account(String accountCode, String bicCode) {
        this.accountCode = accountCode;
        this.bicCode = bicCode;
    }
    // Getter Methods

    public String getAccountCode() {
        return accountCode;
    }

    public String getBicCode() {
        return bicCode;
    }

    // Setter Methods

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }
}
