package comunicacion.comunicaciones.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversacionRequest {

    @NotNull(message = "El participante1Id es obligatorio")
    private Long participante1Id;

    @NotNull(message = "El participante2Id es obligatorio")
    private Long participante2Id;

    private LocalDateTime ultimaMensajeEn;
    private Integer totalMensajes;
}
