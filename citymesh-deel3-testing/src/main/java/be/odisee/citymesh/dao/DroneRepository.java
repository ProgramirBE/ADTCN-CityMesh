package be.odisee.citymesh.dao;

import be.odisee.citymesh.domain.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "drones", path = "drones")
public interface DroneRepository extends JpaRepository<Drone, Long> {
}