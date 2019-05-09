package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.cucumber.listener.Reporter;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;


public class StepDefinition {

	private String BaseURI;
	private String URI;
	private String Token;
	private JSONObject requestparams;
	
	@Given("^I am requesting \"([^\"]*)\"\\.$")
	public void i_am_requesting(String arg1) throws Throwable {
	    
	    
	    File file = new File("D:\\SeleniumBaseProjectRepository\\AQM.APITest.com\\src\\test\\java\\Main\\DataFile.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BaseURI = prop.getProperty(arg1);
		
		Reporter.addStepLog(" Base URI is " +BaseURI);
		System.out.println(BaseURI);
		
		}

@When("^I am on \"([^\"]*)\" resource\\.$")
public void i_am_on_resource(String arg1) throws Throwable {
		URI = BaseURI + arg1;
		System.out.println(URI);
		Reporter.addStepLog(" Request URI is " +URI);
	}
	
@When("^I go to \"([^\"]*)\" with access token as \"([^\"]*)\"\\.$")
public void i_go_to_with_access_token_as(String arg1, String arg2) throws Throwable {
		URI = BaseURI+arg1;
		System.out.println(URI);
		Token = arg2;
		System.out.println(Token);
		Reporter.addStepLog(" Request URI is " +URI);
		Reporter.addStepLog(" Token is " +Token);
	}
	
	
@Then("^I am sending the GET request and get \"([^\"]*)\" with following parameters\\.$")
public void i_am_sending_the_GET_request_and_get_with_following_parameters
(String arg1, DataTable arg2) throws Throwable {
		
		System.out.println(URI);
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.request(Method.GET, URI);
		
		int code = Integer.parseInt(arg1);
		
		Assert.assertEquals(code, response.getStatusCode());
		
		
		Reporter.addStepLog(" Response Code is " +response.getStatusCode());
		
		JsonPath jsonPathEvaluator = response.jsonPath();

		System.out.println("This is " + jsonPathEvaluator.get("page"));
		
		List<List<String>> data = arg2.raw();
				
		for (int i = 0; i < data.get(0).size(); i++) {
			System.out.println(data.get(0).get(i));

			System.out.println("This is " + jsonPathEvaluator.get(data.get(0).get(i)) + " And " + data.get(0).get(i));

			Assert.assertEquals("" + data.get(1).get(i), "" + jsonPathEvaluator.get(data.get(0).get(i)).toString());

			Reporter.addStepLog("This is from Json " + jsonPathEvaluator.get(data.get(0).get(i)) + " for " + data.get(0).get(i));
			
			System.out.println(data.get(1).get(i));

		}long time = TimeUnit.MILLISECONDS.toSeconds(response.getTime());  
		System.out.println("This is time Taken in seconds :"+ time);
		Reporter.addStepLog("This is time Taken in seconds :"+ time);

	}

@Then("^I am sending the GET request and get \"([^\"]*)\" with following details in"
	+ " parentnode \"([^\"]*)\" on position \"([^\"]*)\" for token API and multi values$")
public void i_am_sending_the_GET_request_and_get_with_following_details_in_parentnode_on_position_for_token_API_and_multi_values
(String arg1, String arg2, String arg3, DataTable arg4) throws Throwable {
		System.out.println(URI);
		
		RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer "+Token);
		
		Response response = httpRequest.request(Method.GET, URI);
		
		System.out.println(response.getStatusCode());
		
		int code = Integer.parseInt(arg1);
		
		Assert.assertEquals(code, response.getStatusCode());
		
		Reporter.addStepLog(" Response Code is " +response.getStatusCode());
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		int position = Integer.parseInt(arg3);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		HashMap list1= (HashMap) (((List<List<String>>) jsonPathEvaluator.get(arg2)).get(position));
		System.out.println(list1);
		
		List<List<String>> data = arg4.raw();

		for (int i = 0; i < data.get(0).size(); i++) {
			//System.out.println(data.get(0).get(i));

			// String Value = jsonPathEvaluator.get(data.get(0).get(i)).toString();

			
			
			System.out.println("This is Header " + data.get(0).get(i).toString() + " and this is child "
					+ data.get(1).get(i).toString());
			
			Assert.assertEquals(""+data.get(1).get(i).toString(), ""+list1.get(data.get(0).get(i).toString()));

			Reporter.addStepLog("This is from Json " + list1.get(data.get(0).get(i).toString())
			+ " for " + data.get(0).get(i).toString());
			//Assert.assertEquals(""+data.get(1).get(i),""+ parentnode.get(data.get(0).get(i)));

			// System.out.println(data.get(1).get(i));

		}long time = TimeUnit.MILLISECONDS.toSeconds(response.getTime());  
		System.out.println("This is time Taken in seconds :"+ time);
		Reporter.addStepLog("This is time Taken in seconds :"+ time);
	}
	
@Then("^I am sending the GET request and get \"([^\"]*)\" with following details in parentnode \"([^\"]*)\"\\.$")
public void i_am_sending_the_GET_request_and_get_with_following_details_in_parentnode(int arg1, String arg2, DataTable arg3) throws Throwable {
	RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer "+Token);
	System.out.println(URI);
	Response response = httpRequest.request(Method.GET, URI);

	Assert.assertEquals(arg1, response.getStatusCode());
	Reporter.addStepLog(" Response Code is " +response.getStatusCode());
	JsonPath jsonPathEvaluator = response.jsonPath();

	@SuppressWarnings("rawtypes")
	Map parentnode = ((Map) jsonPathEvaluator.get(arg2));
	
	List<List<String>> data = arg3.raw();

	for (int i = 0; i < data.get(0).size(); i++) {
		System.out.println(data.get(0).get(i));

		// String Value = jsonPathEvaluator.get(data.get(0).get(i)).toString();

		System.out.println("This is " + parentnode.get(data.get(0).get(i).toString()) + " for "
				+ data.get(0).get(i).toString());

		Assert.assertEquals(""+data.get(1).get(i),""+ parentnode.get(data.get(0).get(i)));
		Reporter.addStepLog("This is from Json " + parentnode.get(data.get(0).get(i).toString())
		+ " for " + data.get(0).get(i).toString());
		
		// System.out.println(data.get(1).get(i));

	}long time = TimeUnit.MILLISECONDS.toSeconds(response.getTime());  
	System.out.println("This is time Taken in seconds :"+ time);
	Reporter.addStepLog("This is time Taken in seconds :"+ time);
	}
	
	@Then("^I am sending the GET request and get \"([^\"]*)\" with following details in parentnode \"([^\"]*)\" and child node \"([^\"]*)\"\\.$")
	public void i_am_sending_the_GET_request_and_get_with_following_details_in_parentnode_and_child_node
	(int arg1, String arg2, String arg3, DataTable arg4) throws Throwable {

		RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer "+Token);
		System.out.println(URI);
		Response response = httpRequest.request(Method.GET, URI);
		
		Assert.assertEquals(arg1, response.getStatusCode());
		Reporter.addStepLog(" Response Code is " +response.getStatusCode());
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		@SuppressWarnings("rawtypes")
		Map parentnode = ((Map) jsonPathEvaluator.get(arg2));
		
		@SuppressWarnings("rawtypes")
		Map childnode = ((Map) parentnode.get(arg3));
		
		
		System.out.println(parentnode.get("rateLimit"));
		System.out.println(response.getStatusCode());
	
		System.out.println(childnode.size());
		
		List<List<String>> data = arg4.raw();
		System.out.println("This is data "+(data.get(0).toString().replace("[", "").replace("]", "")));
		
		System.out.println("This is Json data "+ childnode.get(data.get(0).toString().replace("[", "").replace("]", "")));

		for (int i = 0; i < data.get(0).size(); i++) {
			System.out.println(data.get(0).get(i));

			// String Value = jsonPathEvaluator.get(data.get(0).get(i)).toString();

			System.out.println("This is " +  childnode.get(data.get(0).toString().replace("[", "").replace("]", "")) + " for "
					+ data.get(0).get(i).toString());

			Assert.assertEquals(""+data.get(1).get(i),""+  childnode.get(data.get(0).toString().replace("[", "").replace("]", "")));
			Reporter.addStepLog("This is from Json " + childnode.get(data.get(0).get(i).toString())
			+ " for " + data.get(0).get(i).toString());
			// System.out.println(data.get(1).get(i));

		}long time = TimeUnit.MILLISECONDS.toSeconds(response.getTime());  
		System.out.println("This is time Taken in seconds :"+ time);
		Reporter.addStepLog("This is time Taken in seconds :"+ time);
		}

	@When("^I go to \"([^\"]*)\" and post the following parmeters$")
	public void i_go_to_and_post_the_following_parmeters(String arg1, DataTable arg2)
			throws Throwable {
		URI = BaseURI + arg1;
		Reporter.addStepLog("This is URI :"+ URI);
		requestparams = new JSONObject();
		List<List<String>> data = arg2.raw();
		for (int i = 0; i < data.get(0).size(); ++i) {
			requestparams.put(data.get(0).get(i).toString(), data.get(1).get(i).toString());
		}
		System.out.println(requestparams);
		Reporter.addStepLog("This is Request Payload :"+ requestparams);
		
	}
		
	@Then("^I am sending the POST request and get \"([^\"]*)\" with below Bearer Token\\.$")
	public void i_am_sending_the_POST_request_and_get_with_below_Bearer_Token
	(int arg1, DataTable arg2) throws Throwable {
		System.out.println(URI);
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.body(requestparams.toJSONString());
		httpRequest.contentType(ContentType.JSON);
		Response response = httpRequest.post(URI);
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		System.out.println(response.getStatusCode());
		
		Assert.assertEquals(arg1, response.getStatusCode());
		Reporter.addStepLog(" Response Code is " +response.getStatusCode());
		List<List<String>> data = arg2.raw();

		for (int i = 0; i < data.get(0).size(); i++) {
			System.out.println(data.get(0).get(i));

			// String Value = jsonPathEvaluator.get(data.get(0).get(i)).toString();

			System.out.println("This is " + jsonPathEvaluator.get(data.get(0).get(i)) + " And " + data.get(0).get(i));

			Assert.assertEquals("" + data.get(1).get(i), "" + jsonPathEvaluator.get(data.get(0).get(i)).toString());
			
			Reporter.addStepLog("This is from Json " + jsonPathEvaluator.get(data.get(0).get(i).toString())
			+ " for " + data.get(0).get(i).toString());
			
			System.out.println(data.get(1).get(i));

		}long time = TimeUnit.MILLISECONDS.toSeconds(response.getTime());  
		System.out.println("This is time Taken in seconds :"+ time);
		Reporter.addStepLog("This is time Taken in seconds :"+ time);
		}
		}
