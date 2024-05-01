package ua.kiev.cote.artrestservertemperaturemeasurement.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.kiev.cote.artrestservertemperaturemeasurement.dto.MeasurementDTO;
import ua.kiev.cote.artrestservertemperaturemeasurement.dto.ErrorResponse;
import ua.kiev.cote.artrestservertemperaturemeasurement.dto.MeasurementResponse;
import ua.kiev.cote.artrestservertemperaturemeasurement.model.Measurement;
import ua.kiev.cote.artrestservertemperaturemeasurement.service.MeasurementService;
import ua.kiev.cote.artrestservertemperaturemeasurement.util.Error;
import ua.kiev.cote.artrestservertemperaturemeasurement.util.exception.MeasurementException;
import ua.kiev.cote.artrestservertemperaturemeasurement.validator.MeasurementValidator;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;
    private final MeasurementService measurementService;

    public MeasurementController(ModelMapper modelMapper, MeasurementValidator measurementValidator, MeasurementService measurementService) {
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {
        Measurement measurement = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurement, bindingResult);

        if (bindingResult.hasErrors()){
            Error.returnErrorToClient(bindingResult);
        }

        measurementService.addMeasurement(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public MeasurementResponse getMeasurement(){
        return new MeasurementResponse(measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @GetMapping("/raindaycount")
    public long getRainDayCount(){
        return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementException measurementException){
        ErrorResponse response = new ErrorResponse(measurementException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}