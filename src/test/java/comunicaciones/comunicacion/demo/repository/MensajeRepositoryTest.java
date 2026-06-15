package comunicaciones.comunicacion.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.Mensaje;
import comunicacion.comunicaciones.repository.MensajeRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MensajeRepositoryTest {

    @Mock
    private MensajeRepository mensajeRepository;

    @Test
    void findByReceptorIdOrderByFechaEnvioDesc_ShouldReturnMensajes() {
        Mensaje m = new Mensaje();
        m.setId(1L);
        m.setReceptorId(10L);
        when(mensajeRepository.findByReceptorIdOrderByFechaEnvioDesc(10L)).thenReturn(Arrays.asList(m));
        List<Mensaje> resultado = mensajeRepository.findByReceptorIdOrderByFechaEnvioDesc(10L);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(10L, resultado.get(0).getReceptorId());
        verify(mensajeRepository, times(1)).findByReceptorIdOrderByFechaEnvioDesc(10L);
    }

    @Test
    void findByEmisorIdOrderByFechaEnvioDesc_ShouldReturnMensajes() {
        Mensaje m = new Mensaje();
        m.setId(2L);
        m.setEmisorId(20L);
        when(mensajeRepository.findByEmisorIdOrderByFechaEnvioDesc(20L)).thenReturn(Arrays.asList(m));
        List<Mensaje> resultado = mensajeRepository.findByEmisorIdOrderByFechaEnvioDesc(20L);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(20L, resultado.get(0).getEmisorId());
        verify(mensajeRepository, times(1)).findByEmisorIdOrderByFechaEnvioDesc(20L);
    }

    @Test
    void findByConversacionId_ShouldReturnMensajes() {
        Mensaje m1 = new Mensaje();
        m1.setConversacionId(30L);
        Mensaje m2 = new Mensaje();
        m2.setConversacionId(30L);
        when(mensajeRepository.findByConversacionId(30L)).thenReturn(Arrays.asList(m1, m2));
        when(mensajeRepository.findByConversacionId(99L)).thenReturn(Collections.emptyList());
        List<Mensaje> resultadoOk = mensajeRepository.findByConversacionId(30L);
        List<Mensaje> resultadoVacio = mensajeRepository.findByConversacionId(99L);
        assertEquals(2, resultadoOk.size());
        assertTrue(resultadoVacio.isEmpty());
    }
}