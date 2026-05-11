package comunicacion.comunicaciones.controller;

import comunicacion.comunicaciones.dto.ConfiguracionNotificacionRequest;
import static comunicacion.comunicaciones.mapper.RequestToEntityMapper.*;
import comunicacion.comunicaciones.models.ConfiguracionNotificacion;
import comunicacion.comunicaciones.service.ConfiguracionNotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuracion-notificaciones")
public class ConfiguracionNotificacionController {

    private final ConfiguracionNotificacionService configuracionService;

    public ConfiguracionNotificacionController(ConfiguracionNotificacionService configuracionService) {
        this.configuracionService = configuracionService;
    }

    @PostMapping
    public ResponseEntity<ConfiguracionNotificacion> guardar(@RequestBody ConfiguracionNotificacionRequest configuracionRequest) {
        return ResponseEntity.ok(configuracionService.guardar(toConfiguracion(configuracionRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfiguracionNotificacion> obtener(@PathVariable Long id) {
        return configuracionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ConfiguracionNotificacion> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return configuracionService.obtenerPorUsuario(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfiguracionNotificacion> actualizar(@PathVariable Long id,
            @RequestBody ConfiguracionNotificacionRequest configuracionRequest) {
        ConfiguracionNotificacion actualizada = configuracionService.actualizar(id,
                toConfiguracion(configuracionRequest));
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        configuracionService.eliminar(id);
        return ResponseEntity.ok().build();
    }

}
