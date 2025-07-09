

package Authentication_Service.Authentication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no_project")
    private String noProject;

    @Column(name = "mecanic_project")
    private String mecanicProject;

    private String description;

    private String competitivebid;

    private String totalbider;

    @Column(name = "type_project")
    private String typeProject;

    private String duration;

    private String started;

    private String ended;

    @Column(name = "name_project")
    private String nameProject;

    @Column(name = "amount_budget")
    private Double amountBudget;

    @Column(name = "status_project")
    private String statusProject;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();
    
    @ManyToOne
@JoinColumn(name = "project_id")
private Project project;

public Project getProject() {
    return project;
}

public void setProject(Project project) {
    this.project = project;
}

        // Id
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
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
