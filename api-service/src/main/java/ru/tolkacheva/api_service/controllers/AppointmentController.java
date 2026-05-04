package ru.tolkacheva.api_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.tolkacheva.api_service.dto.AppointmentDto;
import ru.tolkacheva.api_service.services.KafkaProducerService;

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
                .uri("http://data-service:8081/appointments/search")
                .retrieve()
                .bodyToFlux(AppointmentDto.class);
    }

    @GetMapping("/reports/count-day")
    public Flux<Object[]> countByDay() {
        return webClient.get()
                .uri("http://data-service:8081/reports/count-day")
                .retrieve()
                .bodyToFlux(Object[].class);
    }

    @GetMapping("/reports/top-doctors")
    public Flux<Object[]> topDoctors() {
        return webClient.get()
                .uri("http://data-service:8081/reports/top-doctors")
                .retrieve()
                .bodyToFlux(Object[].class);
    }

    @GetMapping("/reports/top-patients")
    public Flux<Object[]> topPatients() {
        return webClient.get()
                .uri("http://data-service:8081/reports/top-patients")
                .retrieve()
                .bodyToFlux(Object[].class);
    }
}
