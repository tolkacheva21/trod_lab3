package ru.tolkacheva.api_service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.tolkacheva.api_service.dto.AppointmentDto;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, AppointmentDto> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public void send(AppointmentDto dto) {
        String key = dto.getDoctorName();
        kafkaTemplate.send(topic, key, dto);
    }
}
