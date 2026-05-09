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
@Table(name = "mensajes")
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long emisorId;
    private Long receptorId;
    private Long conversacionId;

    @Column(length = 1000)
    private String contenido;
    
    private String asunto;
    private LocalDateTime fechaEnvio;
    private boolean leido;

    @PrePersist
    protected void onCreate() {
        fechaEnvio = LocalDateTime.now();
        this.leido = false;
    }
}
