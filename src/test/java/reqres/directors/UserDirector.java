package reqres.directors;

import io.restassured.specification.RequestSpecification;
import reqres.models.Register;

public class UserDirector extends Director{

    public static Register buildRegisterUser(){
        return Register.builder().email("lewis@email.com").password("Password01").build();
    }

    public static RequestSpecification buildRegisterRequestSpec(){
        Register user = buildRegisterUser();
        return buildJsonRequest(user);
    }

}

//TODO: set up a way to pass parameters through from test code
//TODO: Make specWithUserPayLoad have a better name so it's more clear and also take parameters.
