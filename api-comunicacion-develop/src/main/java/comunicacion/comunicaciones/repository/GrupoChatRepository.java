package comunicacion.comunicaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import comunicacion.comunicaciones.models.GrupoChat;

public interface GrupoChatRepository extends JpaRepository<GrupoChat, Long> {
    List<GrupoChat> findByCreadorId(Long creadorId);
    List<GrupoChat> findByActivo(boolean activo);
}
