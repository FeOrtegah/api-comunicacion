package comunicacion.comunicaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import comunicacion.comunicaciones.models.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByReceptorIdOrderByFechaEnvioDesc(Long receptorId);
    List<Mensaje> findByEmisorIdOrderByFechaEnvioDesc(Long emisorId);
    List<Mensaje> findByConversacionId(Long conversacionId);
}
