package reqres.directors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

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
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setBody(serialiseBuilderObject(object))
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
    public static ResponseSpecification buildResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification buildResponseSpec(ResponseSpecification overwrites) {
        return new ResponseSpecBuilder()
                .addResponseSpecification(buildResponseSpec())
                .addResponseSpecification(overwrites)
                .build();
    }

}
