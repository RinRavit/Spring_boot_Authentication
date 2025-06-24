package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
    boolean existsByNoProject(String noProject);
}
