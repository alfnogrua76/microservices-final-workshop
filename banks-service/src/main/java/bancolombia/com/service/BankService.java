package bancolombia.com.service;

import bancolombia.com.model.Bank;
import bancolombia.com.repository.IBanksRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankService {
    private final IBanksRepository repository;

    public BankService(IBanksRepository repository) {
        this.repository = repository;
    }

    public Mono<Bank> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not Found")));
    }

    public Flux<Bank> findAll() {
        return repository.findAll()
                .switchIfEmpty(Mono.error(new RuntimeException("Products not Found")));
    }

    public Mono<Bank> create(Bank bank){
        return repository.save(bank);
    }

    public Mono<Bank> update(Long id, Bank bank) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not Found")))
                .flatMap(existing -> {
                    existing.setName(bank.getName());
                    existing.setDescription(bank.getDescription());
                    // setea otros campos...
                    return repository.save(existing);
                });
    }



}
