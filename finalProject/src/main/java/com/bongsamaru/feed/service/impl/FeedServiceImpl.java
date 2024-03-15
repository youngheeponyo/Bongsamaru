package com.bongsamaru.feed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.common.VO.CommentsVO;
import com.bongsamaru.common.VO.InterestVO;
import com.bongsamaru.common.VO.LikeVO;
import com.bongsamaru.feed.mapper.FeedMapper;
import com.bongsamaru.feed.service.FeedService;
import com.bongsamaru.feed.service.FeedVO;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class FeedServiceImpl implements FeedService {

	@Autowired
	FeedMapper feedmapper;
	
	@Override
	public List<FeedVO> getFeedList(String memId) {
		// TODO Auto-generated method stub
		return feedmapper.getFeedList(memId);
	}
	
	@Override
	public List<FeedVO> getFeedFirstList(String memId) {
		// TODO Auto-generated method stub
		return feedmapper.getFeedFirstList(memId);
	}
	
	@Override
	public List<InterestVO> getInterestList(String memId) {
		// TODO Auto-generated method stub
		return feedmapper.getInterestList(memId);
	}
	
	@Override
	public int changeLike(String memId, Integer boardId) {
		int result = 0;
		// feedService.getLike 체크
		LikeVO vo = new LikeVO();
		vo.setMemId(memId);
		vo.setBoardId(boardId);
	    LikeVO likeStatus = feedmapper.getLike(vo);
	    boolean isLiked = likeStatus != null ? true : false;
        if (!isLiked) {
            // 좋아요가 되어있지 않은 경우, 좋아요 추가
            result = feedmapper.insertLike(memId,boardId);
        } else {
            // 좋아요가 이미 되어있는 경우, 좋아요 삭제
            result = feedmapper.deleteLike(memId,boardId);
        }
		return result;
		
	}
	
	
	@Override
	public List<FeedVO> getFeedDetail(String memId, Integer boardId) {
		return feedmapper.getFeedDetail(memId, boardId);
	}
	
	@Override
	public List<CommentsVO> getFeedCommentsList(Integer detailCode) {
		// TODO Auto-generated method stub
		return feedmapper.getFeedCommentsList(detailCode);
	}
}

