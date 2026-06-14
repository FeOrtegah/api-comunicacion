package comunicaciones.comunicacion.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.Notificacion;
import comunicacion.comunicaciones.repository.NotificacionRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacionRepositoryTest {

    @Mock
    private NotificacionRepository notificacionRepository;

    @Test
    void findByUsuarioIdOrderByFechaCreacionDesc_ShouldReturnNotificaciones() {
        Notificacion n = new Notificacion();
        n.setId(1L);
        n.setUsuarioId(10L);
        when(notificacionRepository.findByUsuarioIdOrderByFechaCreacionDesc(10L)).thenReturn(Arrays.asList(n));
        List<Notificacion> resultado = notificacionRepository.findByUsuarioIdOrderByFechaCreacionDesc(10L);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(10L, resultado.get(0).getUsuarioId());
        verify(notificacionRepository, times(1)).findByUsuarioIdOrderByFechaCreacionDesc(10L);
    }

    @Test
    void findByUsuarioIdAndLeida_ShouldReturnFilteredNotificaciones() {
        Notificacion nNoLeida = new Notificacion();
        nNoLeida.setId(2L);
        nNoLeida.setUsuarioId(10L);
        nNoLeida.setLeida(false);
        when(notificacionRepository.findByUsuarioIdAndLeida(10L, false)).thenReturn(Arrays.asList(nNoLeida));
        when(notificacionRepository.findByUsuarioIdAndLeida(10L, true)).thenReturn(Collections.emptyList());
        List<Notificacion> noLeidas = notificacionRepository.findByUsuarioIdAndLeida(10L, false);
        List<Notificacion> leidas = notificacionRepository.findByUsuarioIdAndLeida(10L, true);
        assertEquals(1, noLeidas.size());
        assertFalse(noLeidas.get(0).isLeida());
        assertTrue(leidas.isEmpty());
    }
}