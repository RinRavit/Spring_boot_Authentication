package Authentication_Service.Authentication.repository;

import Authentication_Service.Authentication.entity.Project;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByNoProject(String noProject);
    Optional<Project> findByNoProject(String noProject);
}
