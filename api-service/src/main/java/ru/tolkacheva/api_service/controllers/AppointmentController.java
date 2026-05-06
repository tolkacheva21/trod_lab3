package ru.tolkacheva.api_service.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tolkacheva.api_service.dto.AppointmentDto;
import ru.tolkacheva.api_service.services.KafkaProducerService;

@Slf4j
@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final KafkaProducerService producer;
    private final WebClient webClient;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AppointmentDto dto) {
        producer.send(dto);
        return ResponseEntity.ok().body("Сообщение отправлено");
    }

    @GetMapping("/search")
    public Flux<AppointmentDto> search() {
        return webClient.get()
                .uri("/appointments/search")
                .retrieve()
                .bodyToFlux(AppointmentDto.class)
                .onErrorResume(error -> {
                    log.error("Error fetching search results: {}", error.getMessage());
                    return Flux.error(error);
                });
    }

    @GetMapping("/reports/count-day")
    public Flux<Object[]> countByDay() {
        return webClient.get()
                .uri("/reports/count-day")
                .retrieve()
                .bodyToFlux(Object[].class)
                .onErrorResume(error -> {
                    log.error("Error fetching count-day: {}", error.getMessage());
                    return Flux.error(error);
                });
    }

    @GetMapping("/reports/top-doctors")
    public Flux<Object[]> topDoctors() {
        return webClient.get()
                .uri("/reports/top-doctors")
                .retrieve()
                .bodyToFlux(Object[].class)
                .onErrorResume(error -> {
                    log.error("Error fetching top-doctors: {}", error.getMessage());
                    return Flux.error(error);
                });
    }

    @GetMapping("/reports/top-patients")
    public Flux<Object[]> topPatients() {
        return webClient.get()
                .uri("/reports/top-patients")
                .retrieve()
                .bodyToFlux(Object[].class)
                .onErrorResume(error -> {
                    log.error("Error fetching top-patients: {}", error.getMessage());
                    return Flux.error(error);
                });
    }
}
