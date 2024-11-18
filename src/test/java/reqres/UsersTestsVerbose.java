package reqres;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UsersTestsVerbose {

    @BeforeClass
    public void setURI() {
        baseURI = "https://reqres.in/api";
    }

    //    example with all data stated in the test. This works fine for a simple test.
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

    //    An example of a "fixture", where the json is explicitly written out. This is fragile and not easy to manage long-term
    @Test
    public void testPostRegisterUserVerbose(){
        RequestSpecification requestBody =  new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setBody(
                        "{" +
                                "\"email\": \"lewis@email.com\", " +
                                "\"password\": \"password01\""+
                                "}"
                )
                .build();

        given()
                .when()
                .spec(requestBody)
                .post("/register")
                .then()
                .statusCode(400)
                .body("error",equalTo("Note: Only defined users succeed registration"));
    }
}
