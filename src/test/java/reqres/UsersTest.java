package reqres;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reqres.builders.UserDirector;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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

//    TODO Create a clean test for register user
    @Test
    public void testRegisterUserClean(){
        RequestSpecification requestBody = UserDirector.specWithUserPayload();
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
