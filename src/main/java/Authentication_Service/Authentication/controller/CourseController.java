package Authentication_Service.Authentication.controller;

import Authentication_Service.Authentication.entity.Course;
import Authentication_Service.Authentication.service.CourseService;
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

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return new ResponseEntity<>(
                courseService.createCourse(course.getName(), course.getDescription()),
                HttpStatus.CREATED
        );
    }

    // @PreAuthorize("hasRole('ADMIN')")
    // @PostMapping("/assign")
    // public ResponseEntity<String> assignCourseToUser(
    //         @RequestParam String courseId,
    //         @RequestParam String userId
    // ) {
    //     courseService.assignCourseToUser(courseId, userId);
    //     return ResponseEntity.ok("Course assigned to user successfully.");
    // }
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
    public ResponseEntity<Set<Course>> getCoursesForUser(@RequestParam String userId) {
        return ResponseEntity.ok(courseService.getCoursesForUser(userId));
    }
}
