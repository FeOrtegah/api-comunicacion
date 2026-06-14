package comunicaciones.comunicacion.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import comunicacion.comunicaciones.dto.NotificacionRequest;
import comunicacion.comunicaciones.models.Notificacion;
import comunicacion.comunicaciones.service.NotificacionService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import comunicacion.comunicaciones.controller.NotificacionController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacionControllerTest {

    @Mock private NotificacionService notificacionService;
    @InjectMocks private NotificacionController notificacionController;

    @Test void crear_ShouldReturnOkAndNotificacion() {
        NotificacionRequest request = new NotificacionRequest();
        Notificacion notificacionCreada = new Notificacion(); notificacionCreada.setId(1L);
        when(notificacionService.crear(any())).thenReturn(notificacionCreada);
        ResponseEntity<Notificacion> response = notificacionController.crear(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenExists_ShouldReturnNotificacion() {
        Notificacion notificacion = new Notificacion(); notificacion.setId(1L);
        when(notificacionService.obtenerPorId(1L)).thenReturn(Optional.of(notificacion));
        ResponseEntity<Notificacion> response = notificacionController.obtener(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenNotExists_ShouldReturnNotFound() {
        when(notificacionService.obtenerPorId(99L)).thenReturn(Optional.empty());
        ResponseEntity<Notificacion> response = notificacionController.obtener(99L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void obtenerNotificaciones_ShouldReturnList() {
        List<Notificacion> lista = Arrays.asList(new Notificacion(), new Notificacion());
        when(notificacionService.obtenerNotificacionesUsuario(100L)).thenReturn(lista);
        ResponseEntity<List<Notificacion>> response = notificacionController.obtenerNotificaciones(100L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void obtenerNoLeidas_ShouldReturnList() {
        List<Notificacion> lista = Arrays.asList(new Notificacion());
        when(notificacionService.obtenerNoLeidas(100L)).thenReturn(lista);
        ResponseEntity<List<Notificacion>> response = notificacionController.obtenerNoLeidas(100L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test void marcarComoLeida_ShouldCallServiceAndReturnOk() {
        doNothing().when(notificacionService).marcarComoLeida(1L);
        ResponseEntity<Void> response = notificacionController.marcarComoLeida(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(notificacionService, times(1)).marcarComoLeida(1L);
    }

    @Test void eliminar_ShouldCallServiceAndReturnOk() {
        doNothing().when(notificacionService).eliminar(1L);
        ResponseEntity<Void> response = notificacionController.eliminar(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(notificacionService, times(1)).eliminar(1L);
    }
}