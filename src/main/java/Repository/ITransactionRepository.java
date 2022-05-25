package Repository;

import DB.DBSavedTransaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<DBSavedTransaction, Long> {

}
