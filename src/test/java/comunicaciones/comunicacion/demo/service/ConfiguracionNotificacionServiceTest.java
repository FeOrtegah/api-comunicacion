package comunicaciones.comunicacion.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.ConfiguracionNotificacion;
import comunicacion.comunicaciones.repository.ConfiguracionNotificacionRepository;
import comunicacion.comunicaciones.service.ConfiguracionNotificacionService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfiguracionNotificacionServiceTest {

    @Mock
    private ConfiguracionNotificacionRepository configuracionRepository;

    @InjectMocks
    private ConfiguracionNotificacionService configuracionService;

    @Test
    void guardar_ShouldReturnSavedConfiguracion() {
        ConfiguracionNotificacion config = new ConfiguracionNotificacion();
        when(configuracionRepository.save(any(ConfiguracionNotificacion.class))).thenReturn(config);
        ConfiguracionNotificacion resultado = configuracionService.guardar(new ConfiguracionNotificacion());
        assertNotNull(resultado);
        verify(configuracionRepository, times(1)).save(any(ConfiguracionNotificacion.class));
    }

    @Test
    void obtenerPorUsuario_ShouldReturnOptional() {
        ConfiguracionNotificacion config = new ConfiguracionNotificacion();
        when(configuracionRepository.findByUsuarioId(1L)).thenReturn(Optional.of(config));
        Optional<ConfiguracionNotificacion> resultado = configuracionService.obtenerPorUsuario(1L);
        assertTrue(resultado.isPresent());
    }

    @Test
    void obtenerPorId_ShouldReturnOptional() {
        ConfiguracionNotificacion config = new ConfiguracionNotificacion();
        when(configuracionRepository.findById(1L)).thenReturn(Optional.of(config));
        Optional<ConfiguracionNotificacion> resultado = configuracionService.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
    }

    @Test
    void actualizar_WhenConfiguracionExists_ShouldReturnUpdatedConfiguracion() {
        ConfiguracionNotificacion configExistente = new ConfiguracionNotificacion();
        ConfiguracionNotificacion datosNuevos = new ConfiguracionNotificacion();
        ConfiguracionNotificacion configGuardada = new ConfiguracionNotificacion();

        when(configuracionRepository.findById(1L)).thenReturn(Optional.of(configExistente));
        when(configuracionRepository.save(any(ConfiguracionNotificacion.class))).thenReturn(configGuardada);
        ConfiguracionNotificacion resultado = configuracionService.actualizar(1L, datosNuevos);
        assertNotNull(resultado);
        verify(configuracionRepository, times(1)).findById(1L);
        verify(configuracionRepository, times(1)).save(any(ConfiguracionNotificacion.class));
    }

    @Test
    void actualizar_WhenConfiguracionDoesNotExist_ShouldReturnNull() {
        when(configuracionRepository.findById(1L)).thenReturn(Optional.empty());
        ConfiguracionNotificacion resultado = configuracionService.actualizar(1L, new ConfiguracionNotificacion());
        assertNull(resultado);
        verify(configuracionRepository, never()).save(any());
    }

    @Test
    void eliminar_ShouldInvokeRepositoryDelete() {
        doNothing().when(configuracionRepository).deleteById(1L);
        configuracionService.eliminar(1L);
        verify(configuracionRepository, times(1)).deleteById(1L);
    }
}