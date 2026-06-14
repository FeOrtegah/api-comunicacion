package comunicaciones.comunicacion.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.Conversacion;
import comunicacion.comunicaciones.repository.ConversacionRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConversacionRepositoryTest {

    @Mock
    private ConversacionRepository conversacionRepository;

    @Test
    void findByParticipante1IdOrParticipante2Id_ShouldReturnConversaciones() {
        Conversacion conv1 = new Conversacion();
        conv1.setId(1L);
        conv1.setParticipante1Id(10L);
        conv1.setParticipante2Id(20L);

        Conversacion conv2 = new Conversacion();
        conv2.setId(2L);
        conv2.setParticipante1Id(30L);
        conv2.setParticipante2Id(10L);
        List<Conversacion> esperado = Arrays.asList(conv1, conv2);
        when(conversacionRepository.findByParticipante1IdOrParticipante2Id(10L, 10L)).thenReturn(esperado);
        List<Conversacion> resultado = conversacionRepository.findByParticipante1IdOrParticipante2Id(10L, 10L);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(conversacionRepository, times(1)).findByParticipante1IdOrParticipante2Id(10L, 10L);
    }

    @Test
    void findByActiva_ShouldReturnFilteredConversaciones() {
        Conversacion convActiva = new Conversacion();
        convActiva.setId(1L);
        convActiva.setActiva(true);
        when(conversacionRepository.findByActiva(true)).thenReturn(Arrays.asList(convActiva));
        when(conversacionRepository.findByActiva(false)).thenReturn(Collections.emptyList());
        List<Conversacion> activas = conversacionRepository.findByActiva(true);
        List<Conversacion> inactivas = conversacionRepository.findByActiva(false);
        assertEquals(1, activas.size());
        assertTrue(activas.get(0).isActiva());
        assertTrue(inactivas.isEmpty());
    }
}