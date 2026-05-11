package comunicacion.comunicaciones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import comunicacion.comunicaciones.models.ConfiguracionNotificacion;

public interface ConfiguracionNotificacionRepository extends JpaRepository<ConfiguracionNotificacion, Long> {
    Optional<ConfiguracionNotificacion> findByUsuarioId(Long usuarioId);
}
