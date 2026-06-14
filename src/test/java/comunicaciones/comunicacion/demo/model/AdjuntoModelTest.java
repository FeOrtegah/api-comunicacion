package comunicaciones.comunicacion.demo.model;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import comunicacion.comunicaciones.models.Adjunto;
import static org.junit.jupiter.api.Assertions.*;

class AdjuntoTest {

    @Test
    void onCreate_ShouldSetFechaCarga() throws Exception {
        Adjunto adjunto = new Adjunto();
        adjunto.setNombreArchivo("documento.pdf");
        adjunto.setRuta("/uploads/documento.pdf");
        assertNull(adjunto.getFechaCarga());
        Method method = Adjunto.class.getDeclaredMethod("onCreate");
        method.setAccessible(true);
        method.invoke(adjunto);
        assertNotNull(adjunto.getFechaCarga());
        assertTrue(adjunto.getFechaCarga().isBefore(LocalDateTime.now().plusMinutes(1)));
    }
}