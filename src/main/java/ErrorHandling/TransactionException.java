package ErrorHandling;

public class TransactionException extends RuntimeException{

   public TransactionException(TransactionResponseError transactionResponseError){
        super(transactionResponseError.toString());
    }
}
