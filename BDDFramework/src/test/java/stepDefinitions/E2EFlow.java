package stepDefinitions;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacePojo;
import resources.APIResources;
import resources.TestDataBuild;
import resources.TestUtils;

public class E2EFlow extends TestUtils {
	
	public static TestDataBuild tdb;
	public RequestSpecification req;
	public ResponseSpecification respSpec;
	public Response res;
	public String response;
	public JsonPath js;
	public static String place_Id;
	
	@Given("add place payload with {string}, {string}, {string}")
	public void add_place_payload_with(String name, String language, String address) throws Exception {
	
		System.out.println("Git Practice");
		System.out.println("Git Practice1");
		System.out.println("Git Practice2");
		
		System.out.println("Git Practice3");
		System.out.println("Git Practice4");
		
		tdb=new TestDataBuild();
		AddPlacePojo payloadAddPlace = tdb.addPlacePayload(name,language, address);
		
		// Separating request code
		 req = given()
			.spec(requestSpecifications())
			.body(payloadAddPlace)
			.log().all();
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String htttpMethod) {
		
		respSpec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON)
			.build();
		
		if(htttpMethod.equalsIgnoreCase("POST"))
		{
			res = req.when()
					.post(APIResources.valueOf(resource).getResource());
		}
		else if(htttpMethod.equalsIgnoreCase("GET"))
		{
			res = req.when()
					.get(APIResources.valueOf(resource).getResource());
		}
		else if(htttpMethod.equalsIgnoreCase("DELETE"))
		{
			res = req.when()
					.delete(APIResources.valueOf(resource).getResource());
		}
	}

	@Then("api call is success with status code {int}")
	public void api_call_is_success_with_status_code(int statusCode) {
		
		Assert.assertEquals(statusCode, res.getStatusCode());
	}

	@Then("the {string} in response body is {string}")
	public void the_in_response_body_is(String key, String value) {
		response=res.asPrettyString();
		
		js=TestUtils.getJsonPath(response);
		String keyVal=js.getString(key);
		Assert.assertEquals(keyVal, value);
	}
	

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resource) throws Exception {
		
		place_Id=js.get("place_id");
		
		req=given() 
		.spec(requestSpecifications())
		.queryParam("place_id", place_Id);
		
		user_calls_with_http_request(resource, "GET");
	}
	
	@Given("deletePlace payload")
	public void delete_place_payload() throws Exception {
		System.out.println("place_id : "+place_Id);
		req=given() 
		.spec(requestSpecifications())
		.body(TestDataBuild.deletePlacePayload(place_Id));
	}
}
