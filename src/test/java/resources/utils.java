package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class utils {

	public static RequestSpecification req_spec;

	public RequestSpecification requestSpecification() throws IOException {
		
		if (req_spec==null) {

		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

		req_spec = new RequestSpecBuilder().setBaseUri(getGlobalData("BaseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(RequestLoggingFilter.logRequestTo(log))
				.setContentType(ContentType.JSON).build();

		return req_spec;
		
		}
		return req_spec;
		
	}

	public static String getGlobalData(String Key) throws IOException {

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\HP\\eclipse-workspace\\APIFramework\\src\\test\\resources\\globaldData.properties");

		prop.load(file);
		return prop.getProperty(Key);

	}
}
