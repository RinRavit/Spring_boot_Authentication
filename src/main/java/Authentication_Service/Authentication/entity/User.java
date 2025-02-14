
// // package Authentication_Service.Authentication.entity;

// // import java.util.Set;

// // import org.springframework.data.annotation.Id;
// // import org.springframework.data.mongodb.core.mapping.Document;

// // @Document(collection = "users")
// // public class User {
// //     @Id
// //     private String id;
// //     private String username;
// //     private String password;
// //     private String email;
// //     private Set<Role> roles;
// //     private boolean oauthUser;
// //     // private Set<Course> courses; 

// //     public User() {}

// //     // Getters and Setters
// //     public String getId() {
// //         return id;
// //     }

// //     public void setId(String id) {
// //         this.id = id;
// //     }

// //     public String getUsername() {
// //         return username;
// //     }

// //     public void setUsername(String username) {
// //         this.username = username;
// //     }
// //     public void setEmail(String email) {
// //         this.email = email;
// //     }

// //     public String getPassword() {
// //         return password;
// //     }

// //     public void setPassword(String password) {
// //         this.password = password;
// //     }

// //     public Set<Role> getRoles() {
// //         return roles;
// //     }

// //     public void setRoles(Set<Role> roles) {
// //         this.roles = roles;
// //     }
// //     // public Set<Course> getCourses() {
// //     //     return courses;
// //     // }

// //     // public void setCourses(Set<Course> courses) {
// //     //     this.courses = courses;
// //     // }
// // }

// package Authentication_Service.Authentication.entity;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;
// import org.springframework.data.mongodb.core.mapping.DBRef;

// import java.util.Set;

// @Document(collection = "users")
// public class User {
//     @Id
//     private String id;
//     private String username;
//     private String password;
//     private String email;
//     private Set<Role> roles;

//     @DBRef // Use DBRef to reference related courses
//     private Set<Course> courses;

//     public String getId() {
//         return id;
//     }

//     public void setId(String id) {
//         this.id = id;
//     }

//     public String getUsername() {
//         return username;
//     }

//     public void setUsername(String username) {
//         this.username = username;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public Set<Role> getRoles() {
//         return roles;
//     }

//     public void setRoles(Set<Role> roles) {
//         this.roles = roles;
//     }

//     public Set<Course> getCourses() {
//         return courses;
//     }

//     public void setCourses(Set<Course> courses) {
//         this.courses = courses;
//     }
// }

package Authentication_Service.Authentication.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
    private Set<Course> courses = new HashSet<>(); // Initialize the courses field

    public User() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
