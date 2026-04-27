package Comunicacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anotaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anotacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuarios autor; // Profesor que hace la anotación

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Usuarios estudiante; // Estudiante al que se refiere la anotación
}
