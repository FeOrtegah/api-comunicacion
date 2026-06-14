package comunicaciones.comunicacion.demo.model;

import org.junit.jupiter.api.Test;
import comunicacion.comunicaciones.models.TipoNotificacion;
import static org.junit.jupiter.api.Assertions.*;

class TipoNotificacionTest {

    @Test
    void testEnumValues() {
        TipoNotificacion[] valores = TipoNotificacion.values();
        
        assertEquals(4, valores.length);
        assertEquals(TipoNotificacion.NUEVO_MENSAJE, TipoNotificacion.valueOf("NUEVO_MENSAJE"));
        assertEquals(TipoNotificacion.MENSAJE_GRUPAL, TipoNotificacion.valueOf("MENSAJE_GRUPAL"));
        assertEquals(TipoNotificacion.RECORDATORIO, TipoNotificacion.valueOf("RECORDATORIO"));
        assertEquals(TipoNotificacion.ANUNCIO, TipoNotificacion.valueOf("ANUNCIO"));
    }
}