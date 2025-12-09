package be.odisee.citymesh.service;

import be.odisee.citymesh.domain.Drone;
import be.odisee.citymesh.dao.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service pour la logique métier des drones
 */
@Service
public class DroneService {

    @Autowired
    private DroneRepository droneRepository;

    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    public Optional<Drone> getDroneById(Long id) {
        return droneRepository.findById(id);
    }

    public Drone saveDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    public void deleteDrone(Long id) {
        droneRepository.deleteById(id);
    }

    public List<Drone> getDronesByStatus(String status) {
        return droneRepository.findAll().stream()
                .filter(drone -> drone.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}

