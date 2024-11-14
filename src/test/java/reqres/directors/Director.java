package reqres.directors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Director {

    public static String serialiseBuilderObject(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String payload = null;
        try{
            payload = mapper.writeValueAsString(object);
        }
        catch (JsonProcessingException e) {
            System.out.println(e);
        }
        return payload;
    }

    public static RequestSpecification buildJsonRequest(Object object) {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setAccept(ContentType.JSON);
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.setBody(serialiseBuilderObject(object));
        return requestBuilder.build();
    }
}
