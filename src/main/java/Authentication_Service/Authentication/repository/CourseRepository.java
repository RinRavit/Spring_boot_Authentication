package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {}
