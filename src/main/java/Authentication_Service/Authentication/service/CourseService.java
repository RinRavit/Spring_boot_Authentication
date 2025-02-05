// // package Authentication_Service.Authentication.service;

// // import Authentication_Service.Authentication.entity.Course;
// // import Authentication_Service.Authentication.entity.User;
// // import Authentication_Service.Authentication.repository.CourseRepository;
// // import Authentication_Service.Authentication.repository.UserRepository;
// // import org.springframework.stereotype.Service;

// // import java.util.HashSet;
// // import java.util.Optional;
// // import java.util.Set;
// // import java.util.stream.Collectors;

// // @Service
// // public class CourseService {
// //     private final CourseRepository courseRepository;
// //     private final UserRepository userRepository;

// //     public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
// //         this.courseRepository = courseRepository;
// //         this.userRepository = userRepository;
// //     }

// //     public Course createCourse(String name, String description) {
// //         Course course = new Course(name, description);
// //         return courseRepository.save(course);
// //     }

// //     // public void assignCourseToUser(String courseId, String userId) {
// //     //     Optional<Course> courseOptional = courseRepository.findById(courseId);
// //     //     Optional<User> userOptional = userRepository.findById(userId);

// //     //     if (courseOptional.isEmpty()) {
// //     //         throw new RuntimeException("Course not found");
// //     //     }

// //     //     if (userOptional.isEmpty()) {
// //     //         throw new RuntimeException("User not found");
// //     //     }

// //     //     Course course = courseOptional.get();
// //     //     User user = userOptional.get();

// //     //     course.getAssignedUsers().add(user.getId());
// //     //     courseRepository.save(course);
// //     // }

// //     public void assignCourseToUser(String courseId, String userId) {
// //     Course course = courseRepository.findById(courseId)
// //             .orElseThrow(() -> new RuntimeException("Course not found"));
// //     User user = userRepository.findById(userId)
// //             .orElseThrow(() -> new RuntimeException("User not found"));

// //     // Ensure the user's courses list is initialized
// //     if (user.getCourses() == null) {
// //         user.setCourses(new HashSet<>());
// //     }

// //     // Add the course to the user's list
// //     user.getCourses().add(course);

// //     // Save the user back to the database
// //     userRepository.save(user);
// // }

// //     // public Set<Course> getCoursesForUser(String userId) {
// //     //     return courseRepository.findAll().stream()
// //     //             .filter(course -> course.getAssignedUsers().contains(userId))
// //     //             .collect(Collectors.toSet());
// //     // }
// //     // public Set<Course> getCoursesForUser(String userId) {
// //     //     User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
// //     //     return user.getCourses();
// //     // }
// //     public Set<Course> getCoursesForUser(String userId) {
// //         User user = userRepository.findById(userId)
// //                 .orElseThrow(() -> new RuntimeException("User not found"));
    
// //         // Return the courses set
// //         return user.getCourses();
// //     }
// // }

// package Authentication_Service.Authentication.service;

// import Authentication_Service.Authentication.entity.Course;
// import Authentication_Service.Authentication.entity.User;
// import Authentication_Service.Authentication.repository.CourseRepository;
// import Authentication_Service.Authentication.repository.UserRepository;
// import org.springframework.stereotype.Service;

// import java.util.Optional;
// import java.util.Set;

// @Service
// public class CourseService {
//     private final CourseRepository courseRepository;
//     private final UserRepository userRepository;

//     public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
//         this.courseRepository = courseRepository;
//         this.userRepository = userRepository;
//     }

//     public Set<Course> getCoursesForUser(String userId) {
//         // Fetch user by ID
//         User user = userRepository.findById(userId)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         // Return the user's assigned courses
//         return user.getCourses();
//     }

//     public Course createCourse(String name, String description) {
//         Course course = new Course(name, description);
//         return courseRepository.save(course);
//     }

//     public void assignCourseToUser(String courseId, String userId) {
//         Optional<Course> courseOptional = courseRepository.findById(courseId);
//         if (courseOptional.isEmpty()) {
//             throw new RuntimeException("Course not found");
//         }

//         Course course = courseOptional.get();

//         User user = userRepository.findById(userId)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         user.getCourses().add(course);
//         userRepository.save(user);
//     }
// }

package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Course;
import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.repository.CourseRepository;
import Authentication_Service.Authentication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    // public Course createCourse(String name, String description) {
    //     Course course = new Course(name, description);
    //     return courseRepository.save(course);
    // }
    public Course createCourse(String name, String description) {
        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        return courseRepository.save(course);
    }

    public void assignCourseToUser(String courseId, String userId) {
        // Validate course existence
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Validate user existence
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Assign course to user
        user.getCourses().add(course);
        userRepository.save(user);
    }

    // public Set<Course> getCoursesForUser(String userId) {
    //     User user = userRepository.findById(userId)
    //             .orElseThrow(() -> new RuntimeException("User not found"));
    //     return user.getCourses();
    // }
     public Set<Course> getCoursesForUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return user.getCourses() != null ? user.getCourses() : new HashSet<>();
    }
}
