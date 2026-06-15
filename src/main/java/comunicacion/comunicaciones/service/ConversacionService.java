package comunicacion.comunicaciones.service;

import comunicacion.comunicaciones.models.Conversacion;
import comunicacion.comunicaciones.repository.ConversacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConversacionService {

    private final ConversacionRepository conversacionRepository;

    public ConversacionService(ConversacionRepository conversacionRepository) {
        this.conversacionRepository = conversacionRepository;
    }

    public Conversacion crear(Conversacion conversacion) {
        return conversacionRepository.save(conversacion);
    }

    public Optional<Conversacion> obtenerPorId(Long id) {
        return conversacionRepository.findById(id);
    }

    public List<Conversacion> obtenerConversacionesUsuario(Long usuarioId) {
        return conversacionRepository.findByParticipante1IdOrParticipante2Id(usuarioId, usuarioId);
    }

    public List<Conversacion> obtenerActivas() {
        return conversacionRepository.findByActiva(true);
    }

    public Conversacion actualizar(Long id, Conversacion conversacionActualizada) {
        Optional<Conversacion> convOpt = conversacionRepository.findById(id);
        if (convOpt.isPresent()) {
            Conversacion c = convOpt.get();
            c.setUltimaMensajeEn(conversacionActualizada.getUltimaMensajeEn());
            c.setTotalMensajes(conversacionActualizada.getTotalMensajes());
            return conversacionRepository.save(c);
        }
        return null;
    }

    public void desactivar(Long id) {
        Optional<Conversacion> convOpt = conversacionRepository.findById(id);
        if (convOpt.isPresent()) {
            Conversacion c = convOpt.get();
            c.setActiva(false);
            conversacionRepository.save(c);
        }
    }
}