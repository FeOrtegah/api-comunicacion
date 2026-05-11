package comunicacion.comunicaciones.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "configuraciones_notificacion")
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionNotificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private boolean recibirMensajes;
    private boolean recibirNotificaciones;
    private boolean recibirAvisos;

    @Enumerated(EnumType.STRING)
    private FrecuenciaNotificacion frecuencia;

    private String horarioInicio;
    private String horarioFin;

    @PostPersist
    protected void onCreate() {
        if (this.frecuencia == null) {
            this.frecuencia = FrecuenciaNotificacion.INMEDIATA;
        }
    }
}
