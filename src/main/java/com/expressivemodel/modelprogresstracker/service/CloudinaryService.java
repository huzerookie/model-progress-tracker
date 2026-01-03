package com.expressivemodel.modelprogresstracker.service;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.expressivemodel.modelprogresstracker.util.CloudinaryUtil;

@Service
public class CloudinaryService {
    
    private Cloudinary cloudinary;
    private Logger log = LoggerFactory.getLogger(getClass());
    public CloudinaryService(Cloudinary cloudinary) {
    	this.cloudinary=cloudinary;
    }

    public String uploadImage(MultipartFile file, String projectId) throws IOException {
    	Map<?, ?> options = ObjectUtils.asMap(
    	        "folder", "expressive-model/projects/"+projectId
    	    );
        Map<?, ?> uploadResult = cloudinary.uploader()
                .upload(file.getBytes(), options);

        return uploadResult.get("secure_url").toString();
    }
    
    public boolean deleteImageByUrl(String imageUrl) {
        try {
            String publicId = CloudinaryUtil.extractPublicId(imageUrl);

            Map<?, ?> result = cloudinary.uploader()
                    .destroy(publicId, ObjectUtils.emptyMap());
            log.info("Deleted from cloudinary:{}",imageUrl);
            return "ok".equals(result.get("result"));

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}
