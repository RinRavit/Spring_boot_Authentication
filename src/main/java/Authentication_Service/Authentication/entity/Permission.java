// package Authentication_Service.Authentication.entity;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;

// @Document(collection = "permissions")
// public class Permission {
//     @Id
//     private String id;
//     private String name;

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
// }


package Authentication_Service.Authentication.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "permissions")
public class Permission {
    @Id
    private String id;
    private String name;

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
