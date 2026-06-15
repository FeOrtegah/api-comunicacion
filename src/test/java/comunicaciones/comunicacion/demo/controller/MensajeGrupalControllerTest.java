package comunicaciones.comunicacion.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import comunicacion.comunicaciones.dto.MensajeGrupalRequest;
import comunicacion.comunicaciones.models.MensajeGrupal;
import comunicacion.comunicaciones.service.MensajeGrupalService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import comunicacion.comunicaciones.controller.MensajeGrupalController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MensajeGrupalControllerTest {

    @Mock private MensajeGrupalService mensajeGrupalService;
    @InjectMocks private MensajeGrupalController mensajeGrupalController;
    @Test void enviar_ShouldReturnOkAndMensajeGrupal() {
        MensajeGrupalRequest request = new MensajeGrupalRequest();
        MensajeGrupal mensajeEnviado = new MensajeGrupal(); mensajeEnviado.setId(1L);
        when(mensajeGrupalService.enviar(any())).thenReturn(mensajeEnviado);
        ResponseEntity<MensajeGrupal> response = mensajeGrupalController.enviar(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenExists_ShouldReturnMensajeGrupal() {
        MensajeGrupal mensaje = new MensajeGrupal(); mensaje.setId(1L);
        when(mensajeGrupalService.obtenerPorId(1L)).thenReturn(Optional.of(mensaje));
        ResponseEntity<MensajeGrupal> response = mensajeGrupalController.obtener(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenNotExists_ShouldReturnNotFound() {
        when(mensajeGrupalService.obtenerPorId(99L)).thenReturn(Optional.empty());
        ResponseEntity<MensajeGrupal> response = mensajeGrupalController.obtener(99L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void obtenerMensajesGrupo_ShouldReturnList() {
        List<MensajeGrupal> lista = Arrays.asList(new MensajeGrupal(), new MensajeGrupal());
        when(mensajeGrupalService.obtenerMensajesGrupo(100L)).thenReturn(lista);
        ResponseEntity<List<MensajeGrupal>> response = mensajeGrupalController.obtenerMensajesGrupo(100L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void eliminar_ShouldCallServiceAndReturnOk() {
        doNothing().when(mensajeGrupalService).eliminar(1L);
        ResponseEntity<Void> response = mensajeGrupalController.eliminar(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(mensajeGrupalService, times(1)).eliminar(1L);
    }
}