package comunicacion.comunicaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeGrupalRequest {
    private Long grupoId;
    private Long emisorId;
    private String contenido;
}
