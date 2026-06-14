package comunicaciones.comunicacion.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import comunicacion.comunicaciones.dto.GrupoChatRequest;
import comunicacion.comunicaciones.models.GrupoChat;
import comunicacion.comunicaciones.service.GrupoChatService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import comunicacion.comunicaciones.controller.GrupoChatController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GrupoChatControllerTest {

    @Mock private GrupoChatService grupoChatService;
    @InjectMocks private GrupoChatController grupoChatController;

    @Test void crear_ShouldReturnOkAndGrupoChat() {
        GrupoChatRequest request = new GrupoChatRequest();
        GrupoChat grupoCreado = new GrupoChat(); grupoCreado.setId(1L);
        when(grupoChatService.crear(any())).thenReturn(grupoCreado);
        ResponseEntity<GrupoChat> response = grupoChatController.crear(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenExists_ShouldReturnGrupoChat() {
        GrupoChat grupo = new GrupoChat(); grupo.setId(1L);
        when(grupoChatService.obtenerPorId(1L)).thenReturn(Optional.of(grupo));
        ResponseEntity<GrupoChat> response = grupoChatController.obtener(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenNotExists_ShouldReturnNotFound() {
        when(grupoChatService.obtenerPorId(99L)).thenReturn(Optional.empty());
        ResponseEntity<GrupoChat> response = grupoChatController.obtener(99L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void obtenerGruposCreador_ShouldReturnList() {
        List<GrupoChat> lista = Arrays.asList(new GrupoChat(), new GrupoChat());
        when(grupoChatService.obtenerGruposCreador(100L)).thenReturn(lista);
        ResponseEntity<List<GrupoChat>> response = grupoChatController.obtenerGruposCreador(100L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void obtenerActivos_ShouldReturnList() {
        List<GrupoChat> lista = Arrays.asList(new GrupoChat());
        when(grupoChatService.obtenerActivos()).thenReturn(lista);
        ResponseEntity<List<GrupoChat>> response = grupoChatController.obtenerActivos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test void actualizar_WhenExists_ShouldReturnUpdatedGrupoChat() {
        GrupoChatRequest request = new GrupoChatRequest();
        GrupoChat actualizado = new GrupoChat(); actualizado.setId(1L);
        when(grupoChatService.actualizar(eq(1L), any())).thenReturn(actualizado);
        ResponseEntity<GrupoChat> response = grupoChatController.actualizar(1L, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test void actualizar_WhenNotExists_ShouldReturnNotFound() {
        GrupoChatRequest request = new GrupoChatRequest();
        when(grupoChatService.actualizar(eq(99L), any())).thenReturn(null);
        ResponseEntity<GrupoChat> response = grupoChatController.actualizar(99L, request);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void desactivar_ShouldCallServiceAndReturnOk() {
        doNothing().when(grupoChatService).desactivar(1L);
        ResponseEntity<Void> response = grupoChatController.desactivar(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(grupoChatService, times(1)).desactivar(1L);
    }
}