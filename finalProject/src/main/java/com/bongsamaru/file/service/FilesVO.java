package com.bongsamaru.file.service;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilesVO implements Serializable{
	private int fileId;
	private String filePath;
	private int fileOrder;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date uploadDate;
	private String fileName;
	private String extension;
	private int fileSize;
	private String code;
	private int codeNo;
	private String codeUser;
	
	
	
}
