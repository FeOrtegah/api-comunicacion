package comunicacion.comunicaciones.service;

import comunicacion.comunicaciones.models.Notificacion;
import comunicacion.comunicaciones.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public Notificacion crear(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public Optional<Notificacion> obtenerPorId(Long id) {
        return notificacionRepository.findById(id);
    }

    public List<Notificacion> obtenerNotificacionesUsuario(Long usuarioId) {
        return notificacionRepository.findByUsuarioIdOrderByFechaCreacionDesc(usuarioId);
    }

    public List<Notificacion> obtenerNoLeidas(Long usuarioId) {
        return notificacionRepository.findByUsuarioIdAndLeida(usuarioId, false);
    }

    public void marcarComoLeida(Long id) {
        Optional<Notificacion> notifOpt = notificacionRepository.findById(id);
        if (notifOpt.isPresent()) {
            Notificacion n = notifOpt.get();
            n.setLeida(true);
            notificacionRepository.save(n);
        }
    }

    public void eliminar(Long id) {
        notificacionRepository.deleteById(id);
    }
}