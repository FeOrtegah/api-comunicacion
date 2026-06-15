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
public class AdjuntoRequest {

    @NotNull(message = "El mensajeId es obligatorio")
    private Long mensajeId;

    @NotBlank(message = "El nombre del archivo es obligatorio")
    @Size(max = 255, message = "El nombre del archivo no puede superar 255 caracteres")
    private String nombreArchivo;

    @NotBlank(message = "La ruta del archivo es obligatoria")
    private String ruta;

    @NotNull(message = "El tamaño del archivo es obligatorio")
    private Long tamanio;

    @NotBlank(message = "El tipo de archivo es obligatorio")
    @Size(max = 100, message = "El tipo de archivo no puede superar 100 caracteres")
    private String tipoArchivo;
}
//a
