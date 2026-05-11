package comunicacion.comunicaciones.models;

import java.time.LocalDateTime;

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
@Table(name = "conversaciones")
@NoArgsConstructor
@AllArgsConstructor
public class Conversacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long participante1Id;
    private Long participante2Id;

    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaMensajeEn;
    private Integer totalMensajes;
    private boolean activa;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.activa = true;
        this.totalMensajes = 0;
    }
}
