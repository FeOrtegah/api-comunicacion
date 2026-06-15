package comunicaciones.comunicacion.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import comunicacion.comunicaciones.dto.*;
import comunicacion.comunicaciones.models.*;
import comunicacion.comunicaciones.service.ComunicacionService;
import comunicacion.comunicaciones.controller.ComunicacionController;
import javax.sql.DataSource;   
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComunicacionControllerTest {

    @Mock private ComunicacionService comunicacionService;
    @Mock private DataSource dataSource;
    
    private ComunicacionController comunicacionController;

    @BeforeEach
    void setUp() throws Exception {
        comunicacionController = new ComunicacionController(comunicacionService);
        Field field = ComunicacionController.class.getDeclaredField("dataSource");
        field.setAccessible(true);
        field.set(comunicacionController, dataSource);
    }

    @Test void enviarMensaje_ShouldReturnOkAndMensaje() {
        MensajeRequest request = new MensajeRequest();
        Mensaje mensajeGuardado = new Mensaje(); mensajeGuardado.setId(1L);
        when(comunicacionService.enviarMensaje(any())).thenReturn(mensajeGuardado);
        ResponseEntity<Mensaje> response = comunicacionController.enviarMensaje(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test void bandejaEntrada_ShouldReturnList() {
        List<Mensaje> mensajes = Arrays.asList(new Mensaje(), new Mensaje());
        when(comunicacionService.obtenerMensajesRecibidos(1L)).thenReturn(mensajes);
        ResponseEntity<List<Mensaje>> response = comunicacionController.bandejaEntrada(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test void marcarComoLeido_ShouldReturnOk() {
        doNothing().when(comunicacionService).marcarComoLeido(1L);
        ResponseEntity<Void> response = comunicacionController.marcarComoLeido(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(comunicacionService, times(1)).marcarComoLeido(1L);
    }

    @Test void crearConversacion_ShouldReturnConversacion() {
        ConversacionRequest request = new ConversacionRequest();
        Conversacion conversacion = new Conversacion(); conversacion.setId(10L);
        when(comunicacionService.crearConversacion(any())).thenReturn(conversacion);
        ResponseEntity<Conversacion> response = comunicacionController.crearConversacion(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(10L, response.getBody().getId());
    }

    @Test void obtenerNoLeidas_ShouldReturnList() {
        when(comunicacionService.obtenerNotificacionesNoLeidas(1L)).thenReturn(Collections.emptyList());
        ResponseEntity<List<Notificacion>> response = comunicacionController.obtenerNoLeidas(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test void obtenerConfiguracion_WhenExists_ShouldReturnConfig() {
        ConfiguracionNotificacion config = new ConfiguracionNotificacion();
        when(comunicacionService.obtenerConfiguracion(1L)).thenReturn(Optional.of(config));
        ResponseEntity<ConfiguracionNotificacion> response = comunicacionController.obtenerConfiguracion(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test void obtenerConfiguracion_WhenNotExists_ShouldReturnNotFound() {
        when(comunicacionService.obtenerConfiguracion(1L)).thenReturn(Optional.empty());
        ResponseEntity<ConfiguracionNotificacion> response = comunicacionController.obtenerConfiguracion(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test void fixSequence_ShouldReturnOk() throws Exception {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        when(dataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.execute(anyString())).thenReturn(true);
        ResponseEntity<String> response = comunicacionController.fixSequence();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ok", response.getBody());
    }

    @Test void handleValidationErrors_ShouldReturnBadRequestWithErrors() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("object", "email", "El email es obligatorio");
        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(fieldError));
        ResponseEntity<Map<String, String>> response = comunicacionController.handleValidationErrors(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El email es obligatorio", response.getBody().get("email"));
    }
}
