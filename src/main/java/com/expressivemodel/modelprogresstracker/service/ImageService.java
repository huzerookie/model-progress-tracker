package com.expressivemodel.modelprogresstracker.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expressivemodel.modelprogresstracker.model.Image;
import com.expressivemodel.modelprogresstracker.repository.ImageRepository;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private CloudinaryService cloudinaryService;
    private Logger log = LoggerFactory.getLogger(getClass());
    public ImageService(ImageRepository imageRepository, CloudinaryService cloudinaryService) {
        this.imageRepository = imageRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public Image storeImage(MultipartFile file, String projectId, String uploadedBy) throws Exception {
        Image image = new Image();
        image.setImageId(UUID.randomUUID().toString());
        image.setProjectId(projectId);
        image.setFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setUploadedBy(uploadedBy);
        image.setUrl(cloudinaryService.uploadImage(file, projectId));
        image.setUploadDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
        return imageRepository.save(image);
    }   

	public List<Image> getImagesByProject(String projectId) {
        return imageRepository.findByProjectId(projectId);
    }

	public Image deleteImage(String imageId) {
		Image image = imageRepository.findByImageId(imageId);
		boolean isDeleted = cloudinaryService.deleteImageByUrl(image.getUrl());
		if(!isDeleted) {
			log.error("{} url not deleted",image.getUrl());
			return image;
		}
		imageRepository.delete(image);
		log.info("Successfully deleted image from mongo:{}",image.getId());
		return image;
	}
}
