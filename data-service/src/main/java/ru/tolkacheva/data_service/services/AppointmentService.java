package ru.tolkacheva.data_service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tolkacheva.data_service.dto.AppointmentDto;
import ru.tolkacheva.data_service.entities.Appointment;
import ru.tolkacheva.data_service.entities.Doctor;
import ru.tolkacheva.data_service.entities.Patient;
import ru.tolkacheva.data_service.repositories.AppointmentRepository;
import ru.tolkacheva.data_service.repositories.DoctorRepository;
import ru.tolkacheva.data_service.repositories.PatientRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public void save(AppointmentDto dto) {
        Patient patient = patientRepo.findByName(dto.getPatientName())
                .orElseGet(() -> patientRepo.save(
                        new Patient(null, dto.getPatientName())
                ));

        Doctor doctor = doctorRepo.findByName(dto.getDoctorName())
                .orElseGet(() -> doctorRepo.save(
                   new Doctor(
                           null,
                           dto.getDoctorName(),
                           dto.getDoctorSpecialization()
                           )
                ));

        Appointment appointment = new Appointment(
                null,
                patient,
                doctor,
                dto.getDate()
        );

        if (appointmentRepo.existsByPatientAndDoctorAndDate(
                patient, doctor, dto.getDate())) {
            return;
        }

        appointmentRepo.save(appointment);
    }
}
