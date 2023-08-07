	package resources;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String Name, String Language, String Address)
	{
	AddPlace p=new AddPlace();
	p.setAccuracy(50);
	p.setName(Name);
	p.setLanguage(Language);
	p.setAddress(Address);
	p.setPhone_number("(+91) 983 893 3937");
	p.setWebsite("http://google.com");
	
	List<String> mylisttypes=new ArrayList<String>();
	mylisttypes.add("shoe park");
	mylisttypes.add("shop");
	p.setTypes(mylisttypes);
	
	Location L=new Location();
	L.setLat(-38.383494);
	L.setLng(33.427362);
	p.setLocation(L);
	return p;
	}
	
	public String deleteplacePayload(String placeID)
	{
		return "{\r\n" + 
				"\"place_id\":\""+placeID+"\"\r\n" + 
				"}";
	}
}
