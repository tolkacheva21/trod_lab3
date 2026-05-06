package ru.tolkacheva.api_service.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(@Value("${data.service.url}") String baseUrl) {
        log.info("Initializing WebClient with baseUrl: {}", baseUrl);
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter((request, next) -> next.exchange(request)
                        .retryWhen(Retry.backoff(5, Duration.ofSeconds(1))
                                .maxBackoff(Duration.ofSeconds(5))
                                .doBeforeRetry(rs -> log.warn("Retrying request, attempt {}", rs.totalRetries() + 1)))
                )
                .build();
    }
}
