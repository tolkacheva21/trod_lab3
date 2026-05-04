package ru.tolkacheva.data_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tolkacheva.data_service.entities.Appointment;
import ru.tolkacheva.data_service.repositories.AppointmentRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DataController {
    private final AppointmentRepository appointmentRepo;

    @GetMapping("/appointments/search")
    public List<Appointment> search() {
        return appointmentRepo.findAll();
    }

    @GetMapping("/reports/count-day")
    public List<Object[]> countByDay() {
        return appointmentRepo.countByDay();
    }

    @GetMapping("/reports/top-doctors")
    public List<Object[]> topDoctors() {
        return appointmentRepo.topDoctors();
    }

    @GetMapping("/reports/top-patients")
    public List<Object[]> topPatients() {
        return appointmentRepo.topPatients();
    }
}
