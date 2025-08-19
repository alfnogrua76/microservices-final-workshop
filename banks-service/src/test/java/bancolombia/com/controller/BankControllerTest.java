package bancolombia.com.controller;

import bancolombia.com.model.Bank;
import bancolombia.com.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
class BankControllerTest {

    private WebTestClient webTestClient;
    private BankService bankService;

    @BeforeEach
    void setup() {
        bankService = Mockito.mock(BankService.class);
        BankController controller = new BankController(bankService);
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    void testGetAll() {
        Bank bank1 = new Bank(1L, "Bank1", "Description1");
        Bank bank2 = new Bank(2L, "Bank2", "Description2");

        Mockito.when(bankService.findAll()).thenReturn(Flux.just(bank1, bank2));

        webTestClient.get().uri("/api/bank")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Bank.class)
                .hasSize(2)
                .contains(bank1, bank2);
    }

    @Test
    void testGetById() {
        Bank bank = new Bank(1L, "Bank1", "Description1");

        Mockito.when(bankService.findById(1L)).thenReturn(Mono.just(bank));

        webTestClient.get().uri("/api/bank/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Bank.class)
                .isEqualTo(bank);
    }

    @Test
    void testCreate() {
        Bank bank = new Bank(null, "NewBank", "NewDescription");
        Bank createdBank = new Bank(1L, "NewBank", "NewDescription");

        Mockito.when(bankService.create(bank)).thenReturn(Mono.just(createdBank));

        webTestClient.post().uri("/api/bank")
                .bodyValue(bank)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Bank.class)
                .isEqualTo(createdBank);
    }

    @Test
    void testUpdate() {
        Bank bank = new Bank(null, "UpdatedBank", "UpdatedDescription");
        Bank updatedBank = new Bank(1L, "UpdatedBank", "UpdatedDescription");

        Mockito.when(bankService.update(1L, bank)).thenReturn(Mono.just(updatedBank));

        webTestClient.put().uri("/api/bank/1")
                .bodyValue(bank)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Bank.class)
                .isEqualTo(updatedBank);
    }

}

