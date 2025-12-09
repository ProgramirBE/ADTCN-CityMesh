package be.odisee.citymesh.steps;

import be.odisee.citymesh.CitymeshCrudApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort; // Import important

@CucumberContextConfiguration
@SpringBootTest(classes = CitymeshCrudApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // <-- Changement ici
public class CucumberSpringConfiguration {

    @LocalServerPort
    protected int port; // <-- On récupère le port aléatoire ici

}