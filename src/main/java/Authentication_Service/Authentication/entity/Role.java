
// package Authentication_Service.Authentication.entity;

// import java.util.Set;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;

// @Document(collection = "roles")
// public class Role {
//     @Id
//     private String id;
//     private String name;
//     private Set<Permission> permissions;

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

//     public Set<Permission> getPermissions() {
//         return permissions;
//     }

//     public void setPermissions(Set<Permission> permissions) {
//         this.permissions = permissions;
//     }
// }


package Authentication_Service.Authentication.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {
    @Id
    private String id;
    private String name;

    // Default constructor
    public Role() {}

    // Constructor with name
    public Role(String name) {
        this.name = name;
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
}
