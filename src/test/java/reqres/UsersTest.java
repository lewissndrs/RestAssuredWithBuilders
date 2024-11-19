package reqres;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reqres.directors.UserDirector;
import reqres.models.RegisterErrorResponse;
import reqres.models.RegisterResponse;
import reqres.models.UsersResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class UsersTest {

    @BeforeClass
    public void setURI() {
        baseURI = "https://reqres.in/api";
    }


    @Test
    public void testGetUsers(){
//        Things like content type and response code can be assumed as default for most tests. We can use buildResponseSpec() to add the defaults for us.
        ResponseSpecification resSpec = UserDirector.buildResponseSpec(new ResponseSpecBuilder()
                .expectBody("data.size()",equalTo(5))
                .expectBody("per_page",equalTo(5))
                .expectBody("page",equalTo(2))
                .build()
        );

        given()
                .param("per_page",5)
                .param("page",2)
                .when()
                .get("/users")
                .then()
                .spec(resSpec)
                .extract()
                .as(UsersResponse.class); //ensuring that the response serialises to the expected object.
    }



    @Test
    public void testPostRegisterUser(){
//        set up only the data necessary, if none is needed the overwrite values arg can be skipped.
        RequestSpecification requestBody = UserDirector.buildRegisterRequestSpec();
//        same for expected response data. A standard 200 response and headers are expected so no overwrite data needed.
        ResponseSpecification defaultResSpec = UserDirector.buildResponseSpec();

        given()
                .when()
                .spec(requestBody)
                .post("/register")
                .then()
                .spec(defaultResSpec)
                .extract()
                .as(RegisterResponse.class);
    }

    @Test
    public void testPostRegisterUserNegative(){

        RequestSpecification reqSpec = UserDirector.buildRegisterRequestSpec(new HashMap<>(Map.of(
                "email","lewis@email.com"
        )));

        ResponseSpecification resSpec = UserDirector.buildResponseSpec(new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build());

        given()
                .when()
                .spec(reqSpec)
                .post("/register")
                .then()
                .spec(resSpec)
                .extract()
                .as(RegisterErrorResponse.class);
    }


}
