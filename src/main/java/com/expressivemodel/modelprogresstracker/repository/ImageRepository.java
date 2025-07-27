package com.expressivemodel.modelprogresstracker.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.expressivemodel.modelprogresstracker.model.Image;

public interface ImageRepository extends MongoRepository<Image, String> {
    List<Image> findByProjectId(String projectId);
    Image findByImageId(String imageId);
}