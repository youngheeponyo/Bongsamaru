package com.bongsamaru.feed.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class FeedVO {
	
	private Integer boardId;
	private String memId;
	private String memNick;
	private double tempChange;
	private String memProfile;
	private String title;
	private String content;
	private String filePath;
	private Integer likes;
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date writeDate;
	
}
