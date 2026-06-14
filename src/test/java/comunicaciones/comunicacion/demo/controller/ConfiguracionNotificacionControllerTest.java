package comunicaciones.comunicacion.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import comunicacion.comunicaciones.dto.ConfiguracionNotificacionRequest;
import comunicacion.comunicaciones.models.ConfiguracionNotificacion;
import comunicacion.comunicaciones.service.ConfiguracionNotificacionService;
import java.util.Optional;
import comunicacion.comunicaciones.controller.ConfiguracionNotificacionController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ConfiguracionNotificacionControllerTest {

    @Mock private ConfiguracionNotificacionService configuracionService;
    @InjectMocks private ConfiguracionNotificacionController configuracionNotificacionController;

    @Test void guardar_ShouldReturnOkAndConfiguracion() {
        ConfiguracionNotificacionRequest request = new ConfiguracionNotificacionRequest();
        ConfiguracionNotificacion configGuardada = new ConfiguracionNotificacion(); configGuardada.setId(1L);
        
        when(configuracionService.guardar(any())).thenReturn(configGuardada);

        ResponseEntity<ConfiguracionNotificacion> response = configuracionNotificacionController.guardar(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenExists_ShouldReturnConfiguracion() {
        ConfiguracionNotificacion config = new ConfiguracionNotificacion(); config.setId(1L);
        when(configuracionService.obtenerPorId(1L)).thenReturn(Optional.of(config));

        ResponseEntity<ConfiguracionNotificacion> response = configuracionNotificacionController.obtener(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtener_WhenNotExists_ShouldReturnNotFound() {
        when(configuracionService.obtenerPorId(99L)).thenReturn(Optional.empty());

        ResponseEntity<ConfiguracionNotificacion> response = configuracionNotificacionController.obtener(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void obtenerPorUsuario_WhenExists_ShouldReturnConfiguracion() {
        ConfiguracionNotificacion config = new ConfiguracionNotificacion(); config.setId(1L);
        when(configuracionService.obtenerPorUsuario(100L)).thenReturn(Optional.of(config));

        ResponseEntity<ConfiguracionNotificacion> response = configuracionNotificacionController.obtenerPorUsuario(100L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void obtenerPorUsuario_WhenNotExists_ShouldReturnNotFound() {
        when(configuracionService.obtenerPorUsuario(100L)).thenReturn(Optional.empty());

        ResponseEntity<ConfiguracionNotificacion> response = configuracionNotificacionController.obtenerPorUsuario(100L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void actualizar_WhenExists_ShouldReturnUpdatedConfiguracion() {
        ConfiguracionNotificacionRequest request = new ConfiguracionNotificacionRequest();
        ConfiguracionNotificacion actualizada = new ConfiguracionNotificacion(); actualizada.setId(1L);
        
        when(configuracionService.actualizar(eq(1L), any())).thenReturn(actualizada);

        ResponseEntity<ConfiguracionNotificacion> response = configuracionNotificacionController.actualizar(1L, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test void actualizar_WhenNotExists_ShouldReturnNotFound() {
        ConfiguracionNotificacionRequest request = new ConfiguracionNotificacionRequest();
        when(configuracionService.actualizar(eq(99L), any())).thenReturn(null);

        ResponseEntity<ConfiguracionNotificacion> response = configuracionNotificacionController.actualizar(99L, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void eliminar_ShouldCallServiceAndReturnOk() {
        doNothing().when(configuracionService).eliminar(1L);

        ResponseEntity<Void> response = configuracionNotificacionController.eliminar(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(configuracionService, times(1)).eliminar(1L);
    }
}