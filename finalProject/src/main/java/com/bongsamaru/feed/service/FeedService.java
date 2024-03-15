package com.bongsamaru.feed.service;

import java.util.List;

import com.bongsamaru.common.VO.CommentsVO;
import com.bongsamaru.common.VO.InterestVO;

public interface FeedService {

	public List<FeedVO> getFeedList(String memId);
	public List<FeedVO> getFeedFirstList(String memId);
	public List<InterestVO> getInterestList(String memId);
	public int changeLike(String memId, Integer boardId);
	public List<FeedVO> getFeedDetail(String memId,Integer boardId);
	public List<CommentsVO> getFeedCommentsList(Integer detailCode);
		
}
