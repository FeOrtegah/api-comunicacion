package comunicacion.comunicaciones.service;

import comunicacion.comunicaciones.models.MensajeGrupal;
import comunicacion.comunicaciones.repository.MensajeGrupalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MensajeGrupalService {

    private final MensajeGrupalRepository mensajeGrupalRepository;

    public MensajeGrupalService(MensajeGrupalRepository mensajeGrupalRepository) {
        this.mensajeGrupalRepository = mensajeGrupalRepository;
    }

    public MensajeGrupal enviar(MensajeGrupal mensaje) {
        return mensajeGrupalRepository.save(mensaje);
    }

    public Optional<MensajeGrupal> obtenerPorId(Long id) {
        return mensajeGrupalRepository.findById(id);
    }

    public List<MensajeGrupal> obtenerMensajesGrupo(Long grupoId) {
        return mensajeGrupalRepository.findByGrupoIdOrderByFechaEnvioDesc(grupoId);
    }

    public void eliminar(Long id) {
        mensajeGrupalRepository.deleteById(id);
    }
}