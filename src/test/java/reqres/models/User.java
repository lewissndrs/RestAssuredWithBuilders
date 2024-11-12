package reqres.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

//Data autogenerates getters, setters etc. for the POJO. Look how tiny this is now!
@Data
//Builder makes this conform to the builder pattern
@Builder
//Below is used to strip any null fields
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User {
    String username;
    String email;
    String password;
}
