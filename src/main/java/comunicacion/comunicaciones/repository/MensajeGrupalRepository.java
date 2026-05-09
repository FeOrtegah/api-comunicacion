package comunicacion.comunicaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import comunicacion.comunicaciones.models.MensajeGrupal;

public interface MensajeGrupalRepository extends JpaRepository<MensajeGrupal, Long> {
    List<MensajeGrupal> findByGrupoIdOrderByFechaEnvioDesc(Long grupoId);
}
