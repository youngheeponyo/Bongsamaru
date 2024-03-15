package com.bongsamaru.center.mapper;

import java.util.List;

import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FaqVO;
import com.bongsamaru.common.VO.PageVO;

public interface CenterMapper {

	public List<FaqVO> getFaqList(PageVO vo);
	public int getFaqCategoryCount(String category);
	public List<BoardVO> getNoticeList(PageVO vo);
	public List<BoardVO> getNoticeDetail(Integer boardId);
	public List<BoardVO> getInquiryDetail(Integer boardId);
	public List<BoardVO> getInquiryList(PageVO vo);
	public int getNoticeCount(PageVO vo);
	public int getInquiryCount(String memId);
	public int insertInquiry(BoardVO boardVO);
	public int updateViews(Integer boardId);
}
