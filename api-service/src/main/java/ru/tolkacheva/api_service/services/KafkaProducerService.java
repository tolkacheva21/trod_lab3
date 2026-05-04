package ru.tolkacheva.api_service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.tolkacheva.api_service.dto.AppointmentDto;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, AppointmentDto> kafkaTemplate;

    public void send(AppointmentDto dto) {
        kafkaTemplate.send("appointments-topic", dto);
    }
}
