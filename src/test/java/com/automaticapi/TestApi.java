package com.automaticapi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class TestApi {

	public static void main(String[] args) {
		
		//post method    //resource="maps/api/place/update/json"

		RestAssured.baseURI = "http://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
						+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Frontline house\",\r\n"
						+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
						+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n"
						+ "    \"shoe park\",\r\n" + "    \"shop\"\r\n" + "  ],\r\n"
						+ "  \"website\": \"http://google.com\",\r\n" + "  \"language\": \"French-IN\"\r\n" + "}")
				.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(response);
		
		JsonPath jp = new JsonPath(response);
		String placeid = jp.get("place_id");
		System.out.println(placeid);
		
		//put method
		
		String put= given().log().all().queryParam("key", "qaclick123").headers("Content-Type", "application/json")
		 .body("{\r\n"
		 		+ "\"place_id\":\""+placeid+"\",\r\n"
		 		+ "\"address\":\"70 winter walk,Usa\",\r\n"
		 		+ "\"key\":\"qaclick123\"\r\n"
		 		+ "}").when().put("maps/api/place/add/json")
		 .then().log().all().assertThat().statusCode(200).extract().response().asString();
		 
		// get method
			String get= given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid)
			.header("Content-Type", "application/json").when().get("maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200).extract().response().asString();	
			
			
		
		
		
		}

}
