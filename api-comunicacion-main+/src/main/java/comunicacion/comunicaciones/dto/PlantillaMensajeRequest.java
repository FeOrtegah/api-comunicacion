package comunicacion.comunicaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantillaMensajeRequest {
    private Long creadorId;
    private String nombre;
    private String contenido;
    private String descripcion;
    private boolean publica;
}
