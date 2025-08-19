package bancolombia.com.repository;

import bancolombia.com.model.Bank;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IBanksRepository extends ReactiveCrudRepository<Bank, Long> {
}
