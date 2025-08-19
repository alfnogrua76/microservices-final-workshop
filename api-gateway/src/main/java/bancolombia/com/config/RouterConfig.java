package bancolombia.com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Value("${banks-service.url}") private String banksServiceUrl;
    @Value("${banks-service.id}") private String banksServiceId;
    @Value("${banks-service.path}") private String banksServicePath;

    @Value("${accounts-service.url}") private String accountsServiceUrl;
    @Value("${accounts-service.id}") private String accountsServiceId;
    @Value("${accounts-service.path}") private String accountsServicePath;

    @Value("${transactions-service.url}") private String transactionsServiceUrl;
    @Value("${transactions-service.id}") private String transactionsServiceId;
    @Value("${transactions-service.path}") private String transactionsServicePath;

    @Bean
    public RouteLocator createRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(transactionsServiceId,route->route.path(transactionsServicePath).uri(transactionsServiceUrl))
                .route(accountsServiceId,route->route.path(accountsServicePath).uri(accountsServiceUrl))
                .route(banksServiceId,route->route.path(banksServicePath).uri(banksServiceUrl))

                .build();
    }
}
