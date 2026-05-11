package comunicacion.comunicaciones.service;

import comunicacion.comunicaciones.models.Adjunto;
import comunicacion.comunicaciones.repository.AdjuntoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdjuntoService {

    private final AdjuntoRepository adjuntoRepository;

    public AdjuntoService(AdjuntoRepository adjuntoRepository) {
        this.adjuntoRepository = adjuntoRepository;
    }

    public Adjunto guardar(Adjunto adjunto) {
        return adjuntoRepository.save(adjunto);
    }

    public Optional<Adjunto> obtenerPorId(Long id) {
        return adjuntoRepository.findById(id);
    }

    public List<Adjunto> obtenerPorMensaje(Long mensajeId) {
        return adjuntoRepository.findByMensajeId(mensajeId);
    }

    public void eliminar(Long id) {
        adjuntoRepository.deleteById(id);
    }
}