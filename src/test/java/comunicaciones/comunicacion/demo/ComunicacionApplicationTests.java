package comunicaciones.comunicacion.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import comunicacion.comunicaciones.ComunicacionApplication;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComunicacionApplicationTest {

    @Test
    void main_ShouldRunSpringApplication() {
        try (MockedStatic<SpringApplication> springAppMock = mockStatic(SpringApplication.class)) {
            springAppMock.when(() -> SpringApplication.run(ComunicacionApplication.class, new String[]{}))
                    .thenReturn(mock(ConfigurableApplicationContext.class));

            ComunicacionApplication.main(new String[]{});

            springAppMock.verify(() -> SpringApplication.run(ComunicacionApplication.class, new String[]{}), times(1));
        }
    }
}