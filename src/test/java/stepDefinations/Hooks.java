package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenarion() throws IOException	{
		
	stepDefination m= new stepDefination();
	if(stepDefination.place_id==null)//as place_id is static variable, object is not req to call the variable , classname is required.
	{
	m.add_place_payload("TThouse", "Italic", "garia");
	m.user_call_with_http_request("AddPlaceAPI", "POST");
	m.verify_place_id_created_maps_to_using("TThouse", "GetPlaceAPI");
	

	}
}
}
