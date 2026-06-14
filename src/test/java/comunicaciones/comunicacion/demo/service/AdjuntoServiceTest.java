package comunicaciones.comunicacion.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.Adjunto;
import comunicacion.comunicaciones.repository.AdjuntoRepository;
import comunicacion.comunicaciones.service.AdjuntoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdjuntoServiceTest {

    @Mock
    private AdjuntoRepository adjuntoRepository;

    @InjectMocks
    private AdjuntoService adjuntoService;

    @Test
    void guardar_ShouldReturnSavedAdjunto() {
        Adjunto adjunto = new Adjunto();
        adjunto.setNombreArchivo("test.pdf");
        when(adjuntoRepository.save(any(Adjunto.class))).thenReturn(adjunto);
        Adjunto resultado = adjuntoService.guardar(new Adjunto());
        assertNotNull(resultado);
        assertEquals("test.pdf", resultado.getNombreArchivo());
        verify(adjuntoRepository, times(1)).save(any(Adjunto.class));
    }

    @Test
    void obtenerPorId_WhenExists_ShouldReturnOptionalWithAdjunto() {
        Adjunto adjunto = new Adjunto();
        adjunto.setId(1L);
        when(adjuntoRepository.findById(1L)).thenReturn(Optional.of(adjunto));
        Optional<Adjunto> resultado = adjuntoService.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    void obtenerPorId_WhenDoesNotExist_ShouldReturnEmptyOptional() {
        when(adjuntoRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Adjunto> resultado = adjuntoService.obtenerPorId(99L);
        assertFalse(resultado.isPresent());
    }

    @Test
    void obtenerPorMensaje_ShouldReturnList() {
        List<Adjunto> lista = Arrays.asList(new Adjunto(), new Adjunto());
        when(adjuntoRepository.findByMensajeId(10L)).thenReturn(lista);
        List<Adjunto> resultado = adjuntoService.obtenerPorMensaje(10L);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    void eliminar_ShouldInvokeRepositoryDelete() {
        doNothing().when(adjuntoRepository).deleteById(1L);
        adjuntoService.eliminar(1L);
        verify(adjuntoRepository, times(1)).deleteById(1L);
    }
}