Feature: Validation Place API

  @AddPlace @Regression
  Scenario Outline: Verify is place is being sucessfully added using place API
    Given Add Place payload "<Name>" "<Language>" "<Address>"
    When User call "AddPlaceAPI" with "POST" HTTP request
    Then The API call got sucess with status code 200
    And "status" in response code is "OK"
    And "scope" in response code is "APP"
    And verify place_Id created maps to "<Name>" using "GetPlaceAPI"
    
    Examples:
    |Name		|Language	|Address	|
    |abhouse|English	|Barasat	|
# 	|achouse|Spanish	|Garia		|


@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
 		Given DeletePlace payload
 		When User call "DeletePlaceAPI" with "POST" HTTP request
 		Then The API call got sucess with status code 200
    And "status" in response code is "OK"
    

