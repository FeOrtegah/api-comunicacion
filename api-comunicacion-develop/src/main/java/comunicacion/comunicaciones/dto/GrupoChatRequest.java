package comunicacion.comunicaciones.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoChatRequest {
    private String nombre;
    private String descripcion;
    private Long creadorId;
    private List<Long> miembros;
}
