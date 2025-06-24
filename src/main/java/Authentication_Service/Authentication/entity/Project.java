package Authentication_Service.Authentication.entity;

import lombok.Data;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "projects")
public class Project {

    @Id
    private String id;

    private String noProject;
    private String mecanicProject;
    private String description;
    private String competitivebid;
    private String totalbider;
    private String typeProject;
    private String duration;
    private String started;
    private String ended;
    private String nameProject;
    private Double amountBudget;
    private String statusProject;
    private Instant createdAt = Instant.now();


    // Id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNoProject() {
        return noProject;
    }

    public void setNoProject(String noProject) {
        this.noProject = noProject;
    }

    // mecanicProject
    public String getMecanicProject() {
        return mecanicProject;
    }

    public void setMecanicProject(String mecanicProject) {
        this.mecanicProject = mecanicProject;
    }

    // description
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

    // TypeProject
    public String getTypeProject() {
        return typeProject;
    }
    public void setTypeProject(String typeProject) {
        this.typeProject = typeProject;
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
    public String getStatusProject() {
        return statusProject;
    }

    public void setStatusProject(String statusProject) {
        this.statusProject = statusProject;
    }



}
