package comunicaciones.comunicacion.demo.model;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import comunicacion.comunicaciones.models.GrupoChat;
import static org.junit.jupiter.api.Assertions.*;

class GrupoChatTest {

    @Test
    void onCreate_ShouldSetFechaCreacionAndActivoTrue() throws Exception {
        GrupoChat grupo = new GrupoChat();
        grupo.setNombre("Equipo Java");
        grupo.setCreadorId(1L);
        grupo.setMiembros(Arrays.asList(1L, 2L, 3L));
        assertNull(grupo.getFechaCreacion());
        assertFalse(grupo.isActivo());
        Method method = GrupoChat.class.getDeclaredMethod("onCreate");
        method.setAccessible(true);
        method.invoke(grupo);
        assertNotNull(grupo.getFechaCreacion());
        assertTrue(grupo.isActivo());
        assertTrue(grupo.getFechaCreacion().isBefore(LocalDateTime.now().plusMinutes(1)));
    }
}
