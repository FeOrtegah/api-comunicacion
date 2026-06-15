package comunicaciones.comunicacion.demo.model;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import comunicacion.comunicaciones.models.MensajeGrupal;

import static org.junit.jupiter.api.Assertions.*;

class MensajeGrupalTest {

    @Test
    void onCreate_ShouldSetFechaEnvio_WhenInvoked() throws Exception {
        MensajeGrupal mensajeGrupal = new MensajeGrupal();
        mensajeGrupal.setGrupoId(10L);
        mensajeGrupal.setEmisorId(1L);
        mensajeGrupal.setContenido("Hola grupo");
        assertNull(mensajeGrupal.getFechaEnvio());
        Method method = MensajeGrupal.class.getDeclaredMethod("onCreate");
        method.setAccessible(true);
        method.invoke(mensajeGrupal);
        assertNotNull(mensajeGrupal.getFechaEnvio());
        assertTrue(mensajeGrupal.getFechaEnvio().isBefore(LocalDateTime.now().plusMinutes(1)));
    }
}