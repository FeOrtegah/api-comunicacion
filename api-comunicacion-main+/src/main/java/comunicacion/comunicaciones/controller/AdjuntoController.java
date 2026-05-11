package comunicacion.comunicaciones.controller;

import comunicacion.comunicaciones.dto.AdjuntoRequest;
import static comunicacion.comunicaciones.mapper.RequestToEntityMapper.toAdjunto;
import comunicacion.comunicaciones.models.Adjunto;
import comunicacion.comunicaciones.service.AdjuntoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/adjuntos")
public class AdjuntoController {

    private final AdjuntoService adjuntoService;

    public AdjuntoController(AdjuntoService adjuntoService) {
        this.adjuntoService = adjuntoService;
    }

    @PostMapping
    public ResponseEntity<Adjunto> guardar(@RequestBody AdjuntoRequest adjuntoRequest) {
        return ResponseEntity.ok(adjuntoService.guardar(toAdjunto(adjuntoRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adjunto> obtener(@PathVariable Long id) {
        return adjuntoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mensaje/{mensajeId}")
    public ResponseEntity<List<Adjunto>> obtenerPorMensaje(@PathVariable Long mensajeId) {
        return ResponseEntity.ok(adjuntoService.obtenerPorMensaje(mensajeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        adjuntoService.eliminar(id);
        return ResponseEntity.ok().build();
    }

}
