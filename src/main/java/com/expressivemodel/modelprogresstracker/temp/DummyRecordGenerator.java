package com.expressivemodel.modelprogresstracker.temp;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.expressivemodel.modelprogresstracker.dto.Image;
import com.expressivemodel.modelprogresstracker.dto.Project;
import com.expressivemodel.modelprogresstracker.dto.User;

public class DummyRecordGenerator {
	public static User fetchDummyUser() {
		return new User("huzefa","password123","USER",Arrays.asList("p1","p2"));
	}
	
	public static Project fetchDummyProject() {
		return new Project("p1","Godrej","Deals in Buildings",Arrays.asList("img1","img2"),"admin");
	}
	
	public static Image fetchDummyImage() {
		return new Image("img1","test.img","/uploads/p1/test.jpg","p1","image/jpeg",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}
