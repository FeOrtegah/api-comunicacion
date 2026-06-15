package comunicaciones.comunicacion.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.Conversacion;
import comunicacion.comunicaciones.repository.ConversacionRepository;
import comunicacion.comunicaciones.service.ConversacionService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConversacionServiceTest {

    @Mock
    private ConversacionRepository conversacionRepository;

    @InjectMocks
    private ConversacionService conversacionService;

    @Test
    void crear_ShouldReturnSavedConversacion() {
        Conversacion conversacion = new Conversacion();
        when(conversacionRepository.save(any(Conversacion.class))).thenReturn(conversacion);
        Conversacion resultado = conversacionService.crear(new Conversacion());
        assertNotNull(resultado);
        verify(conversacionRepository, times(1)).save(any(Conversacion.class));
    }

    @Test
    void obtenerPorId_ShouldReturnOptional() {
        Conversacion conversacion = new Conversacion();
        when(conversacionRepository.findById(1L)).thenReturn(Optional.of(conversacion));
        Optional<Conversacion> resultado = conversacionService.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
    }

    @Test
    void obtenerConversacionesUsuario_ShouldReturnList() {
        List<Conversacion> lista = Arrays.asList(new Conversacion(), new Conversacion());
        when(conversacionRepository.findByParticipante1IdOrParticipante2Id(1L, 1L)).thenReturn(lista);
        List<Conversacion> resultado = conversacionService.obtenerConversacionesUsuario(1L);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    void obtenerActivas_ShouldReturnList() {
        List<Conversacion> lista = Arrays.asList(new Conversacion());
        when(conversacionRepository.findByActiva(true)).thenReturn(lista);
        List<Conversacion> resultado = conversacionService.obtenerActivas();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void actualizar_WhenConversacionExists_ShouldReturnUpdatedConversacion() {
        Conversacion configExistente = new Conversacion();
        Conversacion datosNuevos = new Conversacion();
        Conversacion configGuardada = new Conversacion();
        when(conversacionRepository.findById(1L)).thenReturn(Optional.of(configExistente));
        when(conversacionRepository.save(any(Conversacion.class))).thenReturn(configGuardada);
        Conversacion resultado = conversacionService.actualizar(1L, datosNuevos);
        assertNotNull(resultado);
        verify(conversacionRepository, times(1)).findById(1L);
        verify(conversacionRepository, times(1)).save(any(Conversacion.class));
    }

    @Test
    void actualizar_WhenConversacionDoesNotExist_ShouldReturnNull() {
        when(conversacionRepository.findById(1L)).thenReturn(Optional.empty());
        Conversacion resultado = conversacionService.actualizar(1L, new Conversacion());
        assertNull(resultado);
        verify(conversacionRepository, never()).save(any());
    }

    @Test
    void desactivar_WhenConversacionExists_ShouldSetActivaFalseAndSave() {
        Conversacion conversacion = new Conversacion();
        conversacion.setActiva(true);
        when(conversacionRepository.findById(1L)).thenReturn(Optional.of(conversacion));
        when(conversacionRepository.save(any(Conversacion.class))).thenReturn(conversacion);
        conversacionService.desactivar(1L);
        verify(conversacionRepository, times(1)).findById(1L);
        verify(conversacionRepository, times(1)).save(conversacion);
    }

    @Test
    void desactivar_WhenConversacionDoesNotExist_ShouldNotSave() {
        when(conversacionRepository.findById(1L)).thenReturn(Optional.empty());
        conversacionService.desactivar(1L);
        verify(conversacionRepository, never()).save(any());
    }
}