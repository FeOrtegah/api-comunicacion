package comunicaciones.comunicacion.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import comunicacion.comunicaciones.dto.PlantillaMensajeRequest;
import comunicacion.comunicaciones.models.PlantillaMensaje;
import comunicacion.comunicaciones.service.PlantillaMensajeService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import comunicacion.comunicaciones.controller.PlantillaMensajeController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlantillaMensajeControllerTest {

    @Mock private PlantillaMensajeService plantillaService;
    @InjectMocks private PlantillaMensajeController plantillaMensajeController;

    @Test void crear_ShouldReturnOkAndPlantilla() {
        PlantillaMensajeRequest request = new PlantillaMensajeRequest();
        PlantillaMensaje plantillaCreada = new PlantillaMensaje(); plantillaCreada.setId(1L);
        when(plantillaService.crear(any())).thenReturn(plantillaCreada);
        ResponseEntity<PlantillaMensaje> response = plantillaMensajeController.crear(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenExists_ShouldReturnPlantilla() {
        PlantillaMensaje plantilla = new PlantillaMensaje(); plantilla.setId(1L);
        when(plantillaService.obtenerPorId(1L)).thenReturn(Optional.of(plantilla));
        ResponseEntity<PlantillaMensaje> response = plantillaMensajeController.obtener(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenNotExists_ShouldReturnNotFound() {
        when(plantillaService.obtenerPorId(99L)).thenReturn(Optional.empty());
        ResponseEntity<PlantillaMensaje> response = plantillaMensajeController.obtener(99L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void obtenerPlantillasCreador_ShouldReturnList() {
        List<PlantillaMensaje> lista = Arrays.asList(new PlantillaMensaje(), new PlantillaMensaje());
        when(plantillaService.obtenerPlantillasCreador(100L)).thenReturn(lista);
        ResponseEntity<List<PlantillaMensaje>> response = plantillaMensajeController.obtenerPlantillasCreador(100L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void obtenerPlantillasPublicas_ShouldReturnList() {
        List<PlantillaMensaje> lista = Arrays.asList(new PlantillaMensaje());
        when(plantillaService.obtenerPlantillasPublicas()).thenReturn(lista);
        ResponseEntity<List<PlantillaMensaje>> response = plantillaMensajeController.obtenerPlantillasPublicas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test void actualizar_WhenExists_ShouldReturnUpdatedPlantilla() {
        PlantillaMensajeRequest request = new PlantillaMensajeRequest();
        PlantillaMensaje actualizada = new PlantillaMensaje(); actualizada.setId(1L);
        when(plantillaService.actualizar(eq(1L), any())).thenReturn(actualizada);
        ResponseEntity<PlantillaMensaje> response = plantillaMensajeController.actualizar(1L, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test void actualizar_WhenNotExists_ShouldReturnNotFound() {
        PlantillaMensajeRequest request = new PlantillaMensajeRequest();
        when(plantillaService.actualizar(eq(99L), any())).thenReturn(null);
        ResponseEntity<PlantillaMensaje> response = plantillaMensajeController.actualizar(99L, request);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void eliminar_ShouldCallServiceAndReturnOk() {
        doNothing().when(plantillaService).eliminar(1L);
        ResponseEntity<Void> response = plantillaMensajeController.eliminar(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(plantillaService, times(1)).eliminar(1L);
    }
}