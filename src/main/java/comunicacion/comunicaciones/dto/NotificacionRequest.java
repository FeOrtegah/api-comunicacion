package comunicacion.comunicaciones.dto;

import comunicacion.comunicaciones.models.TipoNotificacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionRequest {

    @NotNull(message = "El usuarioId es obligatorio")
    private Long usuarioId;

    private Long mensajeId;

    @NotBlank(message = "El contenido de la notificación es obligatorio")
    @Size(max = 500, message = "El contenido no puede superar 500 caracteres")
    private String contenido;

    @NotNull(message = "El tipo de notificación es obligatorio")
    private TipoNotificacion tipo;
}
