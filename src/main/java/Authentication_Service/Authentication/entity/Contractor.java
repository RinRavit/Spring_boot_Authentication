

package Authentication_Service.Authentication.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@Document(collection = "contractors")
public class Contractor {

    @Id
    private String id;

    @Field("image")
    @JsonProperty("image")
    private String image;

    @Field("directorName")
    @JsonProperty("directorName")
    private String directorName;

    @Field("contractor")
    @JsonProperty("contractor")
    private String contractor;

    @Field("companyName")
    @JsonProperty("companyName")
    private String companyName;

    @Field("phone")
    @JsonProperty("phone")
    private String phone;

    @CreatedDate
    private Instant createdAt;
}
