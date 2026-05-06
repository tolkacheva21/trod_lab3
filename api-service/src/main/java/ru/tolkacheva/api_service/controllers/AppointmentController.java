package ru.tolkacheva.api_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.tolkacheva.api_service.dto.AppointmentDto;
import ru.tolkacheva.api_service.services.DataServiceClient;
import ru.tolkacheva.api_service.services.KafkaProducerService;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final KafkaProducerService producer;
    private final DataServiceClient client;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AppointmentDto dto) {
        producer.send(dto);
        return ResponseEntity.ok().body("Сообщение отправлено");
    }

    @GetMapping("/search")
    public Flux<AppointmentDto> search() {
        return client.search();
    }

    @GetMapping("/reports/count-day")
    public Flux<Object[]> countByDay() {
        return client.countByDay();
    }

    @GetMapping("/reports/top-doctors")
    public Flux<Object[]> topDoctors() {
        return client.topDoctors();
    }

    @GetMapping("/reports/top-patients")
    public Flux<Object[]> topPatients() {
        return client.topPatients();
    }
}
