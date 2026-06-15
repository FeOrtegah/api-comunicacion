package comunicaciones.comunicacion.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.MensajeGrupal;
import comunicacion.comunicaciones.repository.MensajeGrupalRepository;
import comunicacion.comunicaciones.service.MensajeGrupalService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MensajeGrupalServiceTest {

    @Mock
    private MensajeGrupalRepository mensajeGrupalRepository;

    @InjectMocks
    private MensajeGrupalService mensajeGrupalService;

    @Test
    void enviar_ShouldReturnSavedMensajeGrupal() {
        MensajeGrupal mensaje = new MensajeGrupal();
        when(mensajeGrupalRepository.save(any(MensajeGrupal.class))).thenReturn(mensaje);
        MensajeGrupal resultado = mensajeGrupalService.enviar(new MensajeGrupal());
        assertNotNull(resultado);
        verify(mensajeGrupalRepository, times(1)).save(any(MensajeGrupal.class));
    }

    @Test
    void obtenerPorId_ShouldReturnOptional() {
        MensajeGrupal mensaje = new MensajeGrupal();
        when(mensajeGrupalRepository.findById(1L)).thenReturn(Optional.of(mensaje));
        Optional<MensajeGrupal> resultado = mensajeGrupalService.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
    }

    @Test
    void obtenerMensajesGrupo_ShouldReturnList() {
        List<MensajeGrupal> lista = Arrays.asList(new MensajeGrupal(), new MensajeGrupal());
        when(mensajeGrupalRepository.findByGrupoIdOrderByFechaEnvioDesc(1L)).thenReturn(lista);
        List<MensajeGrupal> resultado = mensajeGrupalService.obtenerMensajesGrupo(1L);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    void eliminar_ShouldInvokeRepositoryDelete() {
        doNothing().when(mensajeGrupalRepository).deleteById(1L);
        mensajeGrupalService.eliminar(1L);
        verify(mensajeGrupalRepository, times(1)).deleteById(1L);
    }
}