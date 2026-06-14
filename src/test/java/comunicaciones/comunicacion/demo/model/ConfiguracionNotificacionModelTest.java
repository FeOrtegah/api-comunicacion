package comunicaciones.comunicacion.demo.model;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import comunicacion.comunicaciones.models.ConfiguracionNotificacion;
import comunicacion.comunicaciones.models.FrecuenciaNotificacion;
import static org.junit.jupiter.api.Assertions.*;

class ConfiguracionNotificacionTest {

    @Test
    void onCreate_WhenFrecuenciaIsNull_ShouldSetInmediata() throws Exception {
        ConfiguracionNotificacion config = new ConfiguracionNotificacion();
        config.setUsuarioId(1L);
        config.setFrecuencia(null);
        assertNull(config.getFrecuencia());
        Method method = ConfiguracionNotificacion.class.getDeclaredMethod("onCreate");
        method.setAccessible(true);
        method.invoke(config);
        assertEquals(FrecuenciaNotificacion.INMEDIATA, config.getFrecuencia());
    }

    @Test
    void onCreate_WhenFrecuenciaIsNotNull_ShouldKeepExistingValue() throws Exception {
        ConfiguracionNotificacion config = new ConfiguracionNotificacion();
        config.setUsuarioId(1L);
        config.setFrecuencia(FrecuenciaNotificacion.DIARIA);
        Method method = ConfiguracionNotificacion.class.getDeclaredMethod("onCreate");
        method.setAccessible(true);
        method.invoke(config);
        assertEquals(FrecuenciaNotificacion.DIARIA, config.getFrecuencia());
    }
}