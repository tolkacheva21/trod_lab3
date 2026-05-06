package ru.tolkacheva.api_service.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.tolkacheva.api_service.dto.AppointmentDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataServiceClient {
    private final WebClient webClient;

    public Flux<AppointmentDto> search() {
        return webClient.get()
                .uri("/appointments/search")
                .retrieve()
                .bodyToFlux(AppointmentDto.class)
                .doOnError(e -> log.error("Error fetching search results", e));
    }

    public Flux<Object[]> countByDay() {
        return webClient.get()
                .uri("/reports/count-day")
                .retrieve()
                .bodyToFlux(Object[].class)
                .doOnError(e -> log.error("Error fetching count-day", e));
    }

    public Flux<Object[]> topDoctors() {
        return webClient.get()
                .uri("/reports/top-doctors")
                .retrieve()
                .bodyToFlux(Object[].class)
                .doOnError(e -> log.error("Error fetching top-doctors", e));
    }

    public Flux<Object[]> topPatients() {
        return webClient.get()
                .uri("/reports/top-patients")
                .retrieve()
                .bodyToFlux(Object[].class)
                .doOnError(e -> log.error("Error fetching top-patients", e));
    }
}
