package com.bongsamaru.center.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bongsamaru.center.mapper.CenterMapper;
import com.bongsamaru.center.service.CenterService;
import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FaqVO;
import com.bongsamaru.common.VO.PageVO;

@Service
public class CenterServiceImpl implements CenterService{
	
	@Autowired
	CenterMapper centerMapper;
	
    @Override
    public List<FaqVO> getFaqList(PageVO vo) {
    	// TODO Auto-generated method stub
    	return centerMapper.getFaqList(vo);
    }
    
    @Override
    public int getFaqCategoryCount(String category) {
    	// TODO Auto-generated method stub
    	return centerMapper.getFaqCategoryCount(category);
    }
    
    @Override
    public List<BoardVO> getNoticeList(PageVO vo) {
    	// TODO Auto-generated method stub
    	return centerMapper.getNoticeList(vo);
    }
    
    @Override
    public int getNoticeCount(PageVO vo) {
    	// TODO Auto-generated method stub
    	return centerMapper.getNoticeCount(vo);
    }
    
    @Override
    public List<BoardVO> getNoticeDetail(Integer boardId) {
    	// TODO Auto-generated method stub
    	return centerMapper.getNoticeDetail(boardId);
    }
    
    @Override
    public int insertInquiry(BoardVO boardVO) {
    	// TODO Auto-generated method stub
    	return centerMapper.insertInquiry(boardVO);
    }
    
    @Override
    public List<BoardVO> getInquiryList(PageVO vo) {
    	// TODO Auto-generated method stub
    	return centerMapper.getInquiryList(vo);
    }
    
    @Override
    public int getInquiryCount(String memId) {
    	// TODO Auto-generated method stub
    	return centerMapper.getInquiryCount(memId);
    }
    
    @Override
    public List<BoardVO> getInquiryDetail(Integer boardId) {
    	// TODO Auto-generated method stub
    	return centerMapper.getInquiryDetail(boardId);
    }
    
    @Override
    public int updateViews(Integer boardId) {
    	// TODO Auto-generated method stub
    	return centerMapper.updateViews(boardId);
    }
}
