package ua.kiev.cote.artrestservertemperaturemeasurement.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.kiev.cote.artrestservertemperaturemeasurement.model.Measurement;
import ua.kiev.cote.artrestservertemperaturemeasurement.service.SensorService;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if (measurement.getSensor() == null) return;

        if (sensorService.findByTitle(measurement.getSensor().getTitle()).isEmpty()){
            errors.rejectValue("sensor", "", "Сенсор не зарегестрирован в базе данных.");
        }
    }
}
