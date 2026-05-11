package comunicacion.comunicaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import comunicacion.comunicaciones.models.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByUsuarioIdOrderByFechaCreacionDesc(Long usuarioId);
    List<Notificacion> findByUsuarioIdAndLeida(Long usuarioId, boolean leida);
}
