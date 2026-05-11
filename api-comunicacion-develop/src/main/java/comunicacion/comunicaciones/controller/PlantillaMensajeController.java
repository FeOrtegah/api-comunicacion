package comunicacion.comunicaciones.controller;

import comunicacion.comunicaciones.dto.PlantillaMensajeRequest;
import static comunicacion.comunicaciones.mapper.RequestToEntityMapper.*;
import comunicacion.comunicaciones.models.PlantillaMensaje;
import comunicacion.comunicaciones.service.PlantillaMensajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/plantillas")
public class PlantillaMensajeController {

    private final PlantillaMensajeService plantillaService;

    public PlantillaMensajeController(PlantillaMensajeService plantillaService) {
        this.plantillaService = plantillaService;
    }

    @PostMapping
    public ResponseEntity<PlantillaMensaje> crear(@RequestBody PlantillaMensajeRequest plantillaRequest) {
        return ResponseEntity.ok(plantillaService.crear(toPlantilla(plantillaRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantillaMensaje> obtener(@PathVariable Long id) {
        return plantillaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/creador/{creadorId}")
    public ResponseEntity<List<PlantillaMensaje>> obtenerPlantillasCreador(@PathVariable Long creadorId) {
        return ResponseEntity.ok(plantillaService.obtenerPlantillasCreador(creadorId));
    }

    @GetMapping("/publicas")
    public ResponseEntity<List<PlantillaMensaje>> obtenerPlantillasPublicas() {
        return ResponseEntity.ok(plantillaService.obtenerPlantillasPublicas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantillaMensaje> actualizar(@PathVariable Long id,
            @RequestBody PlantillaMensajeRequest plantillaRequest) {
        PlantillaMensaje actualizada = plantillaService.actualizar(id, toPlantilla(plantillaRequest));
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        plantillaService.eliminar(id);
        return ResponseEntity.ok().build();
    }

}
