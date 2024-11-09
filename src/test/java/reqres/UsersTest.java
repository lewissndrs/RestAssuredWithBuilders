package reqres;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UsersTest {


    @Test
    public void testGetUsers(){
        given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .log().ifError()
                .assertThat().statusCode(200);
    }



}
