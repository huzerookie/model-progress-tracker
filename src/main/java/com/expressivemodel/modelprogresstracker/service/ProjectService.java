package com.expressivemodel.modelprogresstracker.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expressivemodel.modelprogresstracker.model.Project;
import com.expressivemodel.modelprogresstracker.repository.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
    	project.setProjectId(UUID.randomUUID().toString());
        return projectRepository.save(project);
    }

    public Project uploadImage(String projectId, MultipartFile file) throws IOException {
        Project project = projectRepository.findById(projectId).orElseThrow();
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir + File.separator + filename);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());
        //project.getImagePaths().add(filename);
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    
    public List<Project> getProjectsForUser(List<String> projectIds) {
        return projectRepository.findByProjectIds(projectIds);
    }
}
