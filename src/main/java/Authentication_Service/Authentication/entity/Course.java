package Authentication_Service.Authentication.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "courses")
public class Course {
    @Id
    private String id;
    private String name;
    private String description;
    private Set<String> assignedUsers = new HashSet<>();

    public Course() {}

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(Set<String> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }
}
