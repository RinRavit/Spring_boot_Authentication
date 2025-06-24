package Authentication_Service.Authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "contractors")
public class Contractor {

    @Id
    private String id;
    private String image;
    private String directorName;
    private String contractor;
    private String companyName;
    private String location;
    private String phone;
    private String typeService;
    private String typeContract;
    private String typeContractor;

    @CreatedDate
    private Instant createdAt;


    // ID
    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
    }
    // Image
    public String getimage() {
        return image;
    }
    public void setimage(String image) {
        this.image = image;
    }

    // DirrectorName
    public String getdirectorName() {
        return directorName;
    }
    public void setdirectorName(String directorName) {
        this.directorName = directorName;
    }
    
    // Contractor
    public String getcontractor() {
        return contractor;
    }
    public void setcontractor(String contractor) {
        this.contractor = contractor;
    }

    // Company Name
    public String getcompanyName() {
        return companyName;
    }
    public void setcompanyName(String companyName) {
        this.companyName = companyName;
    }


    // Phone
    public String getphone() {
        return phone;
    }
    public void setphone(String phone) {
        this.phone = phone;
    }

    // Location
    public String getlocation() {
        return location;
    }
    public void setlocation(String location) {
        this.location = location;
    }

    // TypeService
    public String gettypeService() {
        return typeService;
    }
    public void settypeService(String typeService) {
        this.typeService = typeService;
    }

    // TypeContract
    public String gettypeContract() {
        return typeContract;
    }
    public void settypeContract(String typeContract) {
        this.typeContract = typeContract;
    }

    // TypeContractor
    public String gettypeContractor() {
        return typeContractor;
    }
    public void settypeContractor(String typeContractor) {
        this.typeContractor = typeContractor;
    }
}
