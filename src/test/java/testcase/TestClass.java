package testcase;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import files.PayLoad;
import files.Resources;
import files.ResponseCheck;
import files.ReusableMethods;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.jayway.restassured.RestAssured.given;


/**
 * Created by rijwan on 5/1/18.
 */
public class TestClass {

    Properties prop=new Properties();

    @BeforeTest
    public void getEnv()throws IOException{
        FileInputStream fis=new FileInputStream("./src/test/java/files/env.properties");
        prop.load(fis);
    }
    @Test
    public void callWithApiKey(){

        RestAssured.baseURI=prop.getProperty("HOST");
        Response res= given().
             //   spec(ResponseCheck.requestQueryParam("key",prop.getProperty("KEY"),PayLoad.getPostData())).
            queryParam("key",prop.getProperty("KEY")).body(PayLoad.getPostData()).
            when()
                .post(Resources.mapsApi()).
                        then().
                        spec(ResponseCheck.postResponse()).extract().response();

        JsonPath js= ReusableMethods.rawToJSON(res);
        String place=js.get("place_id");
        System.out.print("ID: " + place);
    }
    @Test
    public void callWithSessionKey(){

        //System.out.print("ID: " + sessionId);
    }

}
