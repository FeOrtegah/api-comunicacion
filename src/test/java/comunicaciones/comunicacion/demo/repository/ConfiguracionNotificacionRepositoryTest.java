package comunicaciones.comunicacion.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.ConfiguracionNotificacion;
import comunicacion.comunicaciones.repository.ConfiguracionNotificacionRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfiguracionNotificacionRepositoryTest {

    @Mock
    private ConfiguracionNotificacionRepository configuracionNotificacionRepository;

    @Test
    void findByUsuarioId_WhenConfiguracionExists_ShouldReturnOptionalWithConfig() {
        ConfiguracionNotificacion configSimulada = new ConfiguracionNotificacion();
        configSimulada.setId(1L);
        configSimulada.setUsuarioId(50L);
        configSimulada.setRecibirNotificaciones(true);
        when(configuracionNotificacionRepository.findByUsuarioId(50L))
                .thenReturn(Optional.of(configSimulada));
        Optional<ConfiguracionNotificacion> resultado = configuracionNotificacionRepository.findByUsuarioId(50L);
        assertTrue(resultado.isPresent());
        assertEquals(50L, resultado.get().getUsuarioId());
        assertTrue(resultado.get().isRecibirNotificaciones());
        verify(configuracionNotificacionRepository, times(1)).findByUsuarioId(50L);
    }

    @Test
    void findByUsuarioId_WhenConfiguracionDoesNotExist_ShouldReturnEmptyOptional() {
        when(configuracionNotificacionRepository.findByUsuarioId(999L))
                .thenReturn(Optional.empty());
        Optional<ConfiguracionNotificacion> resultado = configuracionNotificacionRepository.findByUsuarioId(999L);
        assertFalse(resultado.isPresent());
    }
}