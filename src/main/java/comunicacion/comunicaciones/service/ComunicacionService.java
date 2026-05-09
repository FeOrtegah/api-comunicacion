package comunicacion.comunicaciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import comunicacion.comunicaciones.models.Adjunto;
import comunicacion.comunicaciones.models.ConfiguracionNotificacion;
import comunicacion.comunicaciones.models.Conversacion;
import comunicacion.comunicaciones.models.GrupoChat;
import comunicacion.comunicaciones.models.Mensaje;
import comunicacion.comunicaciones.models.MensajeGrupal;
import comunicacion.comunicaciones.models.Notificacion;
import comunicacion.comunicaciones.models.PlantillaMensaje;
import comunicacion.comunicaciones.repository.AdjuntoRepository;
import comunicacion.comunicaciones.repository.ConfiguracionNotificacionRepository;
import comunicacion.comunicaciones.repository.ConversacionRepository;
import comunicacion.comunicaciones.repository.GrupoChatRepository;
import comunicacion.comunicaciones.repository.MensajeGrupalRepository;
import comunicacion.comunicaciones.repository.MensajeRepository;
import comunicacion.comunicaciones.repository.NotificacionRepository;
import comunicacion.comunicaciones.repository.PlantillaMensajeRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunicacionService {

    private final MensajeRepository mensajeRepository;
    private final ConversacionRepository conversacionRepository;
    private final NotificacionRepository notificacionRepository;
    private final AdjuntoRepository adjuntoRepository;
    private final GrupoChatRepository grupoChatRepository;
    private final MensajeGrupalRepository mensajeGrupalRepository;
    private final ConfiguracionNotificacionRepository configuracionRepository;
    private final PlantillaMensajeRepository plantillaMensajeRepository;

    public ComunicacionService(MensajeRepository mensajeRepository,
                               ConversacionRepository conversacionRepository,
                               NotificacionRepository notificacionRepository,
                               AdjuntoRepository adjuntoRepository,
                               GrupoChatRepository grupoChatRepository,
                               MensajeGrupalRepository mensajeGrupalRepository,
                               ConfiguracionNotificacionRepository configuracionRepository,
                               PlantillaMensajeRepository plantillaMensajeRepository) {
        this.mensajeRepository = mensajeRepository;
        this.conversacionRepository = conversacionRepository;
        this.notificacionRepository = notificacionRepository;
        this.adjuntoRepository = adjuntoRepository;
        this.grupoChatRepository = grupoChatRepository;
        this.mensajeGrupalRepository = mensajeGrupalRepository;
        this.configuracionRepository = configuracionRepository;
        this.plantillaMensajeRepository = plantillaMensajeRepository;
    }

    public Mensaje enviarMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public List<Mensaje> obtenerMensajesRecibidos(Long receptorId) {
        return mensajeRepository.findByReceptorIdOrderByFechaEnvioDesc(receptorId);
    }

    public Optional<Mensaje> obtenerMensaje(Long id) {
        return mensajeRepository.findById(id);
    }

    public void marcarComoLeido(Long mensajeId) {
        Optional<Mensaje> mensajeOpt = mensajeRepository.findById(mensajeId);
        if (mensajeOpt.isPresent()) {
            Mensaje m = mensajeOpt.get();
            m.setLeido(true);
            mensajeRepository.save(m);
        }
    }

    // CONVERSACIONES
    public Conversacion crearConversacion(Conversacion conversacion) {
        return conversacionRepository.save(conversacion);
    }

    public List<Mensaje> obtenerConversacion(Long conversacionId){
        return mensajeRepository.findByConversacionId(conversacionId);
    }

    public List<Conversacion> obtenerConversacionesUsuario(Long usuarioId) {
        return conversacionRepository.findByParticipante1IdOrParticipante2Id(usuarioId, usuarioId);
    }

    // NOTIFICACIONES
    public Notificacion crearNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> obtenerNotificacionesUsuario(Long usuarioId) {
        return notificacionRepository.findByUsuarioIdOrderByFechaCreacionDesc(usuarioId);
    }

    public List<Notificacion> obtenerNotificacionesNoLeidas(Long usuarioId) {
        return notificacionRepository.findByUsuarioIdAndLeida(usuarioId, false);
    }

    // ADJUNTOS
    public Adjunto guardarAdjunto(Adjunto adjunto) {
        return adjuntoRepository.save(adjunto);
    }

    public List<Adjunto> obtenerAdjuntosMensaje(Long mensajeId) {
        return adjuntoRepository.findByMensajeId(mensajeId);
    }

    // GRUPOS CHAT
    public GrupoChat crearGrupo(GrupoChat grupo) {
        return grupoChatRepository.save(grupo);
    }

    public List<GrupoChat> obtenerGruposUsuario(Long usuarioId) {
        return grupoChatRepository.findByCreadorId(usuarioId);
    }

    // MENSAJES GRUPALES
    public MensajeGrupal enviarMensajeGrupal(MensajeGrupal mensajeGrupal) {
        return mensajeGrupalRepository.save(mensajeGrupal);
    }

    public List<MensajeGrupal> obtenerMensajesGrupo(Long grupoId) {
        return mensajeGrupalRepository.findByGrupoIdOrderByFechaEnvioDesc(grupoId);
    }

    // CONFIGURACIÓN
    public ConfiguracionNotificacion guardarConfiguracion(ConfiguracionNotificacion config) {
        return configuracionRepository.save(config);
    }

    public Optional<ConfiguracionNotificacion> obtenerConfiguracion(Long usuarioId) {
        return configuracionRepository.findByUsuarioId(usuarioId);
    }

    // PLANTILLAS   
    public PlantillaMensaje crearPlantilla(PlantillaMensaje plantilla) {
        return plantillaMensajeRepository.save(plantilla);
    }

    public List<PlantillaMensaje> obtenerPlantillasUsuario(Long usuarioId) {
        return plantillaMensajeRepository.findByCreadorId(usuarioId);
    }

    public List<PlantillaMensaje> obtenerPlantillasPublicas() {
        return plantillaMensajeRepository.findByPublica(true);
    }
}
