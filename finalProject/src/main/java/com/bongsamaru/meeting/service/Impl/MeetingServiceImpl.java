package com.bongsamaru.meeting.service.Impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.common.VO.FreeBoardVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.VolActReviewVO;
import com.bongsamaru.common.VO.VolActVO;
import com.bongsamaru.common.VO.VolMemVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.meeting.mapper.MeetingMapper;
import com.bongsamaru.meeting.service.MeetingService;

@Service
public class MeetingServiceImpl implements MeetingService{

	@Autowired
	MeetingMapper mapper;
	
	@Override
	public VolunteerVO meetingInfo(Integer volId) {
		return mapper.meetingInfo(volId);
	}

	@Override
	public List<VolMemVO> meetingMemList(VolMemVO vo) {
		return mapper.meetingMemList(vo);
	}
	
	@Override
	public List<VolMemVO> volCnt(VolMemVO vo) {
		return mapper.volCnt(vo);
	}

	@Override
	public int volActMemCnt(Integer volActId) {
		return mapper.volActMemCnt(volActId);
	}

	@Override
	public List<VolActVO> meetingVolActListPaging(PageVO pageVO) {
		return mapper.meetingVolActListPaging(pageVO);
	}

	@Override
	public int meetingVolActListCnt(Integer volId) {
		return mapper.meetingVolActListCnt(volId);
	}

	@Override
	public List<VolActVO> volActReviewListPaging(PageVO pageVO) {
		return mapper.volActReviewListPaging(pageVO);
	}

	@Override
	public int volActReviewListCnt(VolActReviewVO vo) {
		return mapper.volActReviewListCnt(vo);
	}

	@Override
	public int findMember(VolMemVO vo) {
		return mapper.findMember(vo);
	}

	@Override
	public String findFile(String code,Integer codeNo) {
		return mapper.findFile(code,codeNo);
	}

	@Override
	public int insertBoard(FreeBoardVO vo) {
		return mapper.insertBoard(vo);
	}

	@Override
	public int findBoardNo() {
		return mapper.findBoardNo();
	}

	@Override
	public FreeBoardVO freeBoardInfo(FreeBoardVO vo) {
		return mapper.freeBoardInfo(vo);
	}

	@Override
	public int updateFreeBoard(FreeBoardVO vo) {
		return mapper.updateFreeBoard(vo);
	}

	@Transactional
	@Override
	public int deleteFreeBoard(Integer volId,Integer boardNo) {
		mapper.delFile("p13",boardNo);
		return mapper.deleteFreeBoard(volId,boardNo);
	}

	@Override
	public List<VolMemVO> MemVolActList(Integer volId, String memId) {
		return mapper.MemVolActList(volId, memId);
	}

	@Override
	public VolActVO volActInfo(VolActVO vo) {
		return mapper.volActInfo(vo);
	}

	@Override
	public int findReviewNo() {
		return mapper.findReviewNo();
	}

	@Override
	public int insertReview(VolActReviewVO vo) {
		return mapper.insertReview(vo);
	}

	@Override
	public VolActReviewVO ReviewInfo(VolActReviewVO vo) {
		return mapper.ReviewInfo(vo);
	}

	@Override
	public int delReview(Integer reviewId) {
		return mapper.delReview(reviewId);
	}

	@Override
	public VolActVO volActBoardInfo(Integer volActId) {
		return mapper.volActBoardInfo(volActId);
	}

	@Override
	public int insertVolAct(VolActVO vo) {
		return mapper.insertVolAct(vo);
	}

	@Override
	public int delVolActBoard(Integer volActId) {
		return mapper.delVolActBoard(volActId);
	}

	@Override
	public int findVolActNo() {
		return mapper.findVolActNo();
	}

	@Override
	public List<FreeBoardVO> getBoardList(PageVO pageVO) {
		return mapper.getBoardList(pageVO);
	}

	@Override
	public int getBoardListCnt(Integer volId) {
		return mapper.getBoardListCnt(volId);
	}

	@Override
	public int approveMeeting(VolMemVO vo) {
		return mapper.approveMeeting(vo);
	}

	@Override
	public int withdrawalMeeting(VolMemVO vo) {
		return mapper.withdrawalMeeting(vo);
	}

	@Override
	@Transactional
	public int deleteMeeting(Integer volId) {
		mapper.deleteMember(volId);
		return mapper.deleteMeeting(volId);
	}

	@Override
	public int insertMeeting(VolunteerVO vo) {
		return mapper.insertMeeting(vo);
	}

	@Override
	public int updateMeeting(VolunteerVO vo) {
		return mapper.updateMeeting(vo);
	}

	@Override
	public int deleteFile(Integer codeNo) {
		return mapper.deleteFile(codeNo);
	}

	@Override
	public int insertTag(TagVO vo) {
		return mapper.insertTag(vo);
	}

	@Override
	public int insertVolActMem(VolMemVO vo) {
		return mapper.insertVolActMem(vo);
	}

	@Override
	public int chamMem(VolMemVO vo) {
		return mapper.chamMem(vo);
	}

	@Override
	public VolActReviewVO volReviewCnt(VolActReviewVO vo) {
		return mapper.volReviewCnt(vo);
	}
}
