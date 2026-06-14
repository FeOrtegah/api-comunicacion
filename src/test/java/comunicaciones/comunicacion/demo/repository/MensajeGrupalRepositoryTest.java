package comunicaciones.comunicacion.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.MensajeGrupal;
import comunicacion.comunicaciones.repository.MensajeGrupalRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MensajeGrupalRepositoryTest {

    @Mock
    private MensajeGrupalRepository mensajeGrupalRepository;

    @Test
    void findByGrupoIdOrderByFechaEnvioDesc_WhenMessagesExist_ShouldReturnSortedList() {
        MensajeGrupal m1 = new MensajeGrupal();
        m1.setId(1L);
        m1.setGrupoId(50L);
        MensajeGrupal m2 = new MensajeGrupal();
        m2.setId(2L);
        m2.setGrupoId(50L);
        List<MensajeGrupal> esperado = Arrays.asList(m1, m2);
        when(mensajeGrupalRepository.findByGrupoIdOrderByFechaEnvioDesc(50L)).thenReturn(esperado);
        List<MensajeGrupal> resultado = mensajeGrupalRepository.findByGrupoIdOrderByFechaEnvioDesc(50L);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(50L, resultado.get(0).getGrupoId());
        verify(mensajeGrupalRepository, times(1)).findByGrupoIdOrderByFechaEnvioDesc(50L);
    }

    @Test
    void findByGrupoIdOrderByFechaEnvioDesc_WhenNoMessagesExist_ShouldReturnEmptyList() {
        when(mensajeGrupalRepository.findByGrupoIdOrderByFechaEnvioDesc(99L)).thenReturn(Collections.emptyList());
        List<MensajeGrupal> resultado = mensajeGrupalRepository.findByGrupoIdOrderByFechaEnvioDesc(99L);
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}