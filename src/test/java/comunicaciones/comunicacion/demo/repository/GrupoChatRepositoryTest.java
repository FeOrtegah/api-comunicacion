package comunicaciones.comunicacion.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.GrupoChat;
import comunicacion.comunicaciones.repository.GrupoChatRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GrupoChatRepositoryTest {

    @Mock
    private GrupoChatRepository grupoChatRepository;

    @Test
    void findByCreadorId_ShouldReturnGrupos() {
        GrupoChat grupo1 = new GrupoChat();
        grupo1.setId(1L);
        grupo1.setCreadorId(100L);

        GrupoChat grupo2 = new GrupoChat();
        grupo2.setId(2L);
        grupo2.setCreadorId(100L);
        List<GrupoChat> esperado = Arrays.asList(grupo1, grupo2);
        when(grupoChatRepository.findByCreadorId(100L)).thenReturn(esperado);
        List<GrupoChat> resultado = grupoChatRepository.findByCreadorId(100L);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(grupoChatRepository, times(1)).findByCreadorId(100L);
    }

    @Test
    void findByActivo_ShouldReturnFilteredGrupos() {
        GrupoChat grupoActivo = new GrupoChat();
        grupoActivo.setId(1L);
        grupoActivo.setActivo(true);
        when(grupoChatRepository.findByActivo(true)).thenReturn(Arrays.asList(grupoActivo));
        when(grupoChatRepository.findByActivo(false)).thenReturn(Collections.emptyList());
        List<GrupoChat> activos = grupoChatRepository.findByActivo(true);
        List<GrupoChat> inactivos = grupoChatRepository.findByActivo(false);
        assertEquals(1, activos.size());
        assertTrue(activos.get(0).isActivo());
        assertTrue(inactivos.isEmpty());
    }
}