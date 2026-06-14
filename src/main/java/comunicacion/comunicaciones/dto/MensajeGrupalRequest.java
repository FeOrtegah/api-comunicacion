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
public class MensajeGrupalRequest {

    @NotNull(message = "El grupoId es obligatorio")
    private Long grupoId;

    @NotNull(message = "El emisorId es obligatorio")
    private Long emisorId;

    @NotBlank(message = "El contenido del mensaje es obligatorio")
    @Size(max = 2000, message = "El contenido no puede superar 2000 caracteres")
    private String contenido;
}
