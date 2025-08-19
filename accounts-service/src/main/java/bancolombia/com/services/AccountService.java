package bancolombia.com.services;

import bancolombia.com.dto.AccountDto;
import bancolombia.com.models.Account;
import bancolombia.com.repositoryes.IAccountRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    private final IAccountRepository repository;
    private final BankServive bankService;

    public AccountService(IAccountRepository repository, BankServive bankService) {
        this.repository = repository;
        this.bankService = bankService;
    }

    public Mono<Account> findById(Long id) {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(new RuntimeException("Account not Found")));
    }

    public Mono<Account> findBynumber(Long number) {
        return repository.findByNumber(number)
            .switchIfEmpty(Mono.error(new RuntimeException("Account not Found")));
    }

    public Flux<Account> findAll() {
        return repository.findAll()
                .switchIfEmpty(Mono.error(new RuntimeException("Products not Found")));
    }
/*
    public Mono<Account> create(Account accoun){
        return repository.save(accoun);
    }
*/
public Mono<Account> create(Account accountDto) {
    return bankService.getBank(accountDto.getBanco())
            .switchIfEmpty(Mono.error(new RuntimeException("BAnco no encontrado")))
            .flatMap(bank -> {
                Account account = new Account();
                account.setBanco(accountDto.getBanco());
                account.setNumber(accountDto.getNumber());
                account.setTitular(accountDto.getTitular());
                account.setSaldo(accountDto.getSaldo());
                // otros campos...
                return repository.save(account);
            });
}

    public Mono<Account> update(Long id, AccountDto account) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Cuenta no encontrado")))
                .flatMap(existing -> {
                    existing.setBanco(account.getBanco());
                    existing.setNumber(account.getNumber());
                    existing.setTitular(account.getTitular());
                    existing.setSaldo(account.getSaldo());
                    // setea otros campos...
                    return repository.save(existing);
                });
    }


}
