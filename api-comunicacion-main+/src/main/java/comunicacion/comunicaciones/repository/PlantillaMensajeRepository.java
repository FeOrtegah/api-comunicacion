package comunicacion.comunicaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import comunicacion.comunicaciones.models.PlantillaMensaje;

public interface PlantillaMensajeRepository extends JpaRepository<PlantillaMensaje, Long> {
    List<PlantillaMensaje> findByCreadorId(Long creadorId);
    List<PlantillaMensaje> findByPublica(boolean publica);

}
