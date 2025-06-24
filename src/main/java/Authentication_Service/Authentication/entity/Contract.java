package Authentication_Service.Authentication.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Data
@Document(collection = "contracts")
public class Contract {
    @Id
    private String id;

    private String noContract;
    private String mecanic;
    private String contractor;
    private String description;
    private String competitivebid;
    private String totalbider;
    private String typeContract;
    private String typeProject;
    private String datedContract;
    private String duration;
    private String started;
    private String ended;
    private String nameProject;
    private Double amountBudget;
    private String statusContract;
    private String createBycontractor; // Just store ID
    private String date;

    private Instant createdAt = Instant.now();

    public String getId() {
        return id;
    }
    public void setId(String id) {
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
