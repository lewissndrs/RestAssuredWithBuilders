package reqres.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Data
@Jacksonized
@JsonPropertyOrder({"page","per_page","total","total_pages","data"})
public class UsersResponse {
    Integer page;
    @Builder.Default
    Integer per_page = 6;
    Integer total;
    Integer total_pages;
    List<User> data;
    @JsonIgnore
    List<String> support;
}
