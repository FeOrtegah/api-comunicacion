package comunicacion.comunicaciones.controller;

import comunicacion.comunicaciones.dto.ConversacionRequest;
import static comunicacion.comunicaciones.mapper.RequestToEntityMapper.toConversacion;
import comunicacion.comunicaciones.models.Conversacion;
import comunicacion.comunicaciones.service.ConversacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/conversaciones")
public class ConversacionController {

    private final ConversacionService conversacionService;

    public ConversacionController(ConversacionService conversacionService) {
        this.conversacionService = conversacionService;
    }

    @PostMapping
    public ResponseEntity<Conversacion> crear(@RequestBody ConversacionRequest conversacionRequest) {
        return ResponseEntity.ok(conversacionService.crear(toConversacion(conversacionRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conversacion> obtener(@PathVariable Long id) {
        return conversacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Conversacion>> obtenerConversacionesUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(conversacionService.obtenerConversacionesUsuario(usuarioId));
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Conversacion>> obtenerActivas() {
        return ResponseEntity.ok(conversacionService.obtenerActivas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conversacion> actualizar(@PathVariable Long id,
            @RequestBody ConversacionRequest conversacionRequest) {
        Conversacion actualizada = conversacionService.actualizar(id, toConversacion(conversacionRequest));
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        conversacionService.desactivar(id);
        return ResponseEntity.ok().build();
    }

}