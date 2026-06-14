package comunicaciones.comunicacion.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import comunicacion.comunicaciones.dto.ConversacionRequest;
import comunicacion.comunicaciones.models.Conversacion;
import comunicacion.comunicaciones.service.ConversacionService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import comunicacion.comunicaciones.controller.ConversacionController;

@ExtendWith(MockitoExtension.class)
class ConversacionControllerTest {

    @Mock private ConversacionService conversacionService;
    @InjectMocks private ConversacionController conversacionController;

    @Test void crear_ShouldReturnOkAndConversacion() {
        ConversacionRequest request = new ConversacionRequest();
        Conversacion conversacionCreada = new Conversacion(); conversacionCreada.setId(1L);
        when(conversacionService.crear(any())).thenReturn(conversacionCreada);
        ResponseEntity<Conversacion> response = conversacionController.crear(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenExists_ShouldReturnConversacion() {
        Conversacion conversacion = new Conversacion(); conversacion.setId(1L);
        when(conversacionService.obtenerPorId(1L)).thenReturn(Optional.of(conversacion));
        ResponseEntity<Conversacion> response = conversacionController.obtener(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenNotExists_ShouldReturnNotFound() {
        when(conversacionService.obtenerPorId(99L)).thenReturn(Optional.empty());
        ResponseEntity<Conversacion> response = conversacionController.obtener(99L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void obtenerConversacionesUsuario_ShouldReturnList() {
        List<Conversacion> lista = Arrays.asList(new Conversacion(), new Conversacion());
        when(conversacionService.obtenerConversacionesUsuario(100L)).thenReturn(lista);
        ResponseEntity<List<Conversacion>> response = conversacionController.obtenerConversacionesUsuario(100L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void obtenerActivas_ShouldReturnList() {
        List<Conversacion> lista = Arrays.asList(new Conversacion());
        when(conversacionService.obtenerActivas()).thenReturn(lista);
        ResponseEntity<List<Conversacion>> response = conversacionController.obtenerActivas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test void actualizar_WhenExists_ShouldReturnUpdatedConversacion() {
        ConversacionRequest request = new ConversacionRequest();
        Conversacion actualizada = new Conversacion(); actualizada.setId(1L);
        when(conversacionService.actualizar(eq(1L), any())).thenReturn(actualizada);
        ResponseEntity<Conversacion> response = conversacionController.actualizar(1L, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test void actualizar_WhenNotExists_ShouldReturnNotFound() {
        ConversacionRequest request = new ConversacionRequest();
        when(conversacionService.actualizar(eq(99L), any())).thenReturn(null);
        ResponseEntity<Conversacion> response = conversacionController.actualizar(99L, request);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void desactivar_ShouldCallServiceAndReturnOk() {
        doNothing().when(conversacionService).desactivar(1L);
        ResponseEntity<Void> response = conversacionController.desactivar(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(conversacionService, times(1)).desactivar(1L);
    }
}
