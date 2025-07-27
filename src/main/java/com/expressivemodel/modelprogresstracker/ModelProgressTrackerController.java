package com.expressivemodel.modelprogresstracker;

import static com.expressivemodel.modelprogresstracker.temp.DummyRecordGenerator.fetchDummyImage;
import static com.expressivemodel.modelprogresstracker.temp.DummyRecordGenerator.fetchDummyProject;
import static com.expressivemodel.modelprogresstracker.temp.DummyRecordGenerator.fetchDummyUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expressivemodel.modelprogresstracker.dto.Image;
import com.expressivemodel.modelprogresstracker.dto.Project;
import com.expressivemodel.modelprogresstracker.dto.User;

@RestController
public class ModelProgressTrackerController {

	@GetMapping("/users")
	public User getUsers() {
		return fetchDummyUser();
	}
	
	@GetMapping("/projects")
	public Project getProjects() {
		return fetchDummyProject();
	}
	
	@GetMapping("/images")
	public Image getImages() {
		return fetchDummyImage();
	}

	
}
