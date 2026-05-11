package comunicacion.comunicaciones.dto;

import comunicacion.comunicaciones.models.FrecuenciaNotificacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionNotificacionRequest {
    private Long usuarioId;
    private boolean recibirMensajes;
    private boolean recibirNotificaciones;
    private boolean recibirAvisos;
    private FrecuenciaNotificacion frecuencia;
    private String horarioInicio;
    private String horarioFin;
}
