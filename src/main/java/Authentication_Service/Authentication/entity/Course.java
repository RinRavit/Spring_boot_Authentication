// // package Authentication_Service.Authentication.entity;

// // import org.springframework.data.annotation.Id;
// // import org.springframework.data.mongodb.core.mapping.Document;

// // import java.util.HashSet;
// // import java.util.Set;

// // @Document(collection = "courses")
// // public class Course {
// //     @Id
// //     private String id;
// //     private String name;
// //     private String description;
// //     private Set<String> assignedUsers = new HashSet<>();

// //     public Course() {}

// //     public Course(String name, String description) {
// //         this.name = name;
// //         this.description = description;
// //     }

// //     // Getters and Setters
// //     public String getId() {
// //         return id;
// //     }

// //     public void setId(String id) {
// //         this.id = id;
// //     }

// //     public String getName() {
// //         return name;
// //     }

// //     public void setName(String name) {
// //         this.name = name;
// //     }

// //     public String getDescription() {
// //         return description;
// //     }

// //     public void setDescription(String description) {
// //         this.description = description;
// //     }

// //     public Set<String> getAssignedUsers() {
// //         return assignedUsers;
// //     }

// //     public void setAssignedUsers(Set<String> assignedUsers) {
// //         this.assignedUsers = assignedUsers;
// //     }
// // }

// package Authentication_Service.Authentication.entity;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;

// import com.fasterxml.jackson.annotation.JsonProperty;

// @Document(collection = "courses")
// public class Course {
//     @Id
//     private String id;
//     @JsonProperty("title")
//     private String name;
//     private String description;

//     // Default constructor
//     public Course() {}

//     // Constructor with name and description
//     public Course(String name, String description) {
//         this.name = name;
//         this.description = description;
//     }

//     // Getters and Setters
//     public String getId() {
//         return id;
//     }

//     public void setId(String id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }
// }

package Authentication_Service.Authentication.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "courses")
public class Course {
    @Id
    private String id;
    
    @JsonProperty("title")
    private String name;
    
    private String description;
    
    private String createdBy;  // Stores the username of the admin who created the course

    public Course() {}

    public Course(String name, String description, String createdBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
    }

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
