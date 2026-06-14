package comunicacion.comunicaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantillaMensajeRequest {

    @NotNull(message = "El creadorId es obligatorio")
    private Long creadorId;

    @NotBlank(message = "El nombre de la plantilla es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El contenido es obligatorio")
    @Size(max = 3000, message = "El contenido no puede superar 3000 caracteres")
    private String contenido;

    @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
    private String descripcion;

    private boolean publica;
}
