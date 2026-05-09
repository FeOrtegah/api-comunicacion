package comunicacion.comunicaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdjuntoRequest {
    private Long mensajeId;
    private String nombreArchivo;
    private String ruta;
    private Long tamanio;
    private String tipoArchivo;
}
