package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utilities {
	
	PrintStream log;
	public static RequestSpecification reqSpec;
	
	public String getValueFromGlobal(String key) throws IOException{
		Properties prop = new Properties();
		FileInputStream fin = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/resources/global.properties");
		prop.load(fin);
		
		return prop.getProperty(key);
	}
	
	public RequestSpecification getRequestSpec() throws IOException{
		
		if(reqSpec == null){ //refer lecture number 106
			log  = new PrintStream(new FileOutputStream("logs.txt"));
			reqSpec= new RequestSpecBuilder().setBaseUri(getValueFromGlobal("baseURL"))
					.addHeader("Content-Type","application/json")
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return reqSpec;
		}
		return reqSpec;
		
	}

	public ResponseSpecification getResponseSpec(){
		ResponseSpecification respSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		return respSpec;
	}
	
	public String getJSONFieldValue(Response resp, String key){
		JsonPath js= new JsonPath(resp.asString());
		System.out.println("added in dummmy git");
		System.out.println("added in main proj 1");
		sysout("by architech in develop branch");	
		System.out.println("added in main proj 2");
		return js.get(key).toString();
	}
	
}
