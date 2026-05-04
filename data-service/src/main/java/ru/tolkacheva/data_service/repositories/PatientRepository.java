package ru.tolkacheva.data_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tolkacheva.data_service.entities.Patient;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findByName(String name);
}
