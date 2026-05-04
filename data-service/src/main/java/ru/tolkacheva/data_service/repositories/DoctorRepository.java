package ru.tolkacheva.data_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tolkacheva.data_service.entities.Doctor;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByName(String name);
}
