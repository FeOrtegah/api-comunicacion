package comunicaciones.comunicacion.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.Adjunto;
import comunicacion.comunicaciones.repository.AdjuntoRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdjuntoRepositoryTest {

    @Mock
    private AdjuntoRepository adjuntoRepository;

    @Test
    void findByMensajeId_WhenAdjuntosExist_ShouldReturnList() {
        Adjunto adjunto1 = new Adjunto();
        adjunto1.setMensajeId(100L);
        adjunto1.setNombreArchivo("foto.png");
        Adjunto adjunto2 = new Adjunto();
        adjunto2.setMensajeId(100L);
        adjunto2.setNombreArchivo("documento.pdf");
        List<Adjunto> listaSimulada = Arrays.asList(adjunto1, adjunto2);
        when(adjuntoRepository.findByMensajeId(100L)).thenReturn(listaSimulada);
        List<Adjunto> resultados = adjuntoRepository.findByMensajeId(100L);
        assertNotNull(resultados);
        assertEquals(2, resultados.size());
        assertEquals("foto.png", resultados.get(0).getNombreArchivo());
        verify(adjuntoRepository, times(1)).findByMensajeId(100L);
    }

    @Test
    void findByMensajeId_WhenNoAdjuntosExist_ShouldReturnEmptyList() {
        when(adjuntoRepository.findByMensajeId(999L)).thenReturn(Collections.emptyList());
        List<Adjunto> resultados = adjuntoRepository.findByMensajeId(999L);
        assertNotNull(resultados);
        assertTrue(resultados.isEmpty());
    }
}