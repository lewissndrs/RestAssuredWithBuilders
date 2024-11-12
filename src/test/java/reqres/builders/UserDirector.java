package reqres.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import reqres.models.User;

public class UserDirector {
    public static User buildUser(){
        return User.builder().email("lewis@email.com").password("Password01").build();
    }

    public static String serialiseUser(User user) {
        ObjectMapper mapper = new ObjectMapper();
        String payload = null;
        try{
            payload = mapper.writeValueAsString(user);
        }
        catch (JsonProcessingException e) {
            System.out.println(e);
        }
        return payload;
    }

    public static RequestSpecification specWithUserPayload(){
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setAccept(ContentType.JSON);
        requestBuilder.setContentType(ContentType.JSON);
        User user = buildUser();
        requestBuilder.setBody(serialiseUser(user));
        return requestBuilder.build();
    }
}
