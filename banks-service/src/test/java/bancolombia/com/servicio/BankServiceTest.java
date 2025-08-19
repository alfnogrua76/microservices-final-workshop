package bancolombia.com.servicio;

import bancolombia.com.model.Bank;
import bancolombia.com.repository.IBanksRepository;
import bancolombia.com.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BankServiceTest {

    @Mock
    private IBanksRepository repository;

    @InjectMocks
    private BankService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_Found() {
        Bank bank = new Bank(1L, "Bank1", "Description1");

        Mockito.when(repository.findById(1L)).thenReturn(Mono.just(bank));

        StepVerifier.create(service.findById(1L))
                .expectNext(bank)
                .verifyComplete();
    }

    @Test
    void testFindById_NotFound() {
        Mockito.when(repository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(service.findById(1L))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Product not Found"))
                .verify();
    }

    @Test
    void testFindAll_Found() {
        Bank bank1 = new Bank(1L, "Bank1", "Description1");
        Bank bank2 = new Bank(2L, "Bank2", "Description2");

        Mockito.when(repository.findAll()).thenReturn(Flux.just(bank1, bank2));

        StepVerifier.create(service.findAll())
                .expectNext(bank1, bank2)
                .verifyComplete();
    }

    @Test
    void testFindAll_NotFound() {
        Mockito.when(repository.findAll()).thenReturn(Flux.empty());

        StepVerifier.create(service.findAll())
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Products not Found"))
                .verify();
    }

    @Test
    void testCreate() {
        Bank bank = new Bank(null, "NewBank", "NewDescription");
        Bank createdBank = new Bank(1L, "NewBank", "NewDescription");

        Mockito.when(repository.save(bank)).thenReturn(Mono.just(createdBank));

        StepVerifier.create(service.create(bank))
                .expectNext(createdBank)
                .verifyComplete();
    }

    @Test
    void testUpdate_Found() {
        Bank existing = new Bank(1L, "OldBank", "OldDescription");
        Bank updateData = new Bank(null, "UpdatedBank", "UpdatedDescription");
        Bank updated = new Bank(1L, "UpdatedBank", "UpdatedDescription");

        Mockito.when(repository.findById(1L)).thenReturn(Mono.just(existing));
        Mockito.when(repository.save(existing)).thenReturn(Mono.just(updated));

        StepVerifier.create(service.update(1L, updateData))
                .expectNext(updated)
                .verifyComplete();
    }

    @Test
    void testUpdate_NotFound() {
        Bank updateData = new Bank(null, "UpdatedBank", "UpdatedDescription");

        Mockito.when(repository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(service.update(1L, updateData))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("Product not Found"))
                .verify();
    }
}
