package com.groupid.firstmavenproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CCM_Backend {
	public String tokenAuth;
	
	@BeforeMethod
	public void backendURI()
	{
		RestAssured.baseURI = "http://52.163.247.87/api/v1/admin";	
	}
	
	@Test(priority = 1)
	public void adminlogin() throws JSONException
	{
		// RestAssured.baseURI = "http://52.163.247.87/api/v1/login";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();

		requestParams.put("username", "nagesh.kumar@ranosys.com");
		requestParams.put("password", "Password@3");

		request.body(requestParams.toString());

		Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
				new Header("X-Requested-With", "XMLHttpRequest"), new Header("Cache-Control", "no-cache") };

		request.headers(new Headers(headers));
		Response response = request.post("/login");

		String responseBody = response.getBody().toString();

		int statusCode = response.getStatusCode();

		String responseBody1 = response.getBody().asString();
		System.out.println("Token is =" + responseBody1);

		JsonPath jsonPathEvaluator = response.jsonPath();

		tokenAuth = jsonPathEvaluator.get("token");
		System.out.println(tokenAuth);
		System.out.println("Admin " + responseBody);
		Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
		System.out.println(statusCode);
		
	}
	
	@Test(priority = 2)
	public void feedbacklisting() throws JSONException
	{
		RequestSpecification request  = RestAssured.given();
		JSONObject requstparams = new JSONObject();
		
		JSONArray filterJsonArray = new JSONArray();
		JSONObject firstFilterJsonObject = new JSONObject();
		firstFilterJsonObject.put("fieldName", "email");
		firstFilterJsonObject.put("value","");
		firstFilterJsonObject.put("matchMode", "like");
		filterJsonArray.put(firstFilterJsonObject);

		JSONObject secondFilterJsonObject = new JSONObject();
		secondFilterJsonObject.put("fieldName", "feedbackType");
		secondFilterJsonObject.put("value", "Ap");
		secondFilterJsonObject.put("matchMode", "like");
		filterJsonArray.put(secondFilterJsonObject);
 
		
		JSONArray multisort = new JSONArray();
		JSONObject firstmultisort = new JSONObject();
		firstmultisort.put("field", "email");
		firstmultisort.put("order", "-1");
		multisort.put(firstmultisort);

		JSONArray extrafilters = new JSONArray();
		JSONObject firstextraFilters = new JSONObject();
		firstextraFilters.put("fieldName", "");
		firstextraFilters.put("value", "");
		extrafilters.put(firstextraFilters);

		JSONObject secondextraFilters = new JSONObject();
		secondextraFilters.put("fieldName", "");
		secondextraFilters.put("value","");
		extrafilters.put(secondextraFilters);

		requstparams.put("first", "0");
		requstparams.put("rows", "10");

		requstparams.put("filters", filterJsonArray);
		requstparams.put("multiSortMetas", multisort);
		requstparams.put("extraFilters", extrafilters);
		requstparams.put("search", "");

		System.out.println("Json-----");
		System.out.println(requstparams);

		Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
				new Header("X-Requested-With", "XMLHttpRequest"), new Header("X-Authorization", "Bearer "+tokenAuth),
				new Header("Cache-Control", "no-cache") };
		request.headers(new Headers(headers));
		request.body(requstparams.toString());

		Response response = request.post("/feedback/list");
				
		int statusCode = response.getStatusCode();
		String statusBody = response.getBody().asString();
		System.out.println(statusCode);
		System.out.println("Feedback list API Body" + statusBody);
		Assert.assertEquals(statusCode, 200);
	
	}
	
	@Test(priority = 3)
	public void addpage() throws JSONException
	{
		RequestSpecification request = RestAssured.given();
		JSONObject requstparams = new JSONObject();
		requstparams.put("page", "");
		requstparams.put("email", "");
		requstparams.put("contactNumber", "");
		requstparams.put("description", "");
		
		Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
				new Header("X-Requested-With", "XMLHttpRequest"), new Header("X-Authorization", "Bearer "+tokenAuth),
				new Header("Cache-Control", "no-cache") };
		request.headers(new Headers(headers));
		request.body(requstparams.toString());
		
		Response response = request.post("/pages/add");
		
		int statusCode = response.getStatusCode();
		String statusBody = response.getBody().asString();
		System.out.println(statusCode);
		System.out.println("Add Page API body" + statusBody);
		Assert.assertEquals(statusCode, 200);
	
	}
	
	@Test(priority = 4)
	public void addadvertisement() throws JSONException
	{
		RequestSpecification request = RestAssured.given();
		JSONObject requstparams = new JSONObject();
		
		requstparams.put("name", "API Promotion");
		requstparams.put("startDate", "23/01/2018");
		requstparams.put("endDate", "23/01/2018");
		requstparams.put("productCode", "LF1");
		requstparams.put("image", "C:\\Users\\User\\Desktop\\11.png");
		requstparams.put("bannerImage", "");
		requstparams.put("postLoginFlag", "0");
		requstparams.put("bannerImage", "");
		
		
		Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
				new Header("X-Requested-With", "XMLHttpRequest"), new Header("X-Authorization", "Bearer "+tokenAuth),
				new Header("Cache-Control", "no-cache") };
		request.headers(new Headers(headers));
		request.body(requstparams.toString());
		
		Response response = request.post("/advertisements/add");
		
		int statusCode = response.getStatusCode();
		String statusBody = response.getBody().asString();
		System.out.println(statusCode);
		System.out.println("Add Page API body" + statusBody);
		Assert.assertEquals(statusCode, 200);
	}
	
	
	@Test(priority = 4)
	public void deleteadvertisement() throws JSONException
	{
		RequestSpecification request = RestAssured.given();
		

		Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
				new Header("X-Requested-With", "XMLHttpRequest"), new Header("X-Authorization", "Bearer "+tokenAuth),
				new Header("Cache-Control", "no-cache") };
		request.headers(new Headers(headers));
		
		
		Response response = request.delete("/advertisements/001");
		
		int statusCode = response.getStatusCode();
		String statusBody = response.getBody().asString();
		System.out.println(statusCode);
		System.out.println("Delete Advertisement" + statusBody);
		Assert.assertEquals(statusCode, 200);
	}
		
	}

