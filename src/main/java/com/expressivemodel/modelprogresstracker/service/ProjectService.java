package com.expressivemodel.modelprogresstracker.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.expressivemodel.modelprogresstracker.model.Project;
import com.expressivemodel.modelprogresstracker.repository.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;    

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
    	project.setProjectId(UUID.randomUUID().toString());
        return projectRepository.save(project);
    }

/*    public Project uploadImage(String projectId, MultipartFile file) throws IOException {
        Project project = projectRepository.findById(projectId).orElseThrow();
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir + File.separator + filename);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());
        //project.getImagePaths().add(filename);
        return projectRepository.save(project);
    }
*/
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    
    public List<Project> getProjectsForUser(List<String> projectIds) {
        return projectRepository.findByProjectIds(projectIds);
    }
}
