package comunicacion.comunicaciones.service;

import comunicacion.comunicaciones.models.GrupoChat;
import comunicacion.comunicaciones.repository.GrupoChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GrupoChatService {

    private final GrupoChatRepository grupoChatRepository;

    public GrupoChatService(GrupoChatRepository grupoChatRepository) {
        this.grupoChatRepository = grupoChatRepository;
    }

    public GrupoChat crear(GrupoChat grupo) {
        return grupoChatRepository.save(grupo);
    }

    public Optional<GrupoChat> obtenerPorId(Long id) {
        return grupoChatRepository.findById(id);
    }

    public List<GrupoChat> obtenerGruposCreador(Long creadorId) {
        return grupoChatRepository.findByCreadorId(creadorId);
    }

    public List<GrupoChat> obtenerActivos() {
        return grupoChatRepository.findByActivo(true);
    }

    public GrupoChat actualizar(Long id, GrupoChat grupoActualizado) {
        Optional<GrupoChat> grupoOpt = grupoChatRepository.findById(id);
        if (grupoOpt.isPresent()) {
            GrupoChat g = grupoOpt.get();
            g.setNombre(grupoActualizado.getNombre());
            g.setDescripcion(grupoActualizado.getDescripcion());
            g.setMiembros(grupoActualizado.getMiembros());
            return grupoChatRepository.save(g);
        }
        return null;
    }

    public void desactivar(Long id) {
        Optional<GrupoChat> grupoOpt = grupoChatRepository.findById(id);
        if (grupoOpt.isPresent()) {
            GrupoChat g = grupoOpt.get();
            g.setActivo(false);
            grupoChatRepository.save(g);
        }
    }
}