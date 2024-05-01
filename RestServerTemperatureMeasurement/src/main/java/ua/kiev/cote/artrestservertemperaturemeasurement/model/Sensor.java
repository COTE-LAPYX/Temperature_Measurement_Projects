package ua.kiev.cote.artrestservertemperaturemeasurement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Sensor")
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 3, max = 50, message = "Название сенсора должно быть от 3 до 50 символов")
    private String title;
}
