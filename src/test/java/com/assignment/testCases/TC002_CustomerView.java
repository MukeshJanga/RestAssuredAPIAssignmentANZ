
/******************************************************
Test Register new record in database 
Base URI:   http://localhost:4547/ 
Request Type: GET
End point: Blog.Api/1111/CustomerView" 
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

import java.util.Scanner;

import io.restassured.http.ContentType;

public class TC002_CustomerView extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	/*
	 * Customer ID,email, first name,last name should be changed accordingly
	 */

	int[] arr= {1111,2222,3333,4444,5555,6666};
	char[] chrarr= {'A','B','C','D','E','F'};
	
	String id = Integer.toString(arr[4]);
	String email = "tester"+chrarr[4]+"@abc.com";
	String first_name = "tester"+chrarr[4]+"First";
	String last_name = "tester"+chrarr[4]+"Last";

	@BeforeClass
	void createEmployee() throws InterruptedException {
		logger.info("********* Started Customer View **********");

		RestAssured.baseURI = "http://localhost:4547/";
		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		
		httpRequest.body(requestParams.toJSONString());
		String endpoint = "Blog.Api/" + id + "/CustomerView";
		response = httpRequest.request(Method.GET, endpoint);

	}

	@Test
	void checkResposeBodyofCustomerView() {
		String responseBody = response.getBody().asString();
		System.out.println("response Body is :" + responseBody);
		
		System.out.println("customer not found:");
		Assert.assertEquals(responseBody.contains(id), true);
		//Assert.assertEquals(responseBody.contains("Customer details not found."), true);
		
	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished Customer View **********");
	}
}
