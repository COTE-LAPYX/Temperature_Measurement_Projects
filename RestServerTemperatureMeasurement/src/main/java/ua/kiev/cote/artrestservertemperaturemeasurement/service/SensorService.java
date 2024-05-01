package ua.kiev.cote.artrestservertemperaturemeasurement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.cote.artrestservertemperaturemeasurement.model.Sensor;
import ua.kiev.cote.artrestservertemperaturemeasurement.repository.SensorRepository;

import java.util.Optional;
@Service@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> findByTitle(String title) {
        return sensorRepository.findByTitle(title);
    }

    @Transactional
    public void register(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
