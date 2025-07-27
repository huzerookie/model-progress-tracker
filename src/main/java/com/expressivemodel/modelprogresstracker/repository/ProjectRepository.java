package com.expressivemodel.modelprogresstracker.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.expressivemodel.modelprogresstracker.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByProjectId(String projectId);
    
    @Query("{ 'projectId': { '$in': ?0 } }")
    List<Project> findByProjectIds(List<String> projectIds);
}