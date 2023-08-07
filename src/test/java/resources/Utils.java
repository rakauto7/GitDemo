package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification Requestspec;
	public RequestSpecification RequestSpecifications() throws IOException
	{	
		if(Requestspec==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));//FOS is to WRITE the FILE
		Requestspec=new RequestSpecBuilder().setBaseUri(getGlobalValue("BaseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return Requestspec;
		}
		return Requestspec;
	}
	
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop=new Properties();//properties class can scan any file having .properties extension.
		FileInputStream fis=new FileInputStream("C:\\java_practice\\APIFramework\\src\\test\\java\\resources\\global.properties");//FIS is to READ the FILE from the path
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String  key)
	{
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();
	}
}
