
package Authentication_Service.Authentication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "contracts")
@Data
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no_contract")
    private String contractNo;

    private String techincalAssistant;

    private String contractorList;

    private String description;

    private String competitivebidding;

    private String numberofbidder;

    @Column(name = "type_contract")
    private String typeprocurment;

    @Column(name = "type_project")
    private String natureOfProject;

    @Column(name = "dated_contract")
    private String Contractsigndate;

    private String Guaranteeperiod;

    private String startDate;

    private String completionDate;

    // @Column(name = "name_project")
    // private String nameProject;

    // @Column(name = "amount_budget")
    // private Double amountBudget;

    // @Column(name = "status_contract")
    // private String statusContract;

    // @Column(name = "create_by_contractor")
    // private String createBycontractor; // This could be a @ManyToOne if needed

    // private String date;

    // @Column(name = "created_at")
    // private Instant createdAt = Instant.now();

        // Id
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getcontractNo() {
            return contractNo;
        }
    
        public void setcontractNo(String contractNo) {
            this.contractNo = contractNo;
        }
    
        public String gettechincalAssistant() {
            return techincalAssistant;
        }
    
        public void settechincalAssistant(String techincalAssistant) {
            this.techincalAssistant = techincalAssistant;
        }
    
        public String getcontractorList() {
            return contractorList;
        }
    
        public void setcontractorList(String contractorList) {
            this.contractorList = contractorList;
        }
    
        public String getDescription() {
            return description;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        //Compeitivebid
        public String getcompetitivebidding() {
            return competitivebidding;
        }
        public void setcompetitivebidding(String competitivebidding) {
            this.competitivebidding = competitivebidding;
        }
    
        //TotalBider
        public String getnumberofbidder() {
            return numberofbidder;
        }
        public void setnumberofbidder(String numberofbidder) {
            this.numberofbidder = numberofbidder;
        }
    
        //TypeContact
        public String gettypeprocurment() {
            return typeprocurment;
        }
        public void settypeprocurment(String typeprocurment) {
            this.typeprocurment = typeprocurment;
        }
    
        // TypeProject
        public String getnatureOfProject() {
            return natureOfProject;
        }
        public void setnatureOfProject(String natureOfProject) {
            this.natureOfProject = natureOfProject;
        }
    
        // DatedContract
        public String getContractsigndate() {
            return Contractsigndate;
        }
        public void setContractsigndate(String Contractsigndate) {
            this.Contractsigndate = Contractsigndate;
        }
    
        // Duration 
        public String getGuaranteeperiod() {
            return Guaranteeperiod;
        }
        public void setGuaranteeperiod(String Guaranteeperiod) {
            this.Guaranteeperiod = Guaranteeperiod;
        }
    
        // Started
        public String getstartDate () {
            return startDate;
        }
        public void setstartDate (String startDate) {
            this.startDate = startDate;
        }
    
        // Ended
        public String getcompletionDate() {
            return completionDate;
        }
        public void setcompletionDate(String completionDate) {
            this.completionDate = completionDate;
        }
    
        // //NameContract
        // public String getNameProject() {
        //     return nameProject;
        // }
        // public void setNameProject(String nameProject) {
        //     this.nameProject = nameProject;
        // }
    
        // //AmountBugdet
        // public Double getAmountBudget() {
        //     return amountBudget;
        // }
        // public void setAmountBudget(Double amountBudget) {
        //     this.amountBudget = amountBudget;
        // }
    
        // // StatusContract
        // public String getStatusContract() {
        //     return statusContract;
        // }
    
        // public void setStatusContract(String statusContract) {
        //     this.statusContract = statusContract;
        // }
    
        // public String getCreateBycontractor() {
        //     return createBycontractor;
        // }
    
        // public void setCreateBycontractor(String createBycontractor) {
        //     this.createBycontractor = createBycontractor;
        // }
    
        // public String getDate() {
        //     return date;
        // }
    
        // public void setDate(String date) {
        //     this.date = date;
        // }
}

