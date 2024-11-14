package reqres.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

//Data autogenerates getters, setters etc. for the POJO. Look how tiny this is now!
@Data
//Builder makes this conform to the builder pattern
@Builder
@Jacksonized
public class User {
    Integer id;
    String first_name;
    String last_name;
    String email;
    String avatar;
}
