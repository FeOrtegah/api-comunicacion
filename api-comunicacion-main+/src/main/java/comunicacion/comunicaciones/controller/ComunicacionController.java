package comunicacion.comunicaciones.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/api/comunicacion")
public class ComunicacionController {

    private final ComunicacionService comunicacionService;
    
    public ComunicacionController(ComunicacionService comunicacionService) {
        this.comunicacionService = comunicacionService;
    }

    // MENSAJES
    @PostMapping("/mensajes")
    public ResponseEntity<Mensaje> enviarMensaje(@RequestBody MensajeRequest mensajeRequest) {
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

    // CONVERSACIONES
    @PostMapping("/conversaciones")
    public ResponseEntity<Conversacion> crearConversacion(@RequestBody ConversacionRequest conversacionRequest) {
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

    // NOTIFICACIONES
    @PostMapping("/notificaciones")
    public ResponseEntity<Notificacion> crearNotificacion(@RequestBody NotificacionRequest notificacionRequest) {
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

    // ADJUNTOS
    @PostMapping("/adjuntos")
    public ResponseEntity<Adjunto> guardarAdjunto(@RequestBody AdjuntoRequest adjuntoRequest) {
        return ResponseEntity.ok(comunicacionService.guardarAdjunto(toAdjunto(adjuntoRequest)));
    }

    @GetMapping("/adjuntos/mensaje/{mensajeId}")
    public ResponseEntity<List<Adjunto>> obtenerAdjuntos(@PathVariable Long mensajeId) {
        return ResponseEntity.ok(comunicacionService.obtenerAdjuntosMensaje(mensajeId));
    }

    // GRUPOS
    @PostMapping("/grupos")
    public ResponseEntity<GrupoChat> crearGrupo(@RequestBody GrupoChatRequest grupoRequest) {
        return ResponseEntity.ok(comunicacionService.crearGrupo(toGrupoChat(grupoRequest)));
    }

    @GetMapping("/grupos/{usuarioId}")
    public ResponseEntity<List<GrupoChat>> obtenerGrupos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comunicacionService.obtenerGruposUsuario(usuarioId));
    }

    // MENSAJES GRUPALES
    @PostMapping("/mensajes-grupales")
    public ResponseEntity<MensajeGrupal> enviarMensajeGrupal(@RequestBody MensajeGrupalRequest mensajeRequest) {
        return ResponseEntity.ok(comunicacionService.enviarMensajeGrupal(toMensajeGrupal(mensajeRequest)));
    }

    @GetMapping("/mensajes-grupales/grupo/{grupoId}")
    public ResponseEntity<List<MensajeGrupal>> obtenerMensajesGrupo(@PathVariable Long grupoId) {
        return ResponseEntity.ok(comunicacionService.obtenerMensajesGrupo(grupoId));
    }

    // CONFIGURACIÓN
    @PostMapping("/configuracion")
    public ResponseEntity<ConfiguracionNotificacion> guardarConfiguracion(
            @RequestBody ConfiguracionNotificacionRequest configRequest) {
        return ResponseEntity.ok(comunicacionService.guardarConfiguracion(toConfiguracion(configRequest)));
    }

    @GetMapping("/configuracion/{usuarioId}")
    public ResponseEntity<ConfiguracionNotificacion> obtenerConfiguracion(@PathVariable Long usuarioId) {
        return comunicacionService.obtenerConfiguracion(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PLANTILLAS
    @PostMapping("/plantillas")
    public ResponseEntity<PlantillaMensaje> crearPlantilla(@RequestBody PlantillaMensajeRequest plantillaRequest) {
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

}