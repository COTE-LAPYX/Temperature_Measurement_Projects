package ua.kiev.cote.artrestservertemperaturemeasurement.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ua.kiev.cote.artrestservertemperaturemeasurement.util.exception.MeasurementException;

import java.util.List;

public class Error {
    public static void returnErrorToClient(BindingResult bindingResult){
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errorList = bindingResult.getFieldErrors();

        for(FieldError error : errorList){
            errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage()).append(";");
        }
        throw new MeasurementException(errorMessage.toString());
    }
}
