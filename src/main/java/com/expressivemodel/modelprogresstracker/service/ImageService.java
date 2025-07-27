package com.expressivemodel.modelprogresstracker.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expressivemodel.modelprogresstracker.model.Image;
import com.expressivemodel.modelprogresstracker.repository.ImageRepository;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.client-img-read-dir}")
    private String clientImgReadDir;
    
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image storeImage(MultipartFile file, String projectId, String uploadedBy) throws Exception {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir + File.separator + filename);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        Image image = new Image();
        image.setImageId(UUID.randomUUID().toString());
        image.setProjectId(projectId);
        image.setFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setUploadedBy(uploadedBy);
        image.setUrl(clientImgReadDir + File.separator + filename);
        image.setUploadDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
        return imageRepository.save(image);
    }

    public List<Image> getImagesByProject(String projectId) {
        return imageRepository.findByProjectId(projectId);
    }

	public Image deleteImage(String imageId) {
		Image image = imageRepository.findByImageId(imageId);
		imageRepository.delete(image);
		return image;
	}
}
