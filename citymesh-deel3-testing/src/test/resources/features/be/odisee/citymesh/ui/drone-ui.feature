Feature: Drone UI

  Scenario: Een nieuwe drone toevoegen via de website
    Given ik ben op de pagina om een drone toe te voegen
    When ik het serienummer "UI-DRONE" invul
    And ik op de knop "Opslaan" klik
    Then zie ik de nieuwe drone in de lijst