package com.expressivemodel.modelprogresstracker.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expressivemodel.modelprogresstracker.model.Image;
import com.expressivemodel.modelprogresstracker.model.Project;
import com.expressivemodel.modelprogresstracker.model.User;
import com.expressivemodel.modelprogresstracker.service.ImageService;
import com.expressivemodel.modelprogresstracker.service.ProjectService;
import com.expressivemodel.modelprogresstracker.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ImageService imageService;

	@GetMapping("/projects")
	public List<Project> getUserProjects(Authentication auth) {
		User user = (User) userService.findByUsername(auth.getName()).orElseThrow();
		if (user == null)
			return Collections.EMPTY_LIST;
		if (user.getRole().equals("USER")) {
			return projectService.getProjectsForUser(user.getAssignedProjectIds());
		}

		if (user.getRole().equals("ADMIN")) {
			return projectService.getAllProjects();
		}
		return Collections.EMPTY_LIST;
	}

	@GetMapping("/projects/{projectId}")
	public List<Image> getImageByProjectId(@PathVariable("projectId") String projectId) {
		return imageService.getImagesByProject(projectId);
	}
	
	@GetMapping("/login")
	public ResponseEntity<Map<String, String>> login(Authentication auth) {
		User user = (User) userService.findByUsername(auth.getName()).orElseThrow();		
		Map<String, String> response = Map.of("role", user.getRole());
	    return ResponseEntity.ok(response);
	}
}