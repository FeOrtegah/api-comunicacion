package comunicacion.comunicaciones.controller;

import comunicacion.comunicaciones.dto.MensajeGrupalRequest;
import static comunicacion.comunicaciones.mapper.RequestToEntityMapper.*;
import comunicacion.comunicaciones.models.MensajeGrupal;
import comunicacion.comunicaciones.service.MensajeGrupalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mensajes-grupales")
public class MensajeGrupalController {

    private final MensajeGrupalService mensajeGrupalService;

    public MensajeGrupalController(MensajeGrupalService mensajeGrupalService) {
        this.mensajeGrupalService = mensajeGrupalService;
    }

    @PostMapping
    public ResponseEntity<MensajeGrupal> enviar(@RequestBody MensajeGrupalRequest mensajeRequest) {
        return ResponseEntity.ok(mensajeGrupalService.enviar(toMensajeGrupal(mensajeRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeGrupal> obtener(@PathVariable Long id) {
        return mensajeGrupalService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<List<MensajeGrupal>> obtenerMensajesGrupo(@PathVariable Long grupoId) {
        return ResponseEntity.ok(mensajeGrupalService.obtenerMensajesGrupo(grupoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mensajeGrupalService.eliminar(id);
        return ResponseEntity.ok().build();
    }

}
