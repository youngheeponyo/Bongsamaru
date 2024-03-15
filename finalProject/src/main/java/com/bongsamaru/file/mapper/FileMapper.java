package com.bongsamaru.file.mapper;

import com.bongsamaru.file.service.FilesVO;

public interface FileMapper {
	public int insertFile(FilesVO vo);
	public int updateFile(FilesVO vo);
	public FilesVO getFileCheck(String code,String codeUser);
	
}
