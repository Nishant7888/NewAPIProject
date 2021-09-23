import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCourse;
import pojo.WebAutomation;
import pojo.api;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class OauthDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	//Step 1 to go to URL and enter credential and get the code
	//google does not allow to automate the gmail, so we need to manually pass the URL	
	/*System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver_win32\\chromedriver.exe");
	WebDriver d = new ChromeDriver();
	d.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
	d.manage().window().maximize();
	//d.findElement(By.id("identifierId")).sendKeys("nishantgrover96@gmail.com");
	d.findElement(By.cssSelector("input[type='email']")).sendKeys("nishantgrover96@gmail.com");
	d.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
	Thread.sleep(4000);
	d.findElement(By.cssSelector("input[type='password']")).sendKeys("nishant7496");
	d.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
	String url= d.getCurrentUrl();//to get the URL
	*/
		String[] courseTitles= {"Selenium Webdriver Java","Cypress","Protractor"};
		String url="4%2F0AX4XfWj9sZXnlzacD--AY9j8UeG5qswzXiLUIGV6JmFcDe51S_nsQ5SvPCbij9xz5M5s9Q";
	//will use Split method to split the URL
	//String partialCode=	url.split("code=")[1]; //to return first index from this array
	//String code=partialCode.split("&Scope=")[0]; //again split the URL as we are interested only in Code part
	//System.out.println(code);

	//Step 2 using the code get the access Token
		String accessTokenResponse=given().queryParams("code", url)
		.urlEncodingEnabled(false)//we dont want that special character in the code should get encoded as Rest assured encode any special characters
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js= new JsonPath(accessTokenResponse);
	String accessToken=	js.getString("access_token");
		
	//Step 3 Using access token get the actual Request
		GetCourse getcourseobject=given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)//REST assured will treat response as default json parser
				.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);//directly converting into json respone
		//System.out.println(getcourseobject);
				//.expect().defaultParser(Parser.JSON
		//as(GetCourse.class)
	System.out.println(getcourseobject.getLinkedIn());
	System.out.println(getcourseobject.getInstructor());
	
	System.out.println(getcourseobject.getCourses().getApi().get(1).getCourseTitle());//get SOAP UI Course title
	List<api> apicourse= getcourseobject.getCourses().getApi();
	for(int i=0;i<apicourse.size();i++)
	{
		
		if(apicourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
		{
			System.out.println(apicourse.get(i).getPrice());
		}
	}
	//get the course name of webautomation
	ArrayList<String> a= new ArrayList<String>();
	List<WebAutomation> webautomation = getcourseobject.getCourses().getWebAutomation();
	for(int i=0;i<webautomation.size();i++)
	{
		a.add(webautomation.get(i).getCourseTitle());//to get the course title and we used Arraylist as we are not aware of the size
		
	}
	
	//As we cannot compare array and Arraylist so we need to convert Array into ArrayList
	
	List<String> expectedList=Arrays.asList(courseTitles);
	
Assert.assertTrue(a.equals(expectedList));
	}

}
