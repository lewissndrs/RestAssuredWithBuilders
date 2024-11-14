package reqres.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

//Data autogenerates getters, setters etc. for the POJO. Look how tiny this is now!
@Data
//Builder makes this conform to the builder pattern
@Builder
//Below is used to strip any null fields from the serialisation as we don't always want them
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Register {
    String username;
    @NonNull
    String email;
    String password;
}
