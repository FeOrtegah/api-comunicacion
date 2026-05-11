package comunicacion.comunicaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeRequest {
    private Long emisorId;
    private Long receptorId;
    private Long conversacionId;
    private String contenido;
    private String asunto;
}
