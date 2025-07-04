

package Authentication_Service.Authentication.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "technicians")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_english")
    @JsonProperty("nameEnglish")
    private String nameEnglish;

    @Column(name = "name_khmer")
    @JsonProperty("nameKhmer")
    private String nameKhmer;

    @Column(name = "gender")
    @JsonProperty("gender")
    private String gender;

    @Column(name = "type_technical")
    @JsonProperty("typeTechnical")
    private String typeTechnical;

    @Column(name = "phone")
    @JsonProperty("phone")
    private String phone;

    @Column(name = "image")
    @JsonProperty("image")
    private String image;

        // ID
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
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
