Feature: Drone API

  Scenario: Een drone toevoegen via de REST API
    Given de applicatie draait
    When ik een POST request stuur naar "/drones" met serienummer "API-DRONE"
    Then krijg ik een HTTP 201 Created response
    And bevat de response de link naar de drone

