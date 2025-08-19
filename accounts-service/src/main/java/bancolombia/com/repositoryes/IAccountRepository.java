package bancolombia.com.repositoryes;

import bancolombia.com.dto.AccountDto;
import bancolombia.com.dto.BankDto;
import bancolombia.com.models.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IAccountRepository extends ReactiveCrudRepository<Account, Long> {
    Mono<Account> findByNumber(Long number);
}
