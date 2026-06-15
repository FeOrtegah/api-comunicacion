package comunicacion.comunicaciones.mapper;

import comunicacion.comunicaciones.dto.AdjuntoRequest;
import comunicacion.comunicaciones.dto.ConfiguracionNotificacionRequest;
import comunicacion.comunicaciones.dto.ConversacionRequest;
import comunicacion.comunicaciones.dto.GrupoChatRequest;
import comunicacion.comunicaciones.dto.MensajeGrupalRequest;
import comunicacion.comunicaciones.dto.MensajeRequest;
import comunicacion.comunicaciones.dto.NotificacionRequest;
import comunicacion.comunicaciones.dto.PlantillaMensajeRequest;
import comunicacion.comunicaciones.models.Adjunto;
import comunicacion.comunicaciones.models.ConfiguracionNotificacion;
import comunicacion.comunicaciones.models.Conversacion;
import comunicacion.comunicaciones.models.GrupoChat;
import comunicacion.comunicaciones.models.Mensaje;
import comunicacion.comunicaciones.models.MensajeGrupal;
import comunicacion.comunicaciones.models.Notificacion;
import comunicacion.comunicaciones.models.PlantillaMensaje;

public final class RequestToEntityMapper {

    private RequestToEntityMapper() {
        throw new UnsupportedOperationException("Mapper class");
    }

    public static Mensaje toMensaje(MensajeRequest request) {
        Mensaje mensaje = new Mensaje();
        mensaje.setEmisorId(request.getEmisorId());
        mensaje.setReceptorId(request.getReceptorId());
        mensaje.setConversacionId(request.getConversacionId());
        mensaje.setContenido(request.getContenido());
        mensaje.setAsunto(request.getAsunto());
        return mensaje;
    }

    public static Conversacion toConversacion(ConversacionRequest request) {
        Conversacion conversacion = new Conversacion();
        conversacion.setParticipante1Id(request.getParticipante1Id());
        conversacion.setParticipante2Id(request.getParticipante2Id());
        conversacion.setUltimaMensajeEn(request.getUltimaMensajeEn());
        conversacion.setTotalMensajes(request.getTotalMensajes());
        return conversacion;
    }

    public static Notificacion toNotificacion(NotificacionRequest request) {
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuarioId(request.getUsuarioId());
        notificacion.setMensajeId(request.getMensajeId());
        notificacion.setContenido(request.getContenido());
        notificacion.setTipo(request.getTipo());
        return notificacion;
    }

    public static Adjunto toAdjunto(AdjuntoRequest request) {
        Adjunto adjunto = new Adjunto();
        adjunto.setMensajeId(request.getMensajeId());
        adjunto.setNombreArchivo(request.getNombreArchivo());
        adjunto.setRuta(request.getRuta());
        adjunto.setTamanio(request.getTamanio());
        adjunto.setTipoArchivo(request.getTipoArchivo());
        return adjunto;
    }

    public static GrupoChat toGrupoChat(GrupoChatRequest request) {
        GrupoChat grupo = new GrupoChat();
        grupo.setNombre(request.getNombre());
        grupo.setDescripcion(request.getDescripcion());
        grupo.setCreadorId(request.getCreadorId());
        grupo.setMiembros(request.getMiembros());
        return grupo;
    }

    public static MensajeGrupal toMensajeGrupal(MensajeGrupalRequest request) {
        MensajeGrupal mensaje = new MensajeGrupal();
        mensaje.setGrupoId(request.getGrupoId());
        mensaje.setEmisorId(request.getEmisorId());
        mensaje.setContenido(request.getContenido());
        return mensaje;
    }

    public static ConfiguracionNotificacion toConfiguracion(ConfiguracionNotificacionRequest request) {
        ConfiguracionNotificacion configuracion = new ConfiguracionNotificacion();
        configuracion.setUsuarioId(request.getUsuarioId());
        configuracion.setRecibirMensajes(request.isRecibirMensajes());
        configuracion.setRecibirNotificaciones(request.isRecibirNotificaciones());
        configuracion.setRecibirAvisos(request.isRecibirAvisos());
        configuracion.setFrecuencia(request.getFrecuencia());
        configuracion.setHorarioInicio(request.getHorarioInicio());
        configuracion.setHorarioFin(request.getHorarioFin());
        return configuracion;
    }

    public static PlantillaMensaje toPlantilla(PlantillaMensajeRequest request) {
        PlantillaMensaje plantilla = new PlantillaMensaje();
        plantilla.setCreadorId(request.getCreadorId());
        plantilla.setNombre(request.getNombre());
        plantilla.setContenido(request.getContenido());
        plantilla.setDescripcion(request.getDescripcion());
        plantilla.setPublica(request.isPublica());
        return plantilla;
    }
}
