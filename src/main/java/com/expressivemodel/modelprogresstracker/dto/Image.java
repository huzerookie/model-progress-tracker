package com.expressivemodel.modelprogresstracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	private String imageId;
	private String fileName;
	private String url;
	private String projectId;
	private String contentType;
	private String uploadDate;
}
