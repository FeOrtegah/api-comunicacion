package comunicacion.comunicaciones.controller;

import comunicacion.comunicaciones.dto.GrupoChatRequest;
import static comunicacion.comunicaciones.mapper.RequestToEntityMapper.*;
import comunicacion.comunicaciones.models.GrupoChat;
import comunicacion.comunicaciones.service.GrupoChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/grupos")
public class GrupoChatController {

    private final GrupoChatService grupoChatService;

    public GrupoChatController(GrupoChatService grupoChatService) {
        this.grupoChatService = grupoChatService;
    }

    @PostMapping
    public ResponseEntity<GrupoChat> crear(@RequestBody GrupoChatRequest grupoRequest) {
        return ResponseEntity.ok(grupoChatService.crear(toGrupoChat(grupoRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoChat> obtener(@PathVariable Long id) {
        return grupoChatService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/creador/{creadorId}")
    public ResponseEntity<List<GrupoChat>> obtenerGruposCreador(@PathVariable Long creadorId) {
        return ResponseEntity.ok(grupoChatService.obtenerGruposCreador(creadorId));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<GrupoChat>> obtenerActivos() {
        return ResponseEntity.ok(grupoChatService.obtenerActivos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoChat> actualizar(@PathVariable Long id,
            @RequestBody GrupoChatRequest grupoRequest) {
        GrupoChat actualizado = grupoChatService.actualizar(id, toGrupoChat(grupoRequest));
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        grupoChatService.desactivar(id);
        return ResponseEntity.ok().build();
    }

}
