package comunicaciones.comunicacion.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.PlantillaMensaje;
import comunicacion.comunicaciones.repository.PlantillaMensajeRepository;
import comunicacion.comunicaciones.service.PlantillaMensajeService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlantillaMensajeServiceTest {

    @Mock
    private PlantillaMensajeRepository plantillaRepository;
    
    @InjectMocks
    private PlantillaMensajeService plantillaMensajeService;

    @Test
    void crear_ShouldReturnSavedPlantillaMensaje() {
        PlantillaMensaje plantilla = new PlantillaMensaje();
        when(plantillaRepository.save(any(PlantillaMensaje.class))).thenReturn(plantilla);
        PlantillaMensaje resultado = plantillaMensajeService.crear(new PlantillaMensaje());
        assertNotNull(resultado);
        verify(plantillaRepository, times(1)).save(any(PlantillaMensaje.class));
    }

    @Test
    void obtenerPorId_ShouldReturnOptional() {
        PlantillaMensaje plantilla = new PlantillaMensaje();
        when(plantillaRepository.findById(1L)).thenReturn(Optional.of(plantilla));
        Optional<PlantillaMensaje> resultado = plantillaMensajeService.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
    }

    @Test
    void obtenerPlantillasCreador_ShouldReturnList() {
        List<PlantillaMensaje> lista = Arrays.asList(new PlantillaMensaje(), new PlantillaMensaje());
        when(plantillaRepository.findByCreadorId(1L)).thenReturn(lista);
        List<PlantillaMensaje> resultado = plantillaMensajeService.obtenerPlantillasCreador(1L);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    void obtenerPlantillasPublicas_ShouldReturnList() {
        List<PlantillaMensaje> lista = Arrays.asList(new PlantillaMensaje());
        when(plantillaRepository.findByPublica(true)).thenReturn(lista);
        List<PlantillaMensaje> resultado = plantillaMensajeService.obtenerPlantillasPublicas();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void actualizar_WhenPlantillaExists_ShouldReturnUpdatedPlantillaMensaje() {
        PlantillaMensaje plantillaExistente = new PlantillaMensaje();
        PlantillaMensaje datosNuevos = new PlantillaMensaje();
        PlantillaMensaje plantillaGuardada = new PlantillaMensaje();
        when(plantillaRepository.findById(1L)).thenReturn(Optional.of(plantillaExistente));
        when(plantillaRepository.save(any(PlantillaMensaje.class))).thenReturn(plantillaGuardada);
        PlantillaMensaje resultado = plantillaMensajeService.actualizar(1L, datosNuevos);
        assertNotNull(resultado);
        verify(plantillaRepository, times(1)).findById(1L);
        verify(plantillaRepository, times(1)).save(any(PlantillaMensaje.class));
    }

    @Test
    void actualizar_WhenPlantillaDoesNotExist_ShouldReturnNull() {
        when(plantillaRepository.findById(1L)).thenReturn(Optional.empty());
        PlantillaMensaje resultado = plantillaMensajeService.actualizar(1L, new PlantillaMensaje());
        assertNull(resultado);
        verify(plantillaRepository, never()).save(any());
    }

    @Test
    void eliminar_ShouldInvokeRepositoryDelete() {
        doNothing().when(plantillaRepository).deleteById(1L);
        plantillaMensajeService.eliminar(1L);
        verify(plantillaRepository, times(1)).deleteById(1L);
    }
}