package comunicaciones.comunicacion.demo.model;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import comunicacion.comunicaciones.models.PlantillaMensaje;
import static org.junit.jupiter.api.Assertions.*;

class PlantillaMensajeTest {

    @Test
    void onCreate_ShouldSetFechaCreacionAndPublicaFalse() throws Exception {
        PlantillaMensaje plantilla = new PlantillaMensaje();
        plantilla.setNombre("Plantilla Bienvenida");
        plantilla.setContenido("Hola [nombre], bienvenido.");
        assertNull(plantilla.getFechaCreacion());
        assertFalse(plantilla.isPublica());
        Method method = PlantillaMensaje.class.getDeclaredMethod("onCreate");
        method.setAccessible(true);
        method.invoke(plantilla);
        assertNotNull(plantilla.getFechaCreacion());
        assertFalse(plantilla.isPublica());
        assertTrue(plantilla.getFechaCreacion().isBefore(LocalDateTime.now().plusMinutes(1)));
    }
}