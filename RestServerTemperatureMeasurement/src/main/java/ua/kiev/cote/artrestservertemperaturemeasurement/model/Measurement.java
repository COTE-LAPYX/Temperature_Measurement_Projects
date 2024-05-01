package ua.kiev.cote.artrestservertemperaturemeasurement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Min(-60)
    @Max(60)
    private Double value;
    @NotNull
    private boolean raining;
    @Column(name = "date_and_time")
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "Sensor", referencedColumnName = "title")
    private Sensor sensor;
}
