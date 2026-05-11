package comunicacion.comunicaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import comunicacion.comunicaciones.models.Adjunto;

public interface AdjuntoRepository extends JpaRepository<Adjunto, Long> {
    List<Adjunto> findByMensajeId(Long mensajeId);
}
