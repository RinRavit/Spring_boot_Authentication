package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Course;
import Authentication_Service.Authentication.entity.User;
import Authentication_Service.Authentication.repository.CourseRepository;
import Authentication_Service.Authentication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(String name, String description) {
        Course course = new Course(name, description);
        return courseRepository.save(course);
    }

    public void assignCourseToUser(String courseId, String userId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (courseOptional.isEmpty()) {
            throw new RuntimeException("Course not found");
        }

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Course course = courseOptional.get();
        User user = userOptional.get();

        course.getAssignedUsers().add(user.getId());
        courseRepository.save(course);
    }

    public Set<Course> getCoursesForUser(String userId) {
        return courseRepository.findAll().stream()
                .filter(course -> course.getAssignedUsers().contains(userId))
                .collect(Collectors.toSet());
    }
}
