package resources;

import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class TestUtils {

	public static RequestSpecification requSpec;
	
	public RequestSpecification requestSpecifications() throws Exception 
	{
		if(requSpec==null)
		{
			// to log in a separate text file,
			PrintStream logs=new PrintStream(new File("logs.txt"));
			
			requSpec = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON)
					// logs
					.addFilter(RequestLoggingFilter.logRequestTo(logs))
					.addFilter(ResponseLoggingFilter.logResponseTo(logs))
					.build();
					
			return requSpec;
		}
		return requSpec;
	}
	
	public static String getGlobalValue(String key) throws Exception
	{
		Properties prop=new Properties();
		FileReader fr=new FileReader(".\\src\\test\\java\\resources\\global.properties");
		prop.load(fr);
		String baseUrl=prop.getProperty(key);
		return baseUrl;
	}
	
	public static JsonPath getJsonPath(String str)
	{
		JsonPath js=new JsonPath(str);
		return js;
	}
	
}
