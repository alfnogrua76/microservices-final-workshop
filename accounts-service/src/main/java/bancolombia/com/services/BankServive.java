package bancolombia.com.services;

import bancolombia.com.dto.BankDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BankServive {

    private final WebClient.Builder webClientBuilder;

    @Value("${BANKS_SERVICE_URL}")
    private String banksServiceUrl;

    public BankServive(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<BankDto> getBank(Long id) {
        return webClientBuilder
                .build()
                .get()
                .uri(banksServiceUrl +"/"+ id)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new RuntimeException("Product not found")))
                .bodyToMono(BankDto.class);

    }
}

