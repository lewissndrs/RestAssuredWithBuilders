package reqres.directors;

import io.restassured.specification.RequestSpecification;
import reqres.models.Register;

import java.util.HashMap;

public class UserDirector extends Director{

    public static Register buildRegisterUser(){
        return Register.builder().email("eve.holt@reqres.in").password("Password01").build(); // default values
    }

    public static RequestSpecification buildRegisterRequestSpec() {
        Register user = buildRegisterUser();
        return buildJsonRequest(user);
    }

    public static RequestSpecification buildRegisterRequestSpec(HashMap<String,String> overwriteValues) {
        Register register = buildRegisterUser();
        if(overwriteValues != null){ // overwrite only what is necessary
            if(overwriteValues.containsKey("email")) {register.setEmail(overwriteValues.get("email"));}
            if(overwriteValues.containsKey("password")) {register.setPassword(overwriteValues.get("password"));}
            if(overwriteValues.containsKey("username")) {register.setUsername(overwriteValues.get("username"));}
        }
        return buildJsonRequest(register);
    }

}