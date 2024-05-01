package ua.kiev.cote.artrestservertemperaturemeasurement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorDTO {
    @NotEmpty(message = "Поле не должно быть пустым.")
    @Size(min = 3, max = 50, message = "Название сенсора должно быть от 3 до 50 символов")
    private String title;
}
