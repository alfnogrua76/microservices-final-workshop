package bancolombia.com.service;

import bancolombia.com.dto.AccountDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    private final WebClient.Builder webClientBuilder;

    @Value("${ACCOUNTS_SERVICE_URL}")
    private String banksServiceUrl;

    public AccountService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<AccountDto> getAccount(Long id) {
        return webClientBuilder
                .build()
                .get()
                .uri(banksServiceUrl +"/number/"+ id)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new RuntimeException("Account not found")))
                .bodyToMono(AccountDto.class);

    }

    public Mono<AccountDto> updateAccount(AccountDto accountDto) {
        return webClientBuilder
                .build()
                .post()
                .uri(banksServiceUrl +"/" +accountDto.getId())
                .body(Mono.just(accountDto), AccountDto.class)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new RuntimeException("Deposito not found")))
                .bodyToMono(AccountDto.class);

    }
}
