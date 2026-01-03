package com.expressivemodel.modelprogresstracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloudinaryUtil {
	private static Logger log = LoggerFactory.getLogger(CloudinaryUtil.class);
    public static String extractPublicId(String imageUrl) {
        // Remove query params
        String cleanUrl = imageUrl.split("\\?")[0];

        // Remove version part (/v12345/)
        cleanUrl = cleanUrl.replaceAll(".*/upload/v\\d+/", "");

        // Remove file extension
        String cleanUrlWithoutExtension = cleanUrl.substring(0, cleanUrl.lastIndexOf('.'));
        log.info("URL:{} --- cleanURL:{}",imageUrl, cleanUrlWithoutExtension);
        return cleanUrlWithoutExtension;
    }
}

