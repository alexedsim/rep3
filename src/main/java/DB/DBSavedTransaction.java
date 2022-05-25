package DB;


import model.Transaction;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DBSavedTransaction {
    private @Id @GeneratedValue Long id;

    @UpdateTimestamp
    private LocalDateTime dataSalvataggio;


    private Transaction transaction;

    public DBSavedTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataSalvataggio() {
        return dataSalvataggio;
    }

    public void setDataSalvataggio(LocalDateTime dataSalvataggio) {
        this.dataSalvataggio = dataSalvataggio;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
