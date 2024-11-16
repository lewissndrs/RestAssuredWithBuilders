package reqres;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reqres.directors.UserDirector;
import reqres.models.UsersResponse;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

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
                .assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("data.size()",equalTo(6))
                .and().body("per_page",equalTo(6))
                .and().body("page",equalTo(1));
    }

    @Test
    public void testGetUsersWithOverwriteDataAndDeserialisation(){
//        we can overwrite the default values by specifying only the data necessary for the test.
//        Things like content type and response code don't need to be specced for every test
        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();
        resSpecBuilder.expectBody("data.size()",equalTo(5));
        resSpecBuilder.expectBody("per_page",equalTo(5));
        resSpecBuilder.expectBody("page",equalTo(1));
        ResponseSpecification resSpec = UserDirector.buildGetUserResponseSpec(resSpecBuilder.build());

        given()
                .param("per_page",5)
                .expect()
                .spec(resSpec)
                .when()
                .get("/users")
                .as(UsersResponse.class);
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
                .post("/register")
                .then()
                .statusCode(200);
    }

    @Test
    public void testRegisterUserClean(){
//        set up only the data necessary, if none is needed the overwrite values arg can be skipped.
        HashMap<String,String> overwriteValues = new HashMap<>();
        overwriteValues.put("email","eve.holt@reqres.in");

//        boilerplate like setting contentType is abstracted to the Director class
        RequestSpecification requestBody = UserDirector.buildRegisterRequestSpec(overwriteValues);
        given()
                .when()
                .spec(requestBody)
                .post("/register")
                .then()
                .statusCode(200);
    }

}
