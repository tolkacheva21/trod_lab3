package ru.tolkacheva.data_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private String patientName;
    private String doctorName;
    private String doctorSpecialization;
    private LocalDateTime date;
}
