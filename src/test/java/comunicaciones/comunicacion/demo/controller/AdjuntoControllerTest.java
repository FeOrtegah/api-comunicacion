package comunicaciones.comunicacion.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import comunicacion.comunicaciones.dto.AdjuntoRequest;
import comunicacion.comunicaciones.models.Adjunto;
import comunicacion.comunicaciones.service.AdjuntoService;
import comunicacion.comunicaciones.mapper.RequestToEntityMapper;
import comunicacion.comunicaciones.controller.AdjuntoController;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdjuntoControllerTest {
    @Mock private AdjuntoService adjuntoService;
    @InjectMocks private AdjuntoController adjuntoController;

    @Test void guardar_ShouldReturnOkAndAdjunto() {
        AdjuntoRequest request = new AdjuntoRequest();
        Adjunto adjuntoMapeado = new Adjunto();
        Adjunto adjuntoGuardado = new Adjunto(); adjuntoGuardado.setId(1L);

        try (MockedStatic<RequestToEntityMapper> mapperMock = mockStatic(RequestToEntityMapper.class)) {
            mapperMock.when(() -> RequestToEntityMapper.toAdjunto(request)).thenReturn(adjuntoMapeado);
            when(adjuntoService.guardar(adjuntoMapeado)).thenReturn(adjuntoGuardado);
            ResponseEntity<Adjunto> response = adjuntoController.guardar(request);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(1L, response.getBody().getId());
        }
    }

    @Test void obtener_WhenExists_ShouldReturnAdjunto() {
        Adjunto adjunto = new Adjunto(); adjunto.setId(1L);
        when(adjuntoService.obtenerPorId(1L)).thenReturn(Optional.of(adjunto));
        ResponseEntity<Adjunto> response = adjuntoController.obtener(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenNotExists_ShouldReturnNotFound() {
        when(adjuntoService.obtenerPorId(99L)).thenReturn(Optional.empty());
        ResponseEntity<Adjunto> response = adjuntoController.obtener(99L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void obtenerPorMensaje_ShouldReturnList() {
        Adjunto a1 = new Adjunto(); a1.setId(1L);
        Adjunto a2 = new Adjunto(); a2.setId(2L);
        List<Adjunto> lista = Arrays.asList(a1, a2);
        when(adjuntoService.obtenerPorMensaje(100L)).thenReturn(lista);
        ResponseEntity<List<Adjunto>> response = adjuntoController.obtenerPorMensaje(100L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void eliminar_ShouldCallServiceAndReturnOk() {
        doNothing().when(adjuntoService).eliminar(1L);
        ResponseEntity<Void> response = adjuntoController.eliminar(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(adjuntoService, times(1)).eliminar(1L);
    }
}
