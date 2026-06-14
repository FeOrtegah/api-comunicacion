package comunicaciones.comunicacion.demo.model;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import comunicacion.comunicaciones.models.Conversacion;
import static org.junit.jupiter.api.Assertions.*;

class ConversacionTest {

    @Test
    void onCreate_ShouldSetInitialValues() throws Exception {
        Conversacion conversacion = new Conversacion();
        conversacion.setParticipante1Id(1L);
        conversacion.setParticipante2Id(2L);
        assertNull(conversacion.getFechaCreacion());
        assertFalse(conversacion.isActiva());
        assertNull(conversacion.getTotalMensajes());
        Method method = Conversacion.class.getDeclaredMethod("onCreate");
        method.setAccessible(true);
        method.invoke(conversacion);
        assertNotNull(conversacion.getFechaCreacion());
        assertTrue(conversacion.isActiva());
        assertEquals(0, conversacion.getTotalMensajes());
        assertTrue(conversacion.getFechaCreacion().isBefore(LocalDateTime.now().plusMinutes(1)));
    }
}