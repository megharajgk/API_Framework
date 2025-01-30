package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Exception
	{
		System.out.println("Git Practice");
		System.out.println("Git Practice1");
		System.out.println("Git Practice2");
		
		System.out.println("Git Practice3");
		System.out.println("Git Practice4");
		
		E2EFlow sd=new E2EFlow();
		if(E2EFlow.place_Id==null)
		{
			sd.add_place_payload_with("mgkrmd", "Kannada", "Mysore");
			sd.user_calls_with_http_request("addPlaceAPI", "POST");
//			sd.verify_place_id_created_maps_to_using("mgkrmd", "getPlaceAPI");
		}
	}

}
