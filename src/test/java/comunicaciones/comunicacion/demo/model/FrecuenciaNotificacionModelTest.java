package comunicaciones.comunicacion.demo.model;

import org.junit.jupiter.api.Test;
import comunicacion.comunicaciones.models.FrecuenciaNotificacion;
import static org.junit.jupiter.api.Assertions.*;

class FrecuenciaNotificacionTest {

    @Test
    void testEnumValues() {
        FrecuenciaNotificacion[] valores = FrecuenciaNotificacion.values();
        
        assertEquals(4, valores.length);
        assertEquals(FrecuenciaNotificacion.INMEDIATA, FrecuenciaNotificacion.valueOf("INMEDIATA"));
        assertEquals(FrecuenciaNotificacion.DIARIA, FrecuenciaNotificacion.valueOf("DIARIA"));
        assertEquals(FrecuenciaNotificacion.SEMANAL, FrecuenciaNotificacion.valueOf("SEMANAL"));
        assertEquals(FrecuenciaNotificacion.NUNCA, FrecuenciaNotificacion.valueOf("NUNCA"));
    }
}
