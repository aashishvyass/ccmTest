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
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CCMApiTest {

	Response response;

	public String tokenAuth;
	public String responseAuth;

	@BeforeMethod()
	public void baseUrl() {
		RestAssured.baseURI = "http://52.163.247.87/api/v1";
	}

	@Test(priority = 1)

	public void brandType() {
		try {

			RequestSpecification httpRequest = RestAssured.given();

			Response response = httpRequest.request(Method.GET, "/brand/types");

			String responseBody = response.getBody().asString();
			int statusCode = response.getStatusCode();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */,
					"Correct status code returned");
			System.out.println(statusCode);
			System.out.println("Brand Type: " + responseBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Brand Type API body  : " + responseBody);
		}
	}

	@Test(priority = 2)
	public void getcountry() {
		try {

			RequestSpecification httpRequest = RestAssured.given();

			Response response = httpRequest.request(Method.GET, "/countries");

			String responseBody = response.getBody().asString();

			int statusCode = response.getStatusCode();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Get Country: " + responseBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Get Country API body: " + responseBody);
		}
	}

	@Test(priority = 3)
	public void categoryType()

	{
		try {

			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.request(Method.GET, "/category/types");
			String responseBody = response.getBody().asString();

			int statusCode = response.getStatusCode();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("CATEGORY TYPE: " + responseBody);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Category type: " + responseBody);
		}
	}

	@Test(priority = 4, enabled = false)
	public void submitFeedback() throws JSONException

	{
		try {

			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();
			requestParams.put("feedbackTypeId", "2");
			requestParams.put("email", "anil.anjana+994@ranosys.com");
			requestParams.put("description", "test 4");

			request.body(requestParams.toString());
			request.header(new Header("Content-Type", "application/json"));
			Response response = request.post("/feedback/submit");

			int statusCode = response.getStatusCode();

			String responseBody = response.getBody().asString();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */,
					"Correct status code returned");
			System.out.println(statusCode);
			System.out.println("Submit Feedback: " + responseBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Submit Feedback: " + responseBody);
		}
	}

	@Test(priority = 5)
	public void registerUser() throws JSONException {

		try {

			RequestSpecification request = RestAssured.given();
			JSONObject requestParams = new JSONObject();

			requestParams.put("email", "anil.anjana+124@ranosys.com");
			requestParams.put("password", "Password@123");
			requestParams.put("mobileCountryId", "1");
			requestParams.put("mobileNumber", "9898989898");
			requestParams.put("fullName", "Anil Anjana");
			requestParams.put("isPrivacyPolicyAgreed", "1");
			requestParams.put("isTermsConditionAgreed", "1");
			requestParams.put("isCcmShareholder", "0");
			requestParams.put("companyName", "");
			requestParams.put("isContractor", "0");
			requestParams.put("contractorCompanyName", "");

			request.body(requestParams.toString());

			request.header(new Header("Content-Type", "application/json"));
			response = request.post("/user/register");

			int statusCode = response.getStatusCode();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			String responseBody = response.getBody().asString();

			System.out.println("Register User: " + responseBody);
			System.out.println(statusCode);
		} catch (AssertionError e) {

			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("registerUser : " + responseBody);

		}

	}

	@Test(priority = 6)

	public void ccmLogin() throws JSONException {
		try {

			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();

			requestParams.put("username", "varsha.gupta+1@ranosys.com");
			requestParams.put("password", "Pass@123");

			request.body(requestParams.toString());

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"), new Header("Cache-Control", "no-cache") };

			request.headers(new Headers(headers));
			Response response = request.post("/login");

			String responseBody = response.getBody().asString();

			int statusCode = response.getStatusCode();

			/*
			 * String responseBody1 = response.getBody().asString();
			 * System.out.println("Token is =" + responseBody1);
			 */

			JsonPath jsonPathEvaluator = response.jsonPath();

			tokenAuth = jsonPathEvaluator.get("token");
			System.out.println(tokenAuth);

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Login API " + responseBody);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Loging API : " + responseBody);
		}
	}

	@Test(priority = 7)
	public void myRewards() throws JSONException {
		try {
			// RestAssured.baseURI = "http://52.163.247.87/api/v1/appuser/mypoints";
			RequestSpecification request = RestAssured.given();

			// JSONObject requestParams = new JSONObject();

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),

					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };

			request.headers(new Headers(headers));
			Response response = request.get("/appuser/mypoints");

			String responseBody = response.getBody().asString();
			System.out.println("My Rewards" + responseBody);

			int statusCode = response.getStatusCode();
			System.out.println(statusCode);

			// String responseBody1 = response.getBody().asString();
			// System.out.println("Token is =" + responseBody1);

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("My Rewards" + responseBody);

		} catch (AssertionError e)

		{
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("My Reward API failed with body: " + responseBody);
		}
	}

	@Test(priority = 32)
	public void logOut() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),

					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));

			Response response = request.post("/appuser/logout");

			int statusCode = response.getStatusCode();
			System.out.println(statusCode);

			String responseBody = response.getBody().asString();

			System.out.println("Logout " + responseBody);

			Assert.assertEquals(statusCode, 200);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Logout API failed with body: " + responseBody);
		}
	}

	@Test(priority = 8)

	public void updatedevicedetails() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));

			Response response = request.post("/updatedevicedetails");

			int statusCode = response.getStatusCode();

			String responseBody = response.getBody().asString();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Update device detail " + responseBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Update device detail API failed with body:" + responseBody);

		}
	}

	@Test(priority = 9)
	public void forgotpassword() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requestParams = new JSONObject();

			requestParams.put("email", "anil.anjana+124@ranosys.com");

			request.body(requestParams.toString());

			request.header(new Header("Content-Type", "application/json"));
			response = request.post("/forgotpassword");

			int statusCode = response.getStatusCode();
			String responseBody = response.getBody().asString();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Foggot Passwod" + responseBody);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Update device detail API failed with body:" + responseBody);
		}
	}

	@Test(priority = 10)

	public void myprofile() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Authorization", "Bearer " + tokenAuth),
					new Header("X-Requested-With", "XMLHttpRequest"), new Header("Cache-Control", "no-cache") };

			request.headers(new Headers(headers));
			Response response = request.get("/appuser/profile/view");

			String responseBody = response.getBody().asString();

			int statusCode = response.getStatusCode();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("My Profile " + responseBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Update device detail API failed with body:" + responseBody);
		}
	}

	@Test(priority = 11)
	public void updateprofile() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requestParams = new JSONObject();

			requestParams.put("userId", "144");
			requestParams.put("mobileCountryId", "anil.anjana@ranosys.com");
			requestParams.put("mobileCountryId", "1");
			requestParams.put("mobileNumber", "9898989898");
			requestParams.put("verificationCode", "9898");
			requestParams.put("fullName", "Anil Anjana");
			requestParams.put("companyName", "Ranosys");
			requestParams.put("address1", "1");
			requestParams.put("address2", "0");
			requestParams.put("city", "");
			requestParams.put("postalCode", "0");
			requestParams.put("countryId", "");

			request.body(requestParams.toString());

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Authorization", "Bearer " + tokenAuth),
					new Header("X-Requested-With", "XMLHttpRequest"), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.headers(new Headers(headers));

			response = request.post("/appuser/profile/update");

			int statusCode = response.getStatusCode();
			String responseBody = response.getBody().asString();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Register user" + responseBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Update Profile API failed with body:" + responseBody);
		}
	}

	@Test(priority = 12)
	public void changepassword() throws JSONException {
		try {
			// RestAssured.baseURI = "http://52.163.247.87/api/v1/user/register";
			RequestSpecification request = RestAssured.given();
			JSONObject requestParams = new JSONObject();

			requestParams.put("currentPassword", "Pass@1234");
			requestParams.put("newPassword", "Pass@123");

			request.body(requestParams.toString());

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.post("appuser/changepassword");

			int statusCode = response.getStatusCode();

			String responseBody = response.getBody().asString();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("changepassword" + responseBody);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Change Password API failed with body:" + responseBody);
		}
	}

	@Test(priority = 13)
	public void sendverificationlink() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			// JSONObject requestparams = new JSONObject();
			// request.body(requestParams.toString());
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.post("appuser/sendverificationlink");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);

			System.out.println(statusCode);
			System.out.println("sendverificationlink" + statusBody);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Send verification link API failed with body:" + responseBody);
		}
	}

	@Test(priority = 14)
	public void productlist() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			/*
			 * requstparams.put("first", "0"); requstparams.put("rows", "10");
			 */

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "branchCode");
			firstFilterJsonObject.put("value", new String[] { "sha", "mon" });
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "categoryCode");
			secondFilterJsonObject.put("value", new String[] { "Ap", "Bp" });
			secondFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "productCode");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "brandCode");
			firstextraFilters.put("value", new String[] {});
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "brandCode");
			secondextraFilters.put("value", new String[] {});
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
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("product/types");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Product list" + statusBody);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Product list API failed with body:" + responseBody);
		}

	}

	@Test(priority = 15)
	public void productimage() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			/*
			 * JSONObject requstparams = new JSONObject(); requstparams.put("productCode",
			 * "ZZZ");
			 */

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("/product/image/list?productCode=ZCARD");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Product Image" + statusBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Product image API failed with body:" + responseBody);
		}
	}

	@Test(priority = 16)
	public void basicpages() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("/pages");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Basic pages" + statusBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Basic Pages API failed with body:" + responseBody);
		}
	}

	@Test(priority = 17)

	public void getfeedbacktype() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("/feedback/types");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Get feedback type" + statusBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Get Feedback API failed with body:" + responseBody);
		}
	}

	@Test(priority = 18)
	public void giftdetail() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("appuser/rewards/giftdetail");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Gift detail" + statusBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Gift Detail API failed with body:" + responseBody);
		}
	}

	@Test(priority = 19)
	public void mypoints() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("/appuser/mypoints");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();
			System.out.println(statusCode);
			System.out.println("My point" + statusBody);
			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("My point" + statusBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("My Point API failed with body:" + responseBody);
		}
	}

	@Test(priority = 20)
	public void mygifts() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("/appuser/mygift");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();
			System.out.println(statusCode);
			System.out.println("My gifts" + statusBody);
			Assert.assertEquals(statusCode, 200);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("My Gift API failed with body:" + responseBody);
		}

	}

	@Test(priority = 21)
	public void advertisementsdetail() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("advertisements/detail?id=166");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Advertisement detail " + statusBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Advertisements detail API failed with body:" + responseBody);
		}

	}

	@Test(priority = 22)
	public void advertisementsdelete() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.delete("/admin/advertisements/166");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Advertisement delete" + statusBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Advertisements detail API failed with body:" + responseBody);
		}

	}

	@Test(priority = 23)
	public void promotionlist() throws JSONException {
		try {

			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "promoCode");
			firstFilterJsonObject.put("value", new String[] { "sha", "mon" });
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "description");
			secondFilterJsonObject.put("value", new String[] { "Ap", "Bp" });
			secondFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "promoCode");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "promoCode");
			firstextraFilters.put("value", new String[] {});
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "brandCode");
			secondextraFilters.put("value", new String[] {});
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
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/promotion/list");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Promotion List" + statusBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Promotion list API failed with body:" + responseBody);
		}

	}

	@Test(priority = 24)
	public void promotionproductlist() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "productCode");
			firstFilterJsonObject.put("value", new String[] { "sha", "mon" });
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "brandDescription");
			secondFilterJsonObject.put("value", new String[] { "Ap", "Bp" });
			secondFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "");
			firstextraFilters.put("value", new String[] {});
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "");
			secondextraFilters.put("value", new String[] {});
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
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/promotions/product/list?promoCode=123");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Promotion Product list" + statusBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Promotion product list  API failed with body:" + responseBody);
		}

	}

	@Test(priority = 25)
	public void userpoint() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "");
			firstFilterJsonObject.put("value", "");
			firstFilterJsonObject.put("matchMode", "");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "");
			secondFilterJsonObject.put("value", "");
			secondFilterJsonObject.put("matchMode", "");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "createdAt");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "");
			firstextraFilters.put("value", "");
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "");
			secondextraFilters.put("value", "");
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
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/appuser/userpoint/list");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("My point transaction history is" + statusBody);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("User Point  API failed with body:" + responseBody);
		}
	}

	@Test(priority = 26)
	public void usergiftlist() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "");
			firstFilterJsonObject.put("value", "");
			firstFilterJsonObject.put("matchMode", "");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "");
			secondFilterJsonObject.put("value", "");
			secondFilterJsonObject.put("matchMode", "");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "createdAt");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "");
			firstextraFilters.put("value", "");
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "");
			secondextraFilters.put("value", "");
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
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/appuser/usergift/list");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("User Gift list" + statusBody);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("User Point  API failed with body:" + responseBody);
		}

	}

	@Test(priority = 27)
	public void productstore() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "name");
			firstFilterJsonObject.put("value", "");
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "address");
			secondFilterJsonObject.put("value", "");
			secondFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "name");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "");
			firstextraFilters.put("value", "");
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "");
			secondextraFilters.put("value", "");
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
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/store/types");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Store Type API Body" + statusBody);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Product Store API failed with body:" + responseBody);
		}

	}

	@Test(priority = 28)
	public void advertisementgetproduct() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "productName");
			firstFilterJsonObject.put("value", "a");
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			requstparams.put("first", "0");
			requstparams.put("rows", "10");

			requstparams.put("filters", filterJsonArray);
			requstparams.put("search", "");

			System.out.println("Json-----");
			System.out.println(requstparams);

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/product/list");

		int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Advertisement Get Product Body " + statusBody);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Advertisement get product API failed with body:" + responseBody);
		}
	}

	@Test(priority = 29)
	public void randomadvertisement() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			requstparams.put("postLoginFlag", "0");
			requstparams.put("excludeAdvertisementId", "");

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/advertisements/random");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Advertisement Get Product Body " + statusBody);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Random advertisement API failed with body:" + responseBody);
		}
	}

	@Test(priority = 30)
	public void advertisementlist() throws JSONException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "name");
			firstFilterJsonObject.put("value", new String[] { "ad" });
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "address");
			secondFilterJsonObject.put("value", new String[] { "Ap" });
			secondFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "name");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "");
			firstextraFilters.put("value", new String[] { "" });
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "");
			secondextraFilters.put("value", new String[] { "" });
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
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/advertisements/list");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Advertisement list " + statusBody);
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Advertisement list API failed with body:" + responseBody);
		}

	}

}
