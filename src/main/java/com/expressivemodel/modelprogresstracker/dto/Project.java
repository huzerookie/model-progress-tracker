package com.expressivemodel.modelprogresstracker.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {    
    private String projectId;
    private String name;
    private String description;
    private List<String> imageIds;
    private String createdBy;
}