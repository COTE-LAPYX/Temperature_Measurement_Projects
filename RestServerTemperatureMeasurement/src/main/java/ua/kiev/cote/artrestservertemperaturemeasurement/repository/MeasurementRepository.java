package ua.kiev.cote.artrestservertemperaturemeasurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kiev.cote.artrestservertemperaturemeasurement.model.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
