package ua.kiev.cote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class MeasurementResponse {
    private List<MeasurementDTO> measurements;
}
