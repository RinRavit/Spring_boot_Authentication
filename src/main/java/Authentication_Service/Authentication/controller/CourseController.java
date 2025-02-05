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


package Authentication_Service.Authentication.controller;

import Authentication_Service.Authentication.entity.Course;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return new ResponseEntity<>(
                courseService.createCourse(course.getName(), course.getDescription()),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
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

            // Fetch courses for the user
            Set<Course> courses = courseService.getCoursesForUserByUsername(username);

            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
