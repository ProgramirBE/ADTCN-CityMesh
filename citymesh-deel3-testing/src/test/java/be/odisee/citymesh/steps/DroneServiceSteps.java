package be.odisee.citymesh.steps;

import be.odisee.citymesh.dao.DroneRepository;
import be.odisee.citymesh.domain.Drone;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional // Important : annule les modifications dans la DB après chaque test
public class DroneServiceSteps {

    @Autowired
    private DroneRepository droneRepository;

    @Given("de drone repository is leeg")
    public void de_drone_repository_is_leeg() {
        droneRepository.deleteAll();
    }

    @When("ik een drone toevoeg met serienummer {string} en model {string}")
    public void ik_een_drone_toevoeg(String serienummer, String model) {
        // On crée un drone avec des valeurs par défaut pour les autres champs
        Drone d = new Drone(serienummer, model, "Test-Status", 100);
        droneRepository.save(d);
    }

    @Then("zou de drone repository {int} drone moeten bevatten")
    public void zou_de_drone_repository_zoveel_moeten_bevatten(int aantal) {
        long count = droneRepository.count();
        Assertions.assertEquals(aantal, count);
    }

    @Then("de drone met serienummer {string} moet bestaan")
    public void de_drone_met_serienummer_moet_bestaan(String serienummer) {
        List<Drone> all = droneRepository.findAll();
        boolean found = false;
        for (Drone d : all) {
            if (d.getSerienummer().equals(serienummer)) {
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found, "Drone met serienummer " + serienummer + " niet gevonden!");
    }
}