package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Project;
import Authentication_Service.Authentication.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        if (projectRepository.existsByNoProject(project.getNoProject())) {
            throw new RuntimeException("Project already exists");
        }
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(String id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Project updateProject(String id, Project updatedData) {
        Project existing = projectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Project not found"));

        existing.setNoProject(updatedData.getNoProject());
        existing.setMecanicProject(updatedData.getMecanicProject());
        existing.setDescription(updatedData.getDescription());
        existing.setCompetitivebid(updatedData.getCompetitivebid());
        existing.setTotalbider(updatedData.getTotalbider());
        existing.setTypeProject(updatedData.getTypeProject());
        existing.setDuration(updatedData.getDuration());
        existing.setStarted(updatedData.getStarted());
        existing.setEnded(updatedData.getEnded());
        existing.setNameProject(updatedData.getNameProject());
        existing.setAmountBudget(updatedData.getAmountBudget());
        existing.setStatusProject(updatedData.getStatusProject());

        return projectRepository.save(existing);
    }

    public void deleteProject(String id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }
        projectRepository.deleteById(id);
    }
}
