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

// package Authentication_Service.Authentication.service;

// import Authentication_Service.Authentication.entity.Course;
// import Authentication_Service.Authentication.entity.User;
// import Authentication_Service.Authentication.repository.CourseRepository;
// import Authentication_Service.Authentication.repository.UserRepository;
// import org.springframework.stereotype.Service;

// import java.util.HashSet;
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

//     // public Course createCourse(String name, String description) {
//     //     Course course = new Course(name, description);
//     //     return courseRepository.save(course);
//     // }
//     public Course createCourse(String name, String description) {
//         Course course = new Course();
//         course.setName(name);
//         course.setDescription(description);
//         return courseRepository.save(course);
//     }

//     public void assignCourseToUser(String courseId, String userId) {
//         // Validate course existence
//         Course course = courseRepository.findById(courseId)
//                 .orElseThrow(() -> new RuntimeException("Course not found"));

//         // Validate user existence
//         User user = userRepository.findById(userId)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         // Assign course to user
//         user.getCourses().add(course);
//         userRepository.save(user);
//     }

//     // public Set<Course> getCoursesForUser(String userId) {
//     //     User user = userRepository.findById(userId)
//     //             .orElseThrow(() -> new RuntimeException("User not found"));
//     //     return user.getCourses();
//     // }
//      public Set<Course> getCoursesForUserByUsername(String username) {
//         User user = userRepository.findByUsername(username)
//                 .orElseThrow(() -> new IllegalArgumentException("User not found"));

//         return user.getCourses() != null ? user.getCourses() : new HashSet<>();
//     }
// }


package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Course;
import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.repository.CourseRepository;
import Authentication_Service.Authentication.repository.UserRepository;
import Authentication_Service.Authentication.utils.JwtUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public Course createCourse(String name, String description, String createdBy) {
        Set<String> userRoles = jwtUtil.extractRolesFromContext();
        if (!userRoles.contains("ROLE_ADMIN") && !userRoles.contains("ROLE_SUPER_ADMIN")) {
            throw new AccessDeniedException("You are not an admin. Only admins can create courses.");
        }

        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setCreatedBy(createdBy);
        return courseRepository.save(course);
    }

    public Set<Course> getCoursesCreatedByAdmin(String adminUsername) {
        return courseRepository.findByCreatedBy(adminUsername);
    }
    
    public void assignCourseToUser(String courseId, String userId) {
        Set<String> userRoles = jwtUtil.extractRolesFromContext();
        String username = jwtUtil.extractUsernameFromContext();
    
        // Check if the user is an Admin or Super Admin
        if (!userRoles.contains("ROLE_ADMIN") && !userRoles.contains("ROLE_SUPER_ADMIN")) {
            throw new AccessDeniedException("Only Admins and Super Admins can assign courses to users.");
        }
    
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    
        // Ensure the admin can only assign users to the courses they created
        if (userRoles.contains("ROLE_ADMIN") && !course.getCreatedBy().equals(username)) {
            throw new AccessDeniedException("You can only assign users to courses you created.");
        }
    
        // Ensure courses set is initialized
        if (user.getCourses() == null) {
            user.setCourses(new HashSet<>());
        }
    
        // Check if the user is already assigned to the course
        boolean alreadyAssigned = user.getCourses().stream()
                .anyMatch(c -> c.getId().equals(courseId));
    
        if (alreadyAssigned) {
            throw new RuntimeException("User already assigned to this course!");
        }
    
        // Assign the course to the user
        user.getCourses().add(course);
        userRepository.save(user);
    }
    
    // public void assignCourseToUser(String courseId, String userId) {
    //     Set<String> userRoles = jwtUtil.extractRolesFromContext();
        
    //     if (!userRoles.contains("ROLE_ADMIN") && !userRoles.contains("ROLE_SUPER_ADMIN")) {
    //         throw new AccessDeniedException("Only Admins can assign courses to users.");
    //     }
    
    //     Course course = courseRepository.findById(courseId)
    //             .orElseThrow(() -> new RuntimeException("Course not found"));
    
    //     User user = userRepository.findById(userId)
    //             .orElseThrow(() -> new RuntimeException("User not found"));
    
    //     // Ensure courses set is initialized
    //     if (user.getCourses() == null) {
    //         user.setCourses(new HashSet<>());
    //     }
    
    //     // Check if the user is already assigned to the course
    //     boolean alreadyAssigned = user.getCourses().stream()
    //             .anyMatch(c -> c.getId().equals(courseId));
    
    //     if (alreadyAssigned) {
    //         throw new RuntimeException("User already assigned to this course!");
    //     }
    
    //     // Assign the course to the user
    //     user.getCourses().add(course);
    //     userRepository.save(user);
    // }
    

    public Set<Course> getCoursesForUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return user.getCourses() != null ? user.getCourses() : new HashSet<>();
    }

    // public Set<Course> getCoursesCreatedByAdmin(String adminUsername) {
    //     return courseRepository.findByCreatedBy(adminUsername);
    // }

    public Set<Course> getAllCourses() {
        return new HashSet<>(courseRepository.findAll()); // Fetch all courses from database
    }
}
