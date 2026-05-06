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
            topics = "${kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(AppointmentDto dto) {
        try {
            service.save(dto);
        } catch (Exception e) {
            System.err.println("Kafka error: " + e.getMessage());
        }
    }
}
