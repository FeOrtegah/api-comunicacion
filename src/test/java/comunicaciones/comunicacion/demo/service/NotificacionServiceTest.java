package comunicaciones.comunicacion.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.Notificacion;
import comunicacion.comunicaciones.repository.NotificacionRepository;
import comunicacion.comunicaciones.service.NotificacionService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceTest {

    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    @Test
    void crear_ShouldReturnSavedNotificacion() {
        Notificacion notificacion = new Notificacion();
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacion);
        Notificacion resultado = notificacionService.crear(new Notificacion());
        assertNotNull(resultado);
        verify(notificacionRepository, times(1)).save(any(Notificacion.class));
    }

    @Test
    void obtenerPorId_ShouldReturnOptional() {
        Notificacion notificacion = new Notificacion();
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));
        Optional<Notificacion> resultado = notificacionService.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
    }

    @Test
    void obtenerNotificacionesUsuario_ShouldReturnList() {
        List<Notificacion> lista = Arrays.asList(new Notificacion(), new Notificacion());
        when(notificacionRepository.findByUsuarioIdOrderByFechaCreacionDesc(1L)).thenReturn(lista);
        List<Notificacion> resultado = notificacionService.obtenerNotificacionesUsuario(1L);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    void obtenerNoLeidas_ShouldReturnList() {
        List<Notificacion> lista = Arrays.asList(new Notificacion());
        when(notificacionRepository.findByUsuarioIdAndLeida(1L, false)).thenReturn(lista);
        List<Notificacion> resultado = notificacionService.obtenerNoLeidas(1L);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void marcarComoLeida_WhenNotificacionExists_ShouldSetLeidaTrueAndSave() {
        Notificacion notificacion = new Notificacion();
        notificacion.setLeida(false);
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacion);
        notificacionService.marcarComoLeida(1L);
        verify(notificacionRepository, times(1)).findById(1L);
        verify(notificacionRepository, times(1)).save(notificacion);
    }

    @Test
    void marcarComoLeida_WhenNotificacionDoesNotExist_ShouldNotSave() {
        when(notificacionRepository.findById(1L)).thenReturn(Optional.empty());
        notificacionService.marcarComoLeida(1L);
        verify(notificacionRepository, never()).save(any());
    }

    @Test
    void eliminar_ShouldInvokeRepositoryDelete() {
        doNothing().when(notificacionRepository).deleteById(1L);
        notificacionService.eliminar(1L);
        verify(notificacionRepository, times(1)).deleteById(1L);
    }
}