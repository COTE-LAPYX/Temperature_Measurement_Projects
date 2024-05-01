package ua.kiev.cote.artrestservertemperaturemeasurement.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.kiev.cote.artrestservertemperaturemeasurement.model.Sensor;
import ua.kiev.cote.artrestservertemperaturemeasurement.service.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (sensorService.findByTitle(sensor.getTitle()).isPresent()){
            errors.rejectValue("title", "", "Сенсор с таким названием уже есть в базе.");
        }
    }
}
