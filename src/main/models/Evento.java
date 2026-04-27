package Comunicacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    private String lugar;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso; // Opcional, si es específico para un curso

    @ManyToOne
    @JoinColumn(name = "organizador_id")
    private Usuarios organizador; // Quien organiza el evento
}
