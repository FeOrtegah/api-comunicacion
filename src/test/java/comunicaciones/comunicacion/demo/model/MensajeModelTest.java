package comunicaciones.comunicacion.demo.model;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import comunicacion.comunicaciones.models.Mensaje;

import static org.junit.jupiter.api.Assertions.*;

class MensajeTest {

    @Test
    void onCreate_ShouldSetFechaEnvioAndLeidoFalse() throws Exception {
        Mensaje mensaje = new Mensaje();
        mensaje.setEmisorId(1L);
        mensaje.setContenido("Test");
        Method method = Mensaje.class.getDeclaredMethod("onCreate");
        method.setAccessible(true);
        method.invoke(mensaje);
        assertNotNull(mensaje.getFechaEnvio());
        assertFalse(mensaje.isLeido());
        assertTrue(mensaje.getFechaEnvio().isBefore(LocalDateTime.now().plusMinutes(1)));
    }
}