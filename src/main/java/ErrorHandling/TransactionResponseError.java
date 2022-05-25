package ErrorHandling;

public class TransactionResponseError {


    private final String code ="API000";
    private final String description="Errore tecnico  La condizione BP049 non e' prevista per il conto id 14537780";

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "TransactionResponseError{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }


}
