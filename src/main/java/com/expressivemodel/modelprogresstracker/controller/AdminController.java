package com.expressivemodel.modelprogresstracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.expressivemodel.modelprogresstracker.model.Image;
import com.expressivemodel.modelprogresstracker.model.Project;
import com.expressivemodel.modelprogresstracker.model.User;
import com.expressivemodel.modelprogresstracker.service.ImageService;
import com.expressivemodel.modelprogresstracker.service.ProjectService;
import com.expressivemodel.modelprogresstracker.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private ImageService imageService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;

	@PostMapping("/project")
	public Project createProject(@RequestBody Project project, Authentication auth) {
		project.setCreatedBy(auth.getName());
		return projectService.createProject(project);
	}	

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/upload")
	public List<Image> uploadImage(Authentication auth, @RequestParam("file") MultipartFile[] files, @RequestParam("projectId") String projectId)
			throws Exception {
		List<Image> imageList = new ArrayList<>();
		for(MultipartFile file: files) {
		 Image image = imageService.storeImage(file, projectId, auth.getName());
		 imageList.add(image);
		}
		return imageList;
	}
	
	@DeleteMapping("/image/{imgId}")
	public Image deleteImage(Authentication auth, @PathVariable("imgId") String imageId) {
		return imageService.deleteImage(imageId);
	}

	@GetMapping("/images")
	public List<Image> fetchImagesByProjectId(@RequestParam("projectId") String projectId) {
		return imageService.getImagesByProject(projectId);
	}
	
	@GetMapping("/projects")
    public List<Project> getUserProjects(Authentication auth) {        
        return projectService.getAllProjects();
    }
	
	@DeleteMapping("/user/{username}")
	public User deleteUser(@PathVariable("username") String username) {
		return userService.deleteUser(username);
	}

}
