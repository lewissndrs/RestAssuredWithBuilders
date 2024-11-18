package reqres.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@Jacksonized
public class RegisterResponse {
    String id;
    String token;
}
