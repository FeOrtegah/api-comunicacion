package comunicaciones.comunicacion.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.PlantillaMensaje;
import comunicacion.comunicaciones.repository.PlantillaMensajeRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlantillaMensajeRepositoryTest {

    @Mock
    private PlantillaMensajeRepository plantillaMensajeRepository;

    @Test
    void findByCreadorId_ShouldReturnPlantillas() {
        PlantillaMensaje p1 = new PlantillaMensaje();
        p1.setId(1L);
        p1.setCreadorId(200L);

        PlantillaMensaje p2 = new PlantillaMensaje();
        p2.setId(2L);
        p2.setCreadorId(200L);
        List<PlantillaMensaje> esperado = Arrays.asList(p1, p2);
        when(plantillaMensajeRepository.findByCreadorId(200L)).thenReturn(esperado);
        List<PlantillaMensaje> resultado = plantillaMensajeRepository.findByCreadorId(200L);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(plantillaMensajeRepository, times(1)).findByCreadorId(200L);
    }

    @Test
    void findByPublica_ShouldReturnFilteredPlantillas() {
        PlantillaMensaje pPublica = new PlantillaMensaje();
        pPublica.setId(1L);
        pPublica.setPublica(true);
        when(plantillaMensajeRepository.findByPublica(true)).thenReturn(Arrays.asList(pPublica));
        when(plantillaMensajeRepository.findByPublica(false)).thenReturn(Collections.emptyList());
        List<PlantillaMensaje> publicas = plantillaMensajeRepository.findByPublica(true);
        List<PlantillaMensaje> privadas = plantillaMensajeRepository.findByPublica(false);
        assertEquals(1, publicas.size());
        assertTrue(publicas.get(0).isPublica());
        assertTrue(privadas.isEmpty());
    }
}
