package com.expressivemodel.modelprogresstracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
	@Id
	private String id;
    private String projectId;
    private String name;
    private String description;
    private String createdBy;
    private String startDate;
    private String endDate;
}