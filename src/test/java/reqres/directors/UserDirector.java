package reqres.directors;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import reqres.models.Register;

import java.util.HashMap;

public class UserDirector extends Director{

    public static Register buildRegisterUser(HashMap<String,String> overwriteValues){
        Register.RegisterBuilder register = Register.builder().email("lewis@email.com").password("Password01"); // default values
        if(overwriteValues != null){ // overwrite only what is necessary
            if(overwriteValues.containsKey("email")) {register.email(overwriteValues.get("email"));}
            if(overwriteValues.containsKey("password")) {register.password(overwriteValues.get("password"));}
            if(overwriteValues.containsKey("username")) {register.username(overwriteValues.get("username"));}
        }
        return register.build();
    }

    public static RequestSpecification buildRegisterRequestSpec() {
        Register user = buildRegisterUser(null);
        return buildJsonRequest(user);
    }

    public static RequestSpecification buildRegisterRequestSpec(HashMap<String,String> overwriteValues) {
        Register user = buildRegisterUser(overwriteValues);
        return buildJsonRequest(user);
    }

    public static ResponseSpecification buildGetUserResponseSpec() {
        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();
        resSpecBuilder.expectStatusCode(200);
        resSpecBuilder.expectContentType(ContentType.JSON);
        return resSpecBuilder.build();
    }

    public static ResponseSpecification buildGetUserResponseSpec(ResponseSpecification overwrites) {
        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();
        resSpecBuilder.addResponseSpecification(overwrites);
        resSpecBuilder.addResponseSpecification(buildGetUserResponseSpec());
        return resSpecBuilder.build();
    }
}