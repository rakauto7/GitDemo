package resources;
//enum is special class in java which has a collections of constants or methods
public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	APIResources(String resource)//constructor
	{
		this.resource=resource;//the argument is loaded from resource to this.resource (global or current class variable)
	}
	
	public String getResource()
	{
		return resource;
	}

}
