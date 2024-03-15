package com.bongsamaru.file.web;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.file.service.FileService;
import com.bongsamaru.user.service.UserService;

@Controller
public class FileController {
	@Autowired
	UserService userService;
	
    @Autowired
    private FileService fileService;
    
    
    @PostMapping("/uploadsAjax")
    @ResponseBody
    public List<String> uploadFile(HttpSession session ,Principal prin ,@RequestPart MultipartFile[] uploadFiles, String code, @RequestParam(required = false, defaultValue = "0") int codeNo, String codeUser, int type) {

        try {
            return fileService.uploadFiles(uploadFiles, code, codeNo, codeUser, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 혹은 적절한 에러 메시지와 함께 응답을 반환
        }
        
    }
    
    @GetMapping("/changeProfile")
    public void changeProfile(HttpSession session ,Principal prin) {
    	String profile = userService.findProfile(prin.getName());
    	session.setAttribute("profile", profile);
    }
    @GetMapping("/deleteFiles")
    @ResponseBody
    public String deleteFile(@RequestParam(name="filePath") String filePath) {
        boolean isDeleted = fileService.deleteFile(filePath);
        if(isDeleted==true) {
        	return "ok";
        }else {
        	return "no";
        }
    }
}
