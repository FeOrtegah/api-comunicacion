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
@Table(name = "adjuntos")
@NoArgsConstructor
@AllArgsConstructor
public class Adjunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mensajeId;

    @Column(nullable = false)
    private String nombreArchivo;

    private String ruta;
    private Long tamanio;
    private String tipoArchivo;
    private LocalDateTime fechaCarga;

    @PrePersist
    protected void onCreate() {
        this.fechaCarga = LocalDateTime.now();
    }
}
