    package comunicacion.comunicaciones.service;

    import comunicacion.comunicaciones.models.ConfiguracionNotificacion;
    import comunicacion.comunicaciones.repository.ConfiguracionNotificacionRepository;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import java.util.Optional;

    @Service
    @Transactional
    public class ConfiguracionNotificacionService {

        private final ConfiguracionNotificacionRepository configuracionRepository;

        public ConfiguracionNotificacionService(ConfiguracionNotificacionRepository configuracionRepository) {
            this.configuracionRepository = configuracionRepository;
        }

        public ConfiguracionNotificacion guardar(ConfiguracionNotificacion configuracion) {
            return configuracionRepository.save(configuracion);
        }

        public Optional<ConfiguracionNotificacion> obtenerPorUsuario(Long usuarioId) {
            return configuracionRepository.findByUsuarioId(usuarioId);
        }

        public Optional<ConfiguracionNotificacion> obtenerPorId(Long id) {
            return configuracionRepository.findById(id);
        }

        public ConfiguracionNotificacion actualizar(Long id, ConfiguracionNotificacion configActualizada) {
            Optional<ConfiguracionNotificacion> configOpt = configuracionRepository.findById(id);
            if (configOpt.isPresent()) {
                ConfiguracionNotificacion c = configOpt.get();
                c.setRecibirMensajes(configActualizada.isRecibirMensajes());
                c.setRecibirNotificaciones(configActualizada.isRecibirNotificaciones());
                c.setRecibirAvisos(configActualizada.isRecibirAvisos());
                c.setFrecuencia(configActualizada.getFrecuencia());
                c.setHorarioInicio(configActualizada.getHorarioInicio());
                c.setHorarioFin(configActualizada.getHorarioFin());
                return configuracionRepository.save(c);
            }
            return null;
        }

        public void eliminar(Long id) {
            configuracionRepository.deleteById(id);
        }
    }