package comunicacion.comunicaciones.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversacionRequest {
    private Long participante1Id;
    private Long participante2Id;
    private LocalDateTime ultimaMensajeEn;
    private Integer totalMensajes;
}
