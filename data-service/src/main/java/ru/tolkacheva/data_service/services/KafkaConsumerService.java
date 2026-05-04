package ru.tolkacheva.data_service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.tolkacheva.data_service.dto.AppointmentDto;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final AppointmentService service;

    @KafkaListener(
            topics = "appointments-topic",
            groupId = "data-service-group"
    )
    public void consume(AppointmentDto dto) {
        service.save(dto);
    }
}
