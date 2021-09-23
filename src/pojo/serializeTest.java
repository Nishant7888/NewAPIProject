package pojo;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;

public class serializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName("Nishant House");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("49, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		
		//creating new arraylist
		List<String> mylist= new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		p.setTypes(mylist);
		//create object of location class
		serializeLocation l= new serializeLocation();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123")
		.body(p).when().post("/maps/api/place/add/json")//pass the object of AddPlace class as it is having all the json, and its restassured to ready this object and based on the way the pojo class is created, the json will be converted 
		.then().assertThat().statusCode(200).extract().response().asString();
	
		System.out.println(response);
	}

}
