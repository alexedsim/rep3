package model;

import java.util.List;

public class TransactionsResponse {
    private String status;
    private List error;
    private Transactions payload;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List getError() {
        return error;
    }

    public void setError(List error) {
        this.error = error;
    }

    public Transactions getPayload() {
        return payload;
    }

    public void setPayload(Transactions payload) {
        this.payload = payload;
    }
}
