package ua.kiev.cote.artrestservertemperaturemeasurement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.cote.artrestservertemperaturemeasurement.model.Measurement;
import ua.kiev.cote.artrestservertemperaturemeasurement.repository.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        addDataToMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void addDataToMeasurement(Measurement measurement){
        measurement.setSensor(sensorService.findByTitle(measurement.getSensor().getTitle()).get());
        measurement.setDateTime(LocalDateTime.now());
    }
}
