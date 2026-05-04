package ru.tolkacheva.data_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tolkacheva.data_service.entities.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @Query("""
        SELECT a.doctor.name, COUNT(a)
        FROM Appointment a
        GROUP BY a.doctor.name
        ORDER BY COUNT(a) DESC
    """)
    List<Object[]> topDoctors();

    // Количество записей по дням
    @Query("""
        SELECT DATE(a.appointmentDate), COUNT(a)
        FROM Appointment a
        GROUP BY DATE(a.appointmentDate)
    """)
    List<Object[]> countByDay();

    @Query("""
        SELECT a.patient.name, COUNT(a)
        FROM Appointment a
        GROUP BY a.patient.name
        ORDER BY COUNT(a) DESC
    """)
    List<Object[]> topPatients();
}
