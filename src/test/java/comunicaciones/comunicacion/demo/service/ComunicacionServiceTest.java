package comunicaciones.comunicacion.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import comunicacion.comunicaciones.models.*;
import comunicacion.comunicaciones.repository.*;
import comunicacion.comunicaciones.service.ComunicacionService;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComunicacionServiceTest {

    @Mock private MensajeRepository mensajeRepository;
    @Mock private ConversacionRepository conversacionRepository;
    @Mock private NotificacionRepository notificacionRepository;
    @Mock private AdjuntoRepository adjuntoRepository;
    @Mock private GrupoChatRepository grupoChatRepository;
    @Mock private MensajeGrupalRepository mensajeGrupalRepository;
    @Mock private ConfiguracionNotificacionRepository configuracionRepository;
    @Mock private PlantillaMensajeRepository plantillaMensajeRepository;

    @InjectMocks private ComunicacionService comunicacionService;

    // --- MENSAJES ---
    @Test void enviarMensaje_ShouldReturnMensaje() {
        Mensaje m = new Mensaje();
        when(mensajeRepository.save(any(Mensaje.class))).thenReturn(m);
        assertNotNull(comunicacionService.enviarMensaje(new Mensaje()));
    }

    @Test void obtenerMensajesRecibidos_ShouldReturnList() {
        when(mensajeRepository.findByReceptorIdOrderByFechaEnvioDesc(1L)).thenReturn(Arrays.asList(new Mensaje()));
        assertEquals(1, comunicacionService.obtenerMensajesRecibidos(1L).size());
    }

    @Test void obtenerMensaje_ShouldReturnOptional() {
        when(mensajeRepository.findById(1L)).thenReturn(Optional.of(new Mensaje()));
        assertTrue(comunicacionService.obtenerMensaje(1L).isPresent());
    }

    @Test void marcarComoLeido_WhenMensajeExists_ShouldSaveTrue() {
        Mensaje m = new Mensaje();
        m.setLeido(false);
        when(mensajeRepository.findById(1L)).thenReturn(Optional.of(m));
        when(mensajeRepository.save(any(Mensaje.class))).thenReturn(m);

        comunicacionService.marcarComoLeido(1L);

        assertTrue(m.isLeido());
        verify(mensajeRepository, times(1)).save(m);
    }

    @Test void marcarComoLeido_WhenMensajeDoesNotExist_ShouldDoNothing() {
        when(mensajeRepository.findById(1L)).thenReturn(Optional.empty());
        comunicacionService.marcarComoLeido(1L);
        verify(mensajeRepository, never()).save(any());
    }

    @Test void crearConversacion_ShouldReturnConversacion() {
        Conversacion c = new Conversacion();
        when(conversacionRepository.save(any(Conversacion.class))).thenReturn(c);
        assertNotNull(comunicacionService.crearConversacion(new Conversacion()));
    }

    @Test void obtenerConversacion_ShouldReturnList() {
        when(mensajeRepository.findByConversacionId(1L)).thenReturn(Arrays.asList(new Mensaje()));
        assertEquals(1, comunicacionService.obtenerConversacion(1L).size());
    }

    @Test void obtenerConversacionesUsuario_ShouldReturnList() {
        when(conversacionRepository.findByParticipante1IdOrParticipante2Id(1L, 1L)).thenReturn(Arrays.asList(new Conversacion()));
        assertEquals(1, comunicacionService.obtenerConversacionesUsuario(1L).size());
    }

    @Test void crearNotificacion_ShouldReturnNotificacion() {
        Notificacion n = new Notificacion();
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(n);
        assertNotNull(comunicacionService.crearNotificacion(new Notificacion()));
    }

    @Test void obtenerNotificacionesUsuario_ShouldReturnList() {
        when(notificacionRepository.findByUsuarioIdOrderByFechaCreacionDesc(1L)).thenReturn(Arrays.asList(new Notificacion()));
        assertEquals(1, comunicacionService.obtenerNotificacionesUsuario(1L).size());
    }

    @Test void obtenerNotificacionesNoLeidas_ShouldReturnList() {
        when(notificacionRepository.findByUsuarioIdAndLeida(1L, false)).thenReturn(Arrays.asList(new Notificacion()));
        assertEquals(1, comunicacionService.obtenerNotificacionesNoLeidas(1L).size());
    }

    @Test void guardarAdjunto_ShouldReturnAdjunto() {
        Adjunto a = new Adjunto();
        when(adjuntoRepository.save(any(Adjunto.class))).thenReturn(a);
        assertNotNull(comunicacionService.guardarAdjunto(new Adjunto()));
    }

    @Test void obtenerAdjuntosMensaje_ShouldReturnList() {
        when(adjuntoRepository.findByMensajeId(1L)).thenReturn(Arrays.asList(new Adjunto()));
        assertEquals(1, comunicacionService.obtenerAdjuntosMensaje(1L).size());
    }

    @Test void crearGrupo_ShouldReturnGrupoChat() {
        GrupoChat g = new GrupoChat();
        when(grupoChatRepository.save(any(GrupoChat.class))).thenReturn(g);
        assertNotNull(comunicacionService.crearGrupo(new GrupoChat()));
    }

    @Test void obtenerGruposUsuario_ShouldReturnList() {
        when(grupoChatRepository.findByCreadorId(1L)).thenReturn(Arrays.asList(new GrupoChat()));
        assertEquals(1, comunicacionService.obtenerGruposUsuario(1L).size());
    }

    // --- MENSAJES GRUPALES ---
    @Test void enviarMensajeGrupal_ShouldReturnMensajeGrupal() {
        MensajeGrupal mg = new MensajeGrupal();
        when(mensajeGrupalRepository.save(any(MensajeGrupal.class))).thenReturn(mg);
        assertNotNull(comunicacionService.enviarMensajeGrupal(new MensajeGrupal()));
    }

    @Test void obtenerMensajesGrupo_ShouldReturnList() {
        when(mensajeGrupalRepository.findByGrupoIdOrderByFechaEnvioDesc(1L)).thenReturn(Arrays.asList(new MensajeGrupal()));
        assertEquals(1, comunicacionService.obtenerMensajesGrupo(1L).size());
    }

    @Test void guardarConfiguracion_ShouldReturnConfiguracion() {
        ConfiguracionNotificacion cn = new ConfiguracionNotificacion();
        when(configuracionRepository.save(any(ConfiguracionNotificacion.class))).thenReturn(cn);
        assertNotNull(comunicacionService.guardarConfiguracion(new ConfiguracionNotificacion()));
    }

    @Test void obtenerConfiguracion_ShouldReturnOptional() {
        when(configuracionRepository.findByUsuarioId(1L)).thenReturn(Optional.of(new ConfiguracionNotificacion()));
        assertTrue(comunicacionService.obtenerConfiguracion(1L).isPresent());
    }

    @Test void crearPlantilla_ShouldReturnPlantillaMensaje() {
        PlantillaMensaje p = new PlantillaMensaje();
        when(plantillaMensajeRepository.save(any(PlantillaMensaje.class))).thenReturn(p);
        assertNotNull(comunicacionService.crearPlantilla(new PlantillaMensaje()));
    }

    @Test void obtenerPlantillasUsuario_ShouldReturnList() {
        when(plantillaMensajeRepository.findByCreadorId(1L)).thenReturn(Arrays.asList(new PlantillaMensaje()));
        assertEquals(1, comunicacionService.obtenerPlantillasUsuario(1L).size());
    }

    @Test void obtenerPlantillasPublicas_ShouldReturnList() {
        when(plantillaMensajeRepository.findByPublica(true)).thenReturn(Arrays.asList(new PlantillaMensaje()));
        assertEquals(1, comunicacionService.obtenerPlantillasPublicas().size());
    }
}