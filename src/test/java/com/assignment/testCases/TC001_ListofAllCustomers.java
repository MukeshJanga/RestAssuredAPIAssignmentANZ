
/******************************************************
Test Case: GET list of customers 
Base URI:   http://localhost:4547/ 
Request Type: GET
End point: Blog.Api/Customers 

**********************************************************/

package com.assignment.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.assignment.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class TC001_ListofAllCustomers extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	String id = "1111";
	String email = "testerA@abc.com";
	String first_name = "testerBFirst";
	String last_name = "testerBLast";

	@BeforeClass
	void createEmployee() throws InterruptedException {
		logger.info("********* Started List All Customers  **********");

		RestAssured.baseURI = "http://localhost:4547/";
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. 
		// pairs using the put method

		JSONObject requestParams = new JSONObject();
		requestParams.put("email", email);
		requestParams.put("id", id);
		requestParams.put("first_name", first_name);
		requestParams.put("last_name", last_name);

		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.request(Method.GET, "Blog.Api/Customers");
		
	}

	@Test
	void checkResposeBodyofAllCustomers() {
		String responseBody = response.getBody().asString();
		System.out.println("response Body is :" + responseBody);
		Assert.assertEquals(responseBody.contains(id), true);
		Assert.assertEquals(responseBody.contains(email), true);
		Assert.assertEquals(responseBody.contains(first_name), true);
		Assert.assertEquals(responseBody.contains(last_name), true);
	}
	  
	 @AfterClass void tearDown() {
	 logger.info("*********  Finished List of All Customers **********"); }
}
