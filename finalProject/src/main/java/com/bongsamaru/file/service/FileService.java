package com.bongsamaru.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired; // @Autowired 어노테이션 사용
import org.springframework.beans.factory.annotation.Value; // @Value 어노테이션 사용
import org.springframework.stereotype.Service; // @Service 어노테이션 사용
import org.springframework.web.multipart.MultipartFile; // MultipartFile 인터페이스 사용

import com.bongsamaru.file.mapper.FileMapper; // FileMapper 사용

@Service
public class FileService {
    
    @Autowired
    private FileMapper fileMapper;

    @Value("${file.upload.path}")
    private String uploadPath;
    
    
    
    private final long MAX_SIZE = 50 * 1024 * 1024; // 50MB in bytes
    
    public List<String> uploadFiles(MultipartFile[] uploadFiles, String code, int codeNo, String user, int type) throws IOException {
        List<String> imageList = new ArrayList<>();
        
        for(MultipartFile uploadFile : uploadFiles) {
            if (uploadFile.getSize() > MAX_SIZE) {
                System.err.println("File size exceeds the maximum limit of 50MB");
                continue; // Skip this file
            }
            
            if (!isAllowedFileType(uploadFile)) {
                System.err.println("This file type is not allowed");
                continue; // Skip this file
            }

            String uploadFileName = handleFileUpload(uploadFile, code, codeNo, user, type);
            imageList.add("/upload/" + uploadFileName); // 이미지 URL 형식으로 변환하여 추가
        }

        return imageList;
    }

    private boolean isImageFile(MultipartFile file) {
        return file.getContentType() != null && file.getContentType().startsWith("image") && file.getContentType().startsWith("zip");
    }

    private String handleFileUpload(MultipartFile uploadFile, String code, int codeNo, String user, int type) throws IOException {
        printFileInfo(uploadFile);
        String uploadFileName = prepareFilePath(uploadFile);
        saveFileMetadata(uploadFile, uploadFileName, code, codeNo, user , type);
        return uploadFileName;
    }

    private void printFileInfo(MultipartFile uploadFile) {
        long fileSize = uploadFile.getSize();
        String contentType = uploadFile.getContentType();
        System.out.println("fileSize : " + fileSize);
        System.out.println("contentType : " + contentType);
    }

    private String prepareFilePath(MultipartFile uploadFile) {
        try {
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName != null ? originalName.substring(originalName.lastIndexOf("//") + 1) : "";
            System.out.println("fileName : " + fileName);

            String contentType = uploadFile.getContentType() != null ? uploadFile.getContentType().split("/")[0] : "";
            String folderPath = makeFolder(contentType); // 파일 유형에 따른 폴더 생성
            String uuid = UUID.randomUUID().toString();
            String savedFileName = uuid + "_" + fileName; // 실제 저장되는 파일 이름
            String uploadFileName = folderPath + "/" + savedFileName; // DB에 저장될 경로
            Path savePath = Paths.get(uploadPath + File.separator + folderPath, savedFileName); // 로컬에 저장될 전체 경로
            uploadFile.transferTo(savePath); // 파일 저장
            return uploadFileName; // DB에 저장될 경로 반환
        } catch (IOException e) {
            System.err.println("File upload failed: " + e.getMessage());
            return null;
        }
    }


    private void saveFileMetadata(MultipartFile uploadFile, String filePath, String code, int codeNo, String codeUser, int type) {
    	if(type == 0) {
    		
    		String originalName = uploadFile.getOriginalFilename();
    		long fileSize = uploadFile.getSize();
    		String contentType = uploadFile.getContentType();
    		
    		
    		
    		FilesVO fileVO = fileMapper.getFileCheck(code,codeUser);
    		
    		if (fileVO != null) {
    			// 파일사이즈가 똑같은게 이미 존재하는 경우 업데이트 수행
    			fileVO.setFilePath("/upload/" + filePath);
    			fileVO.setFileName(originalName);
    			fileVO.setFileSize((int) fileSize);
    			fileVO.setExtension(contentType);
    			fileMapper.updateFile(fileVO);
    		} else {
    			// 파일이 존재하지 않는 경우 인서트 수행
    			fileVO = new FilesVO();
    			fileVO.setFilePath("/upload/" + filePath);
    			fileVO.setFileName(originalName);
    			fileVO.setFileSize((int) fileSize);
    			fileVO.setExtension(contentType);
    			fileVO.setCode(code);
    			fileVO.setCodeNo(codeNo);
    			fileVO.setCodeUser(codeUser);
    			fileMapper.insertFile(fileVO);
    		}
    	}else if(type==1) {
    		String originalName = uploadFile.getOriginalFilename();
    		long fileSize = uploadFile.getSize();
    		String contentType = uploadFile.getContentType();
    		
    		
    		
    		FilesVO fileVO = fileMapper.getFileCheck(code,codeUser);
    		System.out.println(fileVO);
    		
    		fileVO = new FilesVO();
			fileVO.setFilePath("/upload/" + filePath);
			fileVO.setFileName(originalName);
			fileVO.setFileSize((int) fileSize);
			fileVO.setExtension(contentType);
			fileVO.setCode(code);
			fileVO.setCodeNo(codeNo);
			fileVO.setCodeUser(codeUser);
			fileMapper.insertFile(fileVO);
    	}
    	
    }
        
        
    private String makeFolder(String contentType) {
        // 기본 경로에 연/월/일 폴더 생성
        String dateFolderPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String baseFolderPath = uploadPath + File.separator + dateFolderPath;
        String subFolderPath = "";

        // 파일 유형에 따라 서브 폴더 생성
        switch (contentType) {
            case "image":
                subFolderPath = "images";
                break;
            case "video":
                subFolderPath = "videos";
                break;
            case "audio":
                subFolderPath = "audios";
                break;
            case "application":
                subFolderPath = "documents";
                break;
            default:
                subFolderPath = "others";
                break;
        }

        // 전체 경로 조합
        String folderPath = baseFolderPath + '/' + subFolderPath;
        File uploadFolder = new File(folderPath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        return folderPath.replace(uploadPath + File.separator, ""); // DB에 저장될 경로 반환
    }
    private boolean isAllowedFileType(MultipartFile file) {
        String allowedFileTypes = "image,video,audio,application/pdf,application/zip"; // 허용된 파일 유형
        return file.getContentType() != null && allowedFileTypes.contains(file.getContentType().split("/")[0]);
    }
    
    //파일 삭제
    public boolean deleteFile(String filePath) {
        File file = new File("c:\\\\upload" + filePath);   //넘어오는 값이 이미 upload를 붙이고 넘어오기 때문에 경로는 그냥 이대로 저장해도 될까..?
        
        if (file.exists()) { // 파일이 존재하는지 확인
            return file.delete(); // 파일이 존재하면 삭제하고 결과를 반환
        }
        
        return false; // 파일이 존재하지 않으면 false 반환
    }
    
    
    
}