package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefination extends Utils{
	
	RequestSpecification req ;
	ResponseSpecification Responsespec;
	Response response;
	TestDataBuild testdata=new TestDataBuild();
	static String place_id;
	
	@Given("Add Place payload {string} {string} {string}")
	public void add_place_payload(String Name, String Language, String Address) throws IOException {		
 
		req =given().spec(RequestSpecifications()).body(testdata.addPlacePayload(Name,Language,Address));
		
	}
	@When("User call {string} with {string} HTTP request")
	public void user_call_with_http_request(String resource, String httpmethod) {
			
		APIResources resourseAPI=APIResources.valueOf(resource);
		System.out.println(resourseAPI.getResource());
		
		Responsespec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 if(httpmethod.equalsIgnoreCase("POST"))
		 response=req.when().post(resourseAPI.getResource());
		 else if (httpmethod.equalsIgnoreCase("GET"))
		 response=req.when().get(resourseAPI.getResource());	 
			
	}
	@Then("The API call got sucess with status code {int}")
	public void the_api_call_got_sucess_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response code is {string}")
	public void in_response_code_is(String Key, String Expectedvalue) {
		assertEquals(getJsonPath(response,Key),Expectedvalue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String Expectedname, String resource) throws IOException   {
		place_id=getJsonPath(response,"place_id");
		req =given().spec(RequestSpecifications()).queryParam("place_id", place_id);
		user_call_with_http_request(resource,"GET");
		String Actualname=getJsonPath(response,"name");
		assertEquals(Actualname,Expectedname);
	}
	

	@Given("DeletePlace payload")
		public void delete_place_payload() throws IOException {
		req =given().spec(RequestSpecifications()).body(testdata.deleteplacePayload(place_id));
	}
}
