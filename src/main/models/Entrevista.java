package Comunicacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entrevistas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(length = 1000)
    private String notas;

    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = false)
    private Usuarios profesor;

    @ManyToOne
    @JoinColumn(name = "apoderado_id", nullable = false)
    private Usuarios apoderado;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Usuarios estudiante;
}
