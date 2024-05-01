package ua.kiev.cote.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class MeasurementDTO {
    @NotNull
    @Min(-60)
    @Max(60)
    private Double value;
    @NotNull
    private boolean raining;
    @NotNull
    private SensorDTO sensor;
}
