package com.bongsamaru.feed.mapper;

import java.util.List;

import com.bongsamaru.common.VO.CommentsVO;
import com.bongsamaru.common.VO.InterestVO;
import com.bongsamaru.common.VO.LikeVO;
import com.bongsamaru.feed.service.FeedVO;


public interface FeedMapper {
	
	// 메인페이지 전체리스트
	public List<FeedVO> getFeedList(String memId);
	// 피드 첫번째 이미지와 타이틀불러오기
	public List<FeedVO> getFeedFirstList(String memId);
	// 회원별 관심사 조회
	public List<InterestVO> getInterestList(String memId);
	// 좋아요 버튼 추가	
	public int insertLike(String memId, Integer boardId);
	// 좋아요 여부 확인
	public LikeVO getLike(LikeVO likeVO);
	// 좋아요 되있으면 삭제
	public int deleteLike(String memId, Integer boardId);
	// 해당하는 피드 게시글 불러오기
	public List<FeedVO> getFeedDetail(String memId,Integer boardId);
	// 해당하는 피드 댓글 불러오기
	public List<CommentsVO> getFeedCommentsList(Integer detailCode);
}


