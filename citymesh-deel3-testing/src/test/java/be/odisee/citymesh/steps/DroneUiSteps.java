package be.odisee.citymesh.steps;

import be.odisee.citymesh.pageobjects.DronePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.springframework.boot.test.web.server.LocalServerPort; // Import

public class DroneUiSteps {

    @LocalServerPort // <-- On récupère le vrai port ici
    private int port;

    private WebDriver driver;
    private DronePage dronePage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        dronePage = new DronePage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("ik ben op de pagina om een drone toe te voegen")
    public void ik_ben_op_de_pagina() {
        // On pointe vers le Frontend Vue.js
        driver.get("http://localhost:5173");
    }

    @When("ik het serienummer {string} invul")
    public void ik_het_serienummer_invul(String nummer) {
        dronePage.enterSerienummer(nummer);
    }

    @When("ik op de knop {string} klik")
    public void ik_op_de_knop_klik(String knop) {
        dronePage.clickSave();
    }

    @Then("zie ik de nieuwe drone in de lijst")
    public void zie_ik_de_nieuwe_drone() {
        Assertions.assertTrue(dronePage.isDroneInList());
    }
}