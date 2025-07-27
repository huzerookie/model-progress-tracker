package com.expressivemodel.modelprogresstracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	@Id
	private String id;
	private String imageId;
	private String fileName;
	private String url;
	private String projectId;
	private String contentType;
	private String uploadDate;
	private String uploadedBy;
}
