package Authentication_Service.Authentication.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "technicians")
public class Technician {

    @Id
    private String id;

    @Field("nameEnglish")
    @JsonProperty("nameEnglish")
    private String nameEnglish;

    @Field("nameKhmer")
    @JsonProperty("nameKhmer")
    private String nameKhmer;

    @Field("gender")
    @JsonProperty("gender")
    private String gender;

    @Field("typeTechnical")
    @JsonProperty("typeTechnical")
    private String typeTechnical;

    @Field("phone")
    @JsonProperty("phone")
    private String phone;

    @Field("image")
    @JsonProperty("image")
    private String image;


    // ID
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    // NameEnglish
    public String getNameEnglish() {
        return nameEnglish;
    }
    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    // NameKhmer
    public String getNameKhmer() {
        return nameKhmer;
    }
    public void setNameKhmer(String nameKhmer) {
        this.nameKhmer = nameKhmer;
    }

     // Gender
     public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

     // TypeTechnical
     public String getTypeTechnical() {
        return typeTechnical;
    }
    public void setTypeTechnical(String typeTechnical) {
        this.typeTechnical = typeTechnical;
    }

     // Phone
     public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

     // Image
     public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    
}
