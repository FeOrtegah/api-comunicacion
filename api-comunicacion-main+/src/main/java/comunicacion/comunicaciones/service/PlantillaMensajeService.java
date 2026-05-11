package comunicacion.comunicaciones.service;

import comunicacion.comunicaciones.models.PlantillaMensaje;
import comunicacion.comunicaciones.repository.PlantillaMensajeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlantillaMensajeService {

    private final PlantillaMensajeRepository plantillaRepository;

    public PlantillaMensajeService(PlantillaMensajeRepository plantillaRepository) {
        this.plantillaRepository = plantillaRepository;
    }

    public PlantillaMensaje crear(PlantillaMensaje plantilla) {
        return plantillaRepository.save(plantilla);
    }

    public Optional<PlantillaMensaje> obtenerPorId(Long id) {
        return plantillaRepository.findById(id);
    }

    public List<PlantillaMensaje> obtenerPlantillasCreador(Long creadorId) {
        return plantillaRepository.findByCreadorId(creadorId);
    }

    public List<PlantillaMensaje> obtenerPlantillasPublicas() {
        return plantillaRepository.findByPublica(true);
    }

    public PlantillaMensaje actualizar(Long id, PlantillaMensaje plantillaActualizada) {
        Optional<PlantillaMensaje> plantOpt = plantillaRepository.findById(id);
        if (plantOpt.isPresent()) {
            PlantillaMensaje p = plantOpt.get();
            p.setNombre(plantillaActualizada.getNombre());
            p.setContenido(plantillaActualizada.getContenido());
            p.setDescripcion(plantillaActualizada.getDescripcion());
            p.setPublica(plantillaActualizada.isPublica());
            return plantillaRepository.save(p);
        }
        return null;
    }

    public void eliminar(Long id) {
        plantillaRepository.deleteById(id);
    }
}