package bancolombia.com.repository;

import bancolombia.com.model.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ITransactionRepository extends ReactiveCrudRepository<Transaction, Long> {
}
