package ua.kiev.cote.artrestservertemperaturemeasurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.cote.artrestservertemperaturemeasurement.model.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByTitle(String title);
}
