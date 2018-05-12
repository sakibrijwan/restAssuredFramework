package files;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by rijwan on 5/3/18.
 */
public class ReusableMethods {

    public static XmlPath rawToXML(Response res){
        String responseString=res.asString();
        XmlPath xml=new XmlPath(responseString);
        return xml;
    }

    public static JsonPath rawToJSON(Response res){

        String responseString=res.asString();
        JsonPath js=new JsonPath(responseString);
        return js;
    }

    public static String getSessionKey(){
        Properties prop=new Properties();
        FileInputStream fis= null;
        try {
            fis = new FileInputStream("./src/test/java/files/env.properties");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAssured.baseURI=prop.getProperty("HOST2");
        Response res=given().
                header("Content-type", "application/json").body("{\"username\":\"\",\"password\":\"\"}").
                when()
                .post().
                        then().statusCode(200).
                        extract().response();
        JsonPath js= ReusableMethods.rawToJSON(res);
        String sessionId=js.get("session.value");
        return sessionId;
    }
}
