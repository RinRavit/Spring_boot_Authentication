
package Authentication_Service.Authentication.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "contractors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @Column(name = "director_name")
    private String directorName;

    private String contractor;

    @Column(name = "company_name")
    private String companyName;

    private String location;

    private String phone;

    @Column(name = "type_service")
    private String typeService;

    @Column(name = "type_contract")
    private String typeContract;

    @Column(name = "type_contractor")
    private String typeContractor;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    // ID
    public Long getid() {
        return id;
    }
    public void setid(Long id) {
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
