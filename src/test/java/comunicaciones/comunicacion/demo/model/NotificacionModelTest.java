package comunicaciones.comunicacion.demo.model;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import comunicacion.comunicaciones.models.Notificacion;
import static org.junit.jupiter.api.Assertions.*;

class NotificacionTest {

    @Test
    void onCreate_ShouldSetFechaCreacionAndLeidaFalse() throws Exception {
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuarioId(1L);
        notificacion.setContenido("Nueva notificación");
        assertNull(notificacion.getFechaCreacion());
        assertFalse(notificacion.isLeida());
        Method method = Notificacion.class.getDeclaredMethod("onCreate");
        method.setAccessible(true);
        method.invoke(notificacion);
        assertNotNull(notificacion.getFechaCreacion());
        assertFalse(notificacion.isLeida());
        assertTrue(notificacion.getFechaCreacion().isBefore(LocalDateTime.now().plusMinutes(1)));
    }
}