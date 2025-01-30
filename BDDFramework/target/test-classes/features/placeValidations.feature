Feature: validating Place API's

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
Given add place payload with "<name>", "<language>", "<address>" 
When user calls "addPlaceAPI" with "POST" http request
Then api call is success with status code 200
And the "status" in response body is "OK"
And the "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:
|name					|language		|address|
|mgkacademy		|English-UK	|Sydney	|
|mgkacademyrmd|English-IN	|India	|

@DeletePlace
Scenario: Verify if delete place functionality is working

Given deletePlace payload
When user calls "deletePlaceAPI" with "POST" http request
Then api call is success with status code 200
And the "status" in response body is "OK"