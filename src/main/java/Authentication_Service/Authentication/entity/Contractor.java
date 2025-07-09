
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

    @Column(name = "contractor_name")
    private String contractorName;

    @Column(name = "company_name")
    private String companyName;

    private String address;

    private String phoneNumber;

    @Column(name = "business_type")
    private String businesstype;

    @Column(name = "nature_of_project")
    private String natureofproject;

    @Column(name = "classification")
    private String classification;

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
    public String getcontractorName() {
        return contractorName;
    }
    public void setcontractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    // Company Name
    public String getcompanyName() {
        return companyName;
    }
    public void setcompanyName(String companyName) {
        this.companyName = companyName;
    }


    // Phone
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Address
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    // Business type
    public String getBusinesstype() {
        return businesstype;
    }
    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }
    

    // Nature of Project
    public String getNatureofproject() {
        return natureofproject;
    }
    public void setNatureofproject(String natureofproject) {
        this.natureofproject = natureofproject;
    }

    // Classification
    public String getClassification() {
        return classification;
    }
    public void setClassification(String classification) {
        this.classification = classification;
    }
}
