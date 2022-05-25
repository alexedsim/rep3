package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBonifico {
    private String status;
    private List<Error> errors;
    private PayloadBonifico payload;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public PayloadBonifico getPayload() {
        return payload;
    }

    public void setPayload(PayloadBonifico payload) {
        this.payload = payload;
    }
}
