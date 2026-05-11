package comunicacion.comunicaciones.dto;

import comunicacion.comunicaciones.models.TipoNotificacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionRequest {
    private Long usuarioId;
    private Long mensajeId;
    private String contenido;
    private TipoNotificacion tipo;
}
