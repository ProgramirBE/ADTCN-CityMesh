package be.odisee.citymesh.steps;

import be.odisee.citymesh.domain.Drone;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DroneApiSteps {

    @Autowired
    private TestRestTemplate restTemplate; // Outil pour faire des appels HTTP

    private ResponseEntity<String> response; // Pour stocker la réponse

    @Given("de applicatie draait")
    public void de_applicatie_draait() {
        // Rien à faire, CucumberSpringConfiguration a déjà démarré l'app
    }

    @When("ik een POST request stuur naar {string} met serienummer {string}")
    public void ik_een_post_request_stuur(String endpoint, String serienummer) {
        Drone drone = new Drone(serienummer, "API-Model", "Test", 99);
        // On envoie le drone à l'API
        response = restTemplate.postForEntity(endpoint, drone, String.class);
    }

    @Then("krijg ik een HTTP {int} Created response")
    public void krijg_ik_een_http_created_response(int statusCode) {
        Assertions.assertEquals(HttpStatus.valueOf(statusCode), response.getStatusCode());
    }

    @Then("bevat de response de link naar de drone")
    public void bevat_de_response_de_link_naar_de_drone() {
        String body = response.getBody();
        Assertions.assertNotNull(body);
        Assertions.assertTrue(body.contains("_links"), "Geen links gevonden in response");
        Assertions.assertTrue(body.contains("self"), "Geen self-link gevonden");
    }
}