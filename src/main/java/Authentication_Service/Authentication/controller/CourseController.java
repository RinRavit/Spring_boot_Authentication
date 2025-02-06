// package Authentication_Service.Authentication.controller;

// import Authentication_Service.Authentication.entity.Course;
// import Authentication_Service.Authentication.service.CourseService;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;
// import Authentication_Service.Authentication.utils.JwtUtil;

// import java.util.Map;
// import java.util.Set;

// @RestController
// @RequestMapping("/courses")
// public class CourseController {

//     private final CourseService courseService;
//     private final JwtUtil jwtUtil;

//     public CourseController(CourseService courseService) {
//         this.courseService = courseService;
//         this.jwtUtil = jwtUtil;
//     }

//     @PreAuthorize("hasRole('ADMIN')")
//     @PostMapping("/create")
//     public ResponseEntity<Course> createCourse(@RequestBody Course course) {
//         return new ResponseEntity<>(
//                 courseService.createCourse(course.getName(), course.getDescription()),
//                 HttpStatus.CREATED
//         );
//     }

//     // @PreAuthorize("hasRole('ADMIN')")
//     // @PostMapping("/assign")
//     // public ResponseEntity<String> assignCourseToUser(
//     //         @RequestParam String courseId,
//     //         @RequestParam String userId
//     // ) {
//     //     courseService.assignCourseToUser(courseId, userId);
//     //     return ResponseEntity.ok("Course assigned to user successfully.");
//     // }
//     @PreAuthorize("hasRole('ADMIN')")
// @PostMapping("/assign")
// public ResponseEntity<String> assignCourseToUser(@RequestBody Map<String, String> requestBody) {
//     String courseId = requestBody.get("courseId");
//     String userId = requestBody.get("userId");
    
//     courseService.assignCourseToUser(courseId, userId);
//     return ResponseEntity.ok("Course assigned to user successfully.");
// }

//     // @PreAuthorize("hasRole('USER')")
//     // @GetMapping("/my-courses")
//     // public ResponseEntity<Set<Course>> getCoursesForUser(@RequestParam String userId) {
//     //     return ResponseEntity.ok(courseService.getCoursesForUser(userId));
//     // }
//     @PreAuthorize("hasRole('USER')")
//     @GetMapping("/my-courses")
//     public ResponseEntity<?> getCoursesForUser() {
//         try {
//             // Extract username from the token
//             String username = jwtUtil.extractUsernameFromContext();

//             // Fetch courses for the user
//             Set<Course> courses = courseService.getCoursesForUserByUsername(username);

//             return ResponseEntity.ok(courses);
//         } catch (Exception e) {
//             return ResponseEntity.status(500).body("Error: " + e.getMessage());
//         }
//     }
//     // @PreAuthorize("hasRole('USER')")
//     // @GetMapping("/my-courses")
//     // public ResponseEntity<Set<Course>> getAllCoursesForUser(@RequestParam String userId) {
//     //     return ResponseEntity.ok(courseService.getCoursesForUser(userId));
//     // }
// }


// package Authentication_Service.Authentication.controller;

// import Authentication_Service.Authentication.entity.Course;
// import Authentication_Service.Authentication.service.CourseService;
// import Authentication_Service.Authentication.utils.JwtUtil;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;
// import java.util.Set;

// @RestController
// @RequestMapping("/courses")
// public class CourseController {

//     private final CourseService courseService;
//     private final JwtUtil jwtUtil;

//     public CourseController(CourseService courseService, JwtUtil jwtUtil) {
//         this.courseService = courseService;
//         this.jwtUtil = jwtUtil; // Inject JwtUtil via constructor
//     }

//     @PreAuthorize("hasRole('ADMIN')")
//     @PostMapping("/create")
//     public ResponseEntity<Course> createCourse(@RequestBody Course course) {
//         return new ResponseEntity<>(
//                 courseService.createCourse(course.getName(), course.getDescription()),
//                 HttpStatus.CREATED
//         );
//     }

//     @PreAuthorize("hasRole('ADMIN')")
//     @PostMapping("/assign")
//     public ResponseEntity<String> assignCourseToUser(@RequestBody Map<String, String> requestBody) {
//         String courseId = requestBody.get("courseId");
//         String userId = requestBody.get("userId");

//         courseService.assignCourseToUser(courseId, userId);
//         return ResponseEntity.ok("Course assigned to user successfully.");
//     }

//     @PreAuthorize("hasRole('USER')")
//     @GetMapping("/my-courses")
//     public ResponseEntity<?> getCoursesForUser() {
//         try {
//             // Extract username from the token
//             String username = jwtUtil.extractUsernameFromContext();

//             // Fetch courses for the user
//             Set<Course> courses = courseService.getCoursesForUserByUsername(username);

//             return ResponseEntity.ok(courses);
//         } catch (Exception e) {
//             return ResponseEntity.status(500).body("Error: " + e.getMessage());
//         }
//     }
// }
package Authentication_Service.Authentication.controller;

import Authentication_Service.Authentication.entity.Course;
import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.service.CourseService;
import Authentication_Service.Authentication.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final JwtUtil jwtUtil;

    public CourseController(CourseService courseService, JwtUtil jwtUtil) {
        this.courseService = courseService;
        this.jwtUtil = jwtUtil; // Inject JwtUtil via constructor
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        try {
            // Extract the username from the token
            String creatorUsername = jwtUtil.extractUsernameFromContext();

            // Create the course and associate it with the creator
            Course createdCourse = courseService.createCourse(course.getName(), course.getDescription(), creatorUsername);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
@GetMapping("/all-courses")
public ResponseEntity<?> getAllCourses() {
    try {
        Set<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}

    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin-courses")
public ResponseEntity<?> getCoursesForAdmin() {
    try {
        String adminUsername = jwtUtil.extractUsernameFromContext();

        // Fetch courses created by the admin
        Set<Course> courses = courseService.getCoursesCreatedByAdmin(adminUsername);

        return ResponseEntity.ok(courses);
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error: " + e.getMessage());
    }
}

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping("/assign")
    public ResponseEntity<String> assignCourseToUser(@RequestBody Map<String, String> requestBody) {
        String courseId = requestBody.get("courseId");
        String userId = requestBody.get("userId");

        courseService.assignCourseToUser(courseId, userId);
        return ResponseEntity.ok("Course assigned to user successfully.");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my-courses")
    public ResponseEntity<?> getCoursesForUser() {
        try {
            // Extract username from the token
            String username = jwtUtil.extractUsernameFromContext();

            // Fetch courses assigned to the user
            Set<Course> courses = courseService.getCoursesForUserByUsername(username);

            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // @PreAuthorize("hasRole('ADMIN')")
    // @GetMapping("/my-created-courses")
    // public ResponseEntity<?> getCoursesForAdmin() {
    //     try {
    //         // Extract admin username from the token
    //         String username = jwtUtil.extractUsernameFromContext();

    //         // Fetch courses created by the admin
    //         Set<Course> courses = courseService.getCoursesCreatedByAdmin(username);

    //         return ResponseEntity.ok(courses);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("Error: " + e.getMessage());
    //     }
    // }
}
