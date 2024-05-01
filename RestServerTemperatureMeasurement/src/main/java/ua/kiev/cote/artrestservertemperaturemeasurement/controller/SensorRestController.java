package ua.kiev.cote.artrestservertemperaturemeasurement.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.kiev.cote.artrestservertemperaturemeasurement.dto.ErrorResponse;
import ua.kiev.cote.artrestservertemperaturemeasurement.dto.SensorDTO;
import ua.kiev.cote.artrestservertemperaturemeasurement.model.Sensor;
import ua.kiev.cote.artrestservertemperaturemeasurement.service.SensorService;
import ua.kiev.cote.artrestservertemperaturemeasurement.util.Error;
import ua.kiev.cote.artrestservertemperaturemeasurement.util.exception.MeasurementException;
import ua.kiev.cote.artrestservertemperaturemeasurement.validator.SensorValidator;

@RestController
@RequestMapping("/sensor")
public class SensorRestController {
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;
    private final SensorService sensorService;

    @Autowired
    public SensorRestController(ModelMapper modelMapper, SensorValidator sensorValidator, SensorService sensorService) {
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {
        Sensor sensor = convertToSensor(sensorDTO);

        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors()) {
            Error.returnErrorToClient(bindingResult);
        }

        sensorService.register(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementException exception){
        ErrorResponse response = new ErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
