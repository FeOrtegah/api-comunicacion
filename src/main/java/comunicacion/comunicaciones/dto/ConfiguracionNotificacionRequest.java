package comunicacion.comunicaciones.dto;

import comunicacion.comunicaciones.models.FrecuenciaNotificacion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionNotificacionRequest {

    @NotNull(message = "El usuarioId es obligatorio")
    private Long usuarioId;

    private boolean recibirMensajes;
    private boolean recibirNotificaciones;
    private boolean recibirAvisos;

    @NotNull(message = "La frecuencia de notificación es obligatoria")
    private FrecuenciaNotificacion frecuencia;

    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "El horario de inicio debe tener formato HH:mm")
    private String horarioInicio;

    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "El horario de fin debe tener formato HH:mm")
    private String horarioFin;
}
