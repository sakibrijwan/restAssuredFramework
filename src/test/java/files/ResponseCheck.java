package files;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by rijwan on 5/2/18.
 */
public class ResponseCheck {
   public static ResponseSpecBuilder resBuilder;
   public static ResponseSpecification responseSpec;
   public static RequestSpecification requestSpec;
   public static RequestSpecBuilder requestbuilder;

    public static ResponseSpecification postResponse() {
        resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).expectBody("status",equalTo("OK"));

        responseSpec = resBuilder.build();
        return responseSpec;
    }

    public static RequestSpecification requestQueryParam(String parameter,String value,String body){

        System.out.print(parameter);
        System.out.print(value);
        System.out.print(body);
        requestbuilder = new RequestSpecBuilder();
        requestbuilder.addParameter(parameter, value);
        requestbuilder.setBody(body).setContentType(ContentType.JSON);
        //builder.setBody(body);
        //builder.addFormParam(body);
        requestSpec=requestbuilder.build();
        return requestSpec;
    }
}
