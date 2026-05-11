package comunicacion.comunicaciones.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "plantillas_mensaje")
@NoArgsConstructor
@AllArgsConstructor
public class PlantillaMensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long creadorId;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000)
    private String contenido;

    private String descripcion;
    private LocalDateTime fechaCreacion;
    private boolean publica;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.publica = false;
    }
}
