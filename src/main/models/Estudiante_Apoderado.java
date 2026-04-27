package Comunicacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "estudiante_apoderado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante_Apoderado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Usuarios estudiante;

    @ManyToOne
    @JoinColumn(name = "apoderado_id", nullable = false)
    private Usuarios apoderado;

    @Column(length = 500)
    private String relacion; // e.g., "Padre", "Madre", "Tutor"
}
