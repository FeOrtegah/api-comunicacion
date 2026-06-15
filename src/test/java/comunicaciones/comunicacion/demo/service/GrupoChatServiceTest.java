package comunicaciones.comunicacion.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import comunicacion.comunicaciones.models.GrupoChat;
import comunicacion.comunicaciones.repository.GrupoChatRepository;
import comunicacion.comunicaciones.service.GrupoChatService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GrupoChatServiceTest {

    @Mock
    private GrupoChatRepository grupoChatRepository;

    @InjectMocks
    private GrupoChatService grupoChatService;

    @Test
    void crear_ShouldReturnSavedGrupoChat() {
        GrupoChat grupo = new GrupoChat();
        when(grupoChatRepository.save(any(GrupoChat.class))).thenReturn(grupo);
        GrupoChat resultado = grupoChatService.crear(new GrupoChat());
        assertNotNull(resultado);
        verify(grupoChatRepository, times(1)).save(any(GrupoChat.class));
    }

    @Test
    void obtenerPorId_ShouldReturnOptional() {
        GrupoChat grupo = new GrupoChat();
        when(grupoChatRepository.findById(1L)).thenReturn(Optional.of(grupo));
        Optional<GrupoChat> resultado = grupoChatService.obtenerPorId(1L);
        assertTrue(resultado.isPresent());
    }

    @Test
    void obtenerGruposCreador_ShouldReturnList() {
        List<GrupoChat> lista = Arrays.asList(new GrupoChat(), new GrupoChat());
        when(grupoChatRepository.findByCreadorId(1L)).thenReturn(lista);
        List<GrupoChat> resultado = grupoChatService.obtenerGruposCreador(1L);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    void obtenerActivos_ShouldReturnList() {
        List<GrupoChat> lista = Arrays.asList(new GrupoChat());
        when(grupoChatRepository.findByActivo(true)).thenReturn(lista);
        List<GrupoChat> resultado = grupoChatService.obtenerActivos();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void actualizar_WhenGrupoExists_ShouldReturnUpdatedGrupoChat() {
        GrupoChat grupoExistente = new GrupoChat();
        GrupoChat datosNuevos = new GrupoChat();
        GrupoChat grupoGuardado = new GrupoChat();
        when(grupoChatRepository.findById(1L)).thenReturn(Optional.of(grupoExistente));
        when(grupoChatRepository.save(any(GrupoChat.class))).thenReturn(grupoGuardado);
        GrupoChat resultado = grupoChatService.actualizar(1L, datosNuevos);
        assertNotNull(resultado);
        verify(grupoChatRepository, times(1)).findById(1L);
        verify(grupoChatRepository, times(1)).save(any(GrupoChat.class));
    }

    @Test
    void actualizar_WhenGrupoDoesNotExist_ShouldReturnNull() {
        when(grupoChatRepository.findById(1L)).thenReturn(Optional.empty());
        GrupoChat resultado = grupoChatService.actualizar(1L, new GrupoChat());
        assertNull(resultado);
        verify(grupoChatRepository, never()).save(any());
    }

    @Test
    void desactivar_WhenGrupoExists_ShouldSetActivoFalseAndSave() {
        GrupoChat grupo = new GrupoChat();
        grupo.setActivo(true);
        when(grupoChatRepository.findById(1L)).thenReturn(Optional.of(grupo));
        when(grupoChatRepository.save(any(GrupoChat.class))).thenReturn(grupo);
        grupoChatService.desactivar(1L);
        verify(grupoChatRepository, times(1)).findById(1L);
        verify(grupoChatRepository, times(1)).save(grupo);
    }

    @Test
    void desactivar_WhenGrupoDoesNotExist_ShouldNotSave() {
        when(grupoChatRepository.findById(1L)).thenReturn(Optional.empty());
        grupoChatService.desactivar(1L);
        verify(grupoChatRepository, never()).save(any());
    }
}