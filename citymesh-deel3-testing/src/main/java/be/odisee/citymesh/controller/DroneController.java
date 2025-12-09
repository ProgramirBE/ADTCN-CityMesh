package be.odisee.citymesh.controller;

import be.odisee.citymesh.domain.Drone;
import be.odisee.citymesh.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion CRUD des drones
 * Fournit les opérations Create, Read, Update, Delete via REST API
 */
@RestController
@RequestMapping("/api/drones")
@CrossOrigin(origins = "*")
public class DroneController {

    @Autowired
    private DroneService droneService;

    /**
     * GET /api/drones - Récupère tous les drones
     */
    @GetMapping
    public ResponseEntity<List<Drone>> getAllDrones() {
        List<Drone> drones = droneService.getAllDrones();
        return ResponseEntity.ok(drones);
    }

    /**
     * GET /api/drones/{id} - Récupère un drone par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Drone> getDroneById(@PathVariable Long id) {
        Optional<Drone> drone = droneService.getDroneById(id);
        return drone.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/drones - Crée un nouveau drone
     */
    @PostMapping
    public ResponseEntity<Drone> createDrone(@RequestBody Drone drone) {
        Drone savedDrone = droneService.saveDrone(drone);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDrone);
    }

    /**
     * PUT /api/drones/{id} - Met à jour un drone existant
     */
    @PutMapping("/{id}")
    public ResponseEntity<Drone> updateDrone(@PathVariable Long id, @RequestBody Drone droneDetails) {
        Optional<Drone> existingDrone = droneService.getDroneById(id);

        if (existingDrone.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Drone drone = existingDrone.get();
        drone.setSerienummer(droneDetails.getSerienummer());
        drone.setModel(droneDetails.getModel());
        drone.setStatus(droneDetails.getStatus());
        drone.setBatterijNiveau(droneDetails.getBatterijNiveau());

        Drone updatedDrone = droneService.saveDrone(drone);
        return ResponseEntity.ok(updatedDrone);
    }

    /**
     * DELETE /api/drones/{id} - Supprime un drone
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrone(@PathVariable Long id) {
        Optional<Drone> drone = droneService.getDroneById(id);

        if (drone.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        droneService.deleteDrone(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/drones/status/{status} - Récupère les drones par statut
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Drone>> getDronesByStatus(@PathVariable String status) {
        List<Drone> drones = droneService.getDronesByStatus(status);
        return ResponseEntity.ok(drones);
    }
}

