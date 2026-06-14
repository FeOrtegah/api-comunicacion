package comunicacion.comunicaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import comunicacion.comunicaciones.models.Conversacion;

public interface ConversacionRepository extends JpaRepository<Conversacion, Long> {
    List<Conversacion> findByParticipante1IdOrParticipante2Id(Long id1, Long id2);
    List<Conversacion> findByActiva(boolean activa);
}
