package reqres;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reqres.directors.UserDirector;
import reqres.models.UsersResponse;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class UsersTest {

    @BeforeClass
    public void setURI() {
        baseURI = "https://reqres.in/api";
    }

    @Test
    public void testGetUsers(){
        given()
                .when()
                .get("/users")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void testGetUsersDeserialised() {
        UsersResponse response =
        given()
                .when()
                .get("/users")
                .as(UsersResponse.class);
        Assert.assertEquals(6, response.getData().size());
    }

    @Test
    public void testRegisterUserVerbose(){
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setAccept(ContentType.JSON);
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.setBody(
                "{" +
                        "\"email\": \"eve.holt@reqres.in\", " +
                        "\"password\": \"password01\""+
                        "}"
        );
        RequestSpecification requestBody = requestBuilder.build();

        given()
                .when()
                .spec(requestBody)
                .log()
                .body()
                .post("/register")
                .then()
                .log()
                .body()
                .statusCode(200);
    }

    @Test
    public void testRegisterUserClean(){
//        set up only the data necessary, if none is needed the overwritevalues arg can be skipped.
        HashMap<String,String> overwriteValues = new HashMap<String,String>();
        overwriteValues.put("email","eve.holt@reqres.in");

//        boilerplate like setting contentType is abstracted to the Director class
        RequestSpecification requestBody = UserDirector.buildRegisterRequestSpec(overwriteValues);
        given()
                .when()
                .spec(requestBody)
                .log()
                .body()
                .post("/register")
                .then()
                .log()
                .body()
                .statusCode(200);
    }


}
