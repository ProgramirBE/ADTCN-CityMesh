Feature: Drone Management Service

  Scenario: Een drone toevoegen via de service
    Given de drone repository is leeg
    When ik een drone toevoeg met serienummer "TEST-S1" en model "Mavic 3"
    Then zou de drone repository 1 drone moeten bevatten
    And de drone met serienummer "TEST-S1" moet bestaan

