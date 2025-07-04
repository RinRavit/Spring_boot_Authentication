
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
    private String noContract;

    private String mecanic;

    private String contractor;

    private String description;

    private String competitivebid;

    private String totalbider;

    @Column(name = "type_contract")
    private String typeContract;

    @Column(name = "type_project")
    private String typeProject;

    @Column(name = "dated_contract")
    private String datedContract;

    private String duration;

    private String started;

    private String ended;

    @Column(name = "name_project")
    private String nameProject;

    @Column(name = "amount_budget")
    private Double amountBudget;

    @Column(name = "status_contract")
    private String statusContract;

    @Column(name = "create_by_contractor")
    private String createBycontractor; // This could be a @ManyToOne if needed

    private String date;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

        // Id
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getNoContract() {
            return noContract;
        }
    
        public void setNoContract(String noContract) {
            this.noContract = noContract;
        }
    
        public String getMecanic() {
            return mecanic;
        }
    
        public void setMecanic(String mecanic) {
            this.mecanic = mecanic;
        }
    
        public String getContractor() {
            return contractor;
        }
    
        public void setContractor(String contractor) {
            this.contractor = contractor;
        }
    
        public String getDescription() {
            return description;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        //Compeitivebid
        public String getCompetitivebid() {
            return competitivebid;
        }
        public void setCompetitivebid(String competitivebid) {
            this.competitivebid = competitivebid;
        }
    
        //TotalBider
        public String getTotalbider() {
            return totalbider;
        }
        public void setTotalbider(String totalbider) {
            this.totalbider = totalbider;
        }
    
        //TypeContact
        public String getTypeContract() {
            return typeContract;
        }
        public void setTypeContract(String typeContract) {
            this.typeContract = typeContract;
        }
    
        // TypeProject
        public String getTypeProject() {
            return typeProject;
        }
        public void setTypeProject(String typeProject) {
            this.typeProject = typeProject;
        }
    
        // DatedContract
        public String getDatedContract() {
            return datedContract;
        }
        public void setDatedContract(String datedContract) {
            this.datedContract = datedContract;
        }
    
        // Duration 
        public String getDuration() {
            return duration;
        }
        public void setDuration(String duration) {
            this.duration = duration;
        }
    
        // Started
        public String getStarted() {
            return started;
        }
        public void setStarted(String started) {
            this.started = started;
        }
    
        // Ended
        public String getEnded() {
            return ended;
        }
        public void setEnded(String ended) {
            this.ended = ended;
        }
    
        //NameContract
        public String getNameProject() {
            return nameProject;
        }
        public void setNameProject(String nameProject) {
            this.nameProject = nameProject;
        }
    
        //AmountBugdet
        public Double getAmountBudget() {
            return amountBudget;
        }
        public void setAmountBudget(Double amountBudget) {
            this.amountBudget = amountBudget;
        }
    
        // StatusContract
        public String getStatusContract() {
            return statusContract;
        }
    
        public void setStatusContract(String statusContract) {
            this.statusContract = statusContract;
        }
    
        public String getCreateBycontractor() {
            return createBycontractor;
        }
    
        public void setCreateBycontractor(String createBycontractor) {
            this.createBycontractor = createBycontractor;
        }
    
        public String getDate() {
            return date;
        }
    
        public void setDate(String date) {
            this.date = date;
        }
}

