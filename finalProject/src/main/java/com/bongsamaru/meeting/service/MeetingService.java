package com.bongsamaru.meeting.service;

import java.util.List;

import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.FreeBoardVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.VolActReviewVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.file.service.FilesVO;

public interface MeetingService {
	public VolunteerVO meetingInfo(Integer volId);
	public int withdrawalMeeting(VolMemVO vo);
	public int approveMeeting(VolMemVO vo);
	public int meetingVolActListCnt(Integer volId);
	public List<VolActVO> meetingVolActListPaging(PageVO pageVO);
	public List<VolMemVO> meetingMemList(VolMemVO vo);
	public List<VolMemVO> MemVolActList(Integer volId,String memId);
	public List<VolMemVO> volCnt(VolMemVO vo);
	public int volActMemCnt(Integer volActId);
	public List<VolActVO> volActReviewListPaging(PageVO pageVO);
	public int volActReviewListCnt(VolActReviewVO vo);
	public int findMember(VolMemVO vo);
	public int chamMem(VolMemVO vo);
	public VolActReviewVO volReviewCnt(VolActReviewVO vo);
	public String findFile(String code,Integer codeNo);
	public int insertBoard(FreeBoardVO vo);
	public int findBoardNo();
	public int findReviewNo();
	public FreeBoardVO freeBoardInfo(FreeBoardVO vo);
	public int updateFreeBoard(FreeBoardVO vo);
	public int deleteFreeBoard(Integer volId,Integer boardNo);
	public VolActVO volActInfo(VolActVO vo);
	public int insertReview(VolActReviewVO vo);
	public VolActReviewVO ReviewInfo(VolActReviewVO vo);
	public int delReview(Integer reviewId);
	public VolActVO volActBoardInfo(Integer volActId);
	public int insertVolAct(VolActVO vo);
	public int delVolActBoard(Integer volActId);
	public int findVolActNo();
	public List<FreeBoardVO> getBoardList(PageVO pageVO);
	public int getBoardListCnt(Integer volId);
	//모임 삭제
	public int deleteMeeting(Integer volId);
	//모임 등록
	public int insertMeeting(VolunteerVO vo);
	public int insertTag(TagVO vo);
	//모임 수정
	public int updateMeeting(VolunteerVO vo);
	public int deleteFile(Integer codeNo);
	//봉사참여
	public int insertVolActMem(VolMemVO vo);
}
