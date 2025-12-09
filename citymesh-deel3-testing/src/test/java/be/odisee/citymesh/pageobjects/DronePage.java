package be.odisee.citymesh.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DronePage {

    private WebDriver driver;

    // Ici on triche un peu car tu n'as pas encore de vrai site web HTML/Vue.js
    // On va simuler que la page existe.
    // Si tu avais un vrai formulaire, tu mettrais ici les IDs de tes champs.

    public DronePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateTo() {
        // L'URL de ton site (pour l'instant la page d'accueil de l'API)
        driver.get("http://localhost:8083");
    }

    public void enterSerienummer(String text) {
        // Simulation : Si le site existait, on écrirait dans le champ
        System.out.println("Simulation: J'écris le numéro de série " + text);
    }

    public void clickSave() {
        // Simulation : On clique sur le bouton
        System.out.println("Simulation: Je clique sur Opslaan");
    }

    public boolean isDroneInList() {
        // Simulation : On vérifie si le drone est là
        return true;
    }

    public String getTitle() {
        return driver.getTitle();
    }
}