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
public class MensajeRequest {

    @NotNull(message = "El emisorId es obligatorio")
    private Long emisorId;

    @NotNull(message = "El receptorId es obligatorio")
    private Long receptorId;

    private Long conversacionId;

    @NotBlank(message = "El contenido del mensaje es obligatorio")
    @Size(max = 2000, message = "El contenido no puede superar 2000 caracteres")
    private String contenido;

    @Size(max = 200, message = "El asunto no puede superar 200 caracteres")
    private String asunto;
}
