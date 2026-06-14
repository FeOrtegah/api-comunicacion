package comunicacion.comunicaciones.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import comunicacion.comunicaciones.dto.AdjuntoRequest;
import comunicacion.comunicaciones.dto.ConfiguracionNotificacionRequest;
import comunicacion.comunicaciones.dto.ConversacionRequest;
import comunicacion.comunicaciones.dto.GrupoChatRequest;
import comunicacion.comunicaciones.dto.MensajeGrupalRequest;
import comunicacion.comunicaciones.dto.MensajeRequest;
import comunicacion.comunicaciones.dto.NotificacionRequest;
import comunicacion.comunicaciones.dto.PlantillaMensajeRequest;
import static comunicacion.comunicaciones.mapper.RequestToEntityMapper.*;
import comunicacion.comunicaciones.models.Adjunto;
import comunicacion.comunicaciones.models.ConfiguracionNotificacion;
import comunicacion.comunicaciones.models.Conversacion;
import comunicacion.comunicaciones.models.GrupoChat;
import comunicacion.comunicaciones.models.Mensaje;
import comunicacion.comunicaciones.models.MensajeGrupal;
import comunicacion.comunicaciones.models.Notificacion;
import comunicacion.comunicaciones.models.PlantillaMensaje;
import comunicacion.comunicaciones.service.ComunicacionService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/comunicacion")
public class ComunicacionController {

    private final ComunicacionService comunicacionService;

    @Autowired
        private javax.sql.DataSource dataSource;
    
    @GetMapping("/fix-sequence")
    public ResponseEntity<String> fixSequence() throws Exception {
        try (var conn = dataSource.getConnection();
            var stmt = conn.createStatement()) {
            stmt.execute("SELECT setval(pg_get_serial_sequence('comunicacion.conversaciones', 'id'), (SELECT MAX(id) FROM comunicacion.conversaciones))");
            stmt.execute("SELECT setval(pg_get_serial_sequence('comunicacion.mensajes', 'id'), (SELECT MAX(id) FROM comunicacion.mensajes))");
        }
        return ResponseEntity.ok("ok");
    }

    public ComunicacionController(ComunicacionService comunicacionService) {
        this.comunicacionService = comunicacionService;
    }

    @PostMapping("/mensajes")
    public ResponseEntity<Mensaje> enviarMensaje(@Valid @RequestBody MensajeRequest mensajeRequest) {
        return ResponseEntity.ok(comunicacionService.enviarMensaje(toMensaje(mensajeRequest)));
    }

    @GetMapping("/mensajes/entrada/{usuarioId}")
    public ResponseEntity<List<Mensaje>> bandejaEntrada(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comunicacionService.obtenerMensajesRecibidos(usuarioId));
    }

    @PatchMapping("/mensajes/{mensajeId}/leer")
    public ResponseEntity<Void> marcarComoLeido(@PathVariable("mensajeId") Long mensajeId) {
        comunicacionService.marcarComoLeido(mensajeId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/conversaciones")
    public ResponseEntity<Conversacion> crearConversacion(@Valid @RequestBody ConversacionRequest conversacionRequest) {
        return ResponseEntity.ok(comunicacionService.crearConversacion(toConversacion(conversacionRequest)));
    }

    @GetMapping("/conversaciones/{usuarioId}")
    public ResponseEntity<List<Conversacion>> obtenerConversaciones(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comunicacionService.obtenerConversacionesUsuario(usuarioId));
    }

    @GetMapping("/conversaciones/{id}/mensajes")
    public ResponseEntity<List<Mensaje>> obtenerConversacion(@PathVariable Long id) {
        return ResponseEntity.ok(comunicacionService.obtenerConversacion(id));
    }

    @PostMapping("/notificaciones")
    public ResponseEntity<Notificacion> crearNotificacion(@Valid @RequestBody NotificacionRequest notificacionRequest) {
        return ResponseEntity.ok(comunicacionService.crearNotificacion(toNotificacion(notificacionRequest)));
    }

    @GetMapping("/notificaciones/{usuarioId}")
    public ResponseEntity<List<Notificacion>> obtenerNotificaciones(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comunicacionService.obtenerNotificacionesUsuario(usuarioId));
    }

    @GetMapping("/notificaciones/{usuarioId}/no-leidas")
    public ResponseEntity<List<Notificacion>> obtenerNoLeidas(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comunicacionService.obtenerNotificacionesNoLeidas(usuarioId));
    }

    @PostMapping("/adjuntos")
    public ResponseEntity<Adjunto> guardarAdjunto(@Valid @RequestBody AdjuntoRequest adjuntoRequest) {
        return ResponseEntity.ok(comunicacionService.guardarAdjunto(toAdjunto(adjuntoRequest)));
    }

    @GetMapping("/adjuntos/mensaje/{mensajeId}")
    public ResponseEntity<List<Adjunto>> obtenerAdjuntos(@PathVariable Long mensajeId) {
        return ResponseEntity.ok(comunicacionService.obtenerAdjuntosMensaje(mensajeId));
    }

    @PostMapping("/grupos")
    public ResponseEntity<GrupoChat> crearGrupo(@Valid @RequestBody GrupoChatRequest grupoRequest) {
        return ResponseEntity.ok(comunicacionService.crearGrupo(toGrupoChat(grupoRequest)));
    }

    @GetMapping("/grupos/{usuarioId}")
    public ResponseEntity<List<GrupoChat>> obtenerGrupos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comunicacionService.obtenerGruposUsuario(usuarioId));
    }

    @PostMapping("/mensajes-grupales")
    public ResponseEntity<MensajeGrupal> enviarMensajeGrupal(@Valid @RequestBody MensajeGrupalRequest mensajeRequest) {
        return ResponseEntity.ok(comunicacionService.enviarMensajeGrupal(toMensajeGrupal(mensajeRequest)));
    }

    @GetMapping("/mensajes-grupales/grupo/{grupoId}")
    public ResponseEntity<List<MensajeGrupal>> obtenerMensajesGrupo(@PathVariable Long grupoId) {
        return ResponseEntity.ok(comunicacionService.obtenerMensajesGrupo(grupoId));
    }

    @PostMapping("/configuracion")
    public ResponseEntity<ConfiguracionNotificacion> guardarConfiguracion(
            @Valid @RequestBody ConfiguracionNotificacionRequest configRequest) {
        return ResponseEntity.ok(comunicacionService.guardarConfiguracion(toConfiguracion(configRequest)));
    }

    @GetMapping("/configuracion/{usuarioId}")
    public ResponseEntity<ConfiguracionNotificacion> obtenerConfiguracion(@PathVariable Long usuarioId) {
        return comunicacionService.obtenerConfiguracion(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/plantillas")
    public ResponseEntity<PlantillaMensaje> crearPlantilla(@Valid @RequestBody PlantillaMensajeRequest plantillaRequest) {
        return ResponseEntity.ok(comunicacionService.crearPlantilla(toPlantilla(plantillaRequest)));
    }

    @GetMapping("/plantillas/{usuarioId}")
    public ResponseEntity<List<PlantillaMensaje>> obtenerPlantillas(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comunicacionService.obtenerPlantillasUsuario(usuarioId));
    }

    @GetMapping("/plantillas/publicas")
    public ResponseEntity<List<PlantillaMensaje>> obtenerPlantillasPublicas() {
        return ResponseEntity.ok(comunicacionService.obtenerPlantillasPublicas());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }
}
