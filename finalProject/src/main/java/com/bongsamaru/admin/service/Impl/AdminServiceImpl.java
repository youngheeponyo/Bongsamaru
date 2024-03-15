package com.bongsamaru.admin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bongsamaru.admin.mapper.AdminMapper;
import com.bongsamaru.admin.service.AdminService;
import com.bongsamaru.common.VO.AlertVO;
import com.bongsamaru.common.VO.BoardVO;
import com.bongsamaru.common.VO.CodeVO;
import com.bongsamaru.common.VO.CommentsVO;
import com.bongsamaru.common.VO.DonationLedgerVO;
import com.bongsamaru.common.VO.DonationVO;
import com.bongsamaru.common.VO.FacilityVO;
import com.bongsamaru.common.VO.FaqVO;
import com.bongsamaru.common.VO.PageVO;
import com.bongsamaru.common.VO.RemittanceVO;
import com.bongsamaru.common.VO.ReportVO;
import com.bongsamaru.common.VO.TagVO;
import com.bongsamaru.common.VO.UserVO;
import com.bongsamaru.common.VO.VolunteerVO;
import com.bongsamaru.dona.service.DonaVO;
import com.bongsamaru.file.service.FilesVO;
import com.bongsamaru.mypage.service.DonledgerVO;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminMapper userMapper;

	@Override
	public List<CodeVO> subCodeList(String mainCodeId) {
		return userMapper.subCodeList(mainCodeId);
	}
	
	@Override
	public List<UserVO> getUserList(PageVO pageVO) {
		return userMapper.getUserList(pageVO);
	}
	
	@Override
	public int getUserListCnt(PageVO pageVO) {
		return userMapper.getUserListCnt(pageVO);
	}
	
	@Override
	public List<BoardVO> getBoardList(PageVO pageVO) {
		return userMapper.getBoardList(pageVO);
	}
	
	@Override
	public List<FacilityVO> getFacilityList(PageVO pageVO) {
		return userMapper.getFacilityList(pageVO);
	}
	
	@Override
	public int getFacilityCnt(PageVO pageVO) {
		return userMapper.getFacilityCnt(pageVO);
	}
	
	@Override
	public List<DonationVO> getDonationList(PageVO vo) {
		return userMapper.getDonationList(vo);
	}

	@Override
	public int getDonationCnt(PageVO pageVO) {
		return userMapper.getDonationCnt(pageVO);
	}
	
	@Override
	public List<FaqVO> getFaqList(PageVO pageVO) {
		return userMapper.getFaqList(pageVO);
	}

	@Override
	public void insertNotice(BoardVO boardVO) {
		userMapper.insertNotice(boardVO);
	}

	@Override
	public UserVO getUserOne(String memId) {
		return userMapper.getUserOne(memId);
	}

	@Override
	public VolunteerVO volCount(String memId) {
		return userMapper.volCount(memId);
	}

	@Override
	public DonationLedgerVO donCount(String memId) {
		return userMapper.donCount(memId);
	}
	
	@Override
	public FacilityVO getFacilityInfo(String facId) {
		return userMapper.getFacilityInfo(facId);
	}

	@Override
	public DonationVO getDonOne(Integer donId) {
		return userMapper.getDonOne(donId);
	}

	@Override
	public int updateFacApp(String facId) {
		return userMapper.updateFacApp(facId);
	}

	@Override
	public int updateDonApp(String facId) {
		return userMapper.updateDonApp(facId);
	}
	
	@Override
	public int updateDonRegApp(String donId) {
		return userMapper.updateDonRegApp(donId);
	}

	@Override
	public BoardVO getNoticeOne(String category, Integer detailCate) {
		return userMapper.getNoticeOne(category, detailCate);
	}

	@Override
	public int delNotice(String category, String detailCate) {
		return userMapper.delNotice(category, detailCate);
	}

	@Override
	public int faqInsert(FaqVO faqVO) {
		return userMapper.faqInsert(faqVO);
	}

	@Override
	public FaqVO getFaqOne(Integer faqId) {
		return userMapper.getFaqOne(faqId);
	}

	@Override
	public int delFaq(Integer faqId) {
		return userMapper.delFaq(faqId);
	}

	@Override
	public int updateNotice(BoardVO boardVO) {
		return userMapper.updateNotice(boardVO);
	}

	@Override
	public List<ReportVO> getReportList(PageVO vo) {
		return userMapper.getReportList(vo);
	}
	
	@Override
	public int getReportCnt(PageVO pageVO) {
		return userMapper.getReportCnt(pageVO);
	}

	@Transactional
	@Override
	public int inquireComments(CommentsVO commentsVO) {
		userMapper.inquireComments(commentsVO);
		return userMapper.updateInquire(commentsVO.getDetailCate());
	}


	@Override
	public CommentsVO inquireCommentOne(Integer detailCode) {
		return userMapper.inquireCommentOne(detailCode);
	}

	@Override
	public int updateReport(Integer reqCode,Integer repId) {
		return userMapper.updateReport(reqCode,repId);
	}

	@Override
	public List<FilesVO> selectFile(String codeNo) {
		return userMapper.selectFile(codeNo);
	}

	@Override
	public int delFile(String filePath) {
		return userMapper.delFile(filePath);
	}

	@Override
	public List<DonledgerVO> DonationKing() {
		return userMapper.DonationKing();
	}

	@Override
	public List<TagVO> tagList(TagVO vo) {
		return userMapper.tagList(vo);
	}

	@Override
	public List<VolunteerVO> memMeetList(String memId) {
		return userMapper.memMeetList(memId);
	}

	@Override
	public List<DonaVO> donationLedgerList(PageVO pageVO) {
		return userMapper.donationLedgerList(pageVO);
	}

	@Override
	public List<DonaVO> facDonLedgerList(PageVO pageVO) {
		return userMapper.facDonLedgerList(pageVO);
	}

	@Override
	public int donationLedgerCnt(PageVO pageVO) {
		return userMapper.donationLedgerCnt(pageVO);
	}

	@Override
	public int facDonLedgerCnt(PageVO pageVO) {
		return userMapper.facDonLedgerCnt(pageVO);
	}

	@Override
	public List<DonaVO> donationSettlement() {
		return userMapper.donationSettlement();
	}

	@Override
	public DonaVO checkFacDonation(Integer donId) {
		return userMapper.checkFacDonation(donId);
	}

	@Override
	public List<RemittanceVO> remittanceList() {
		return userMapper.remittanceList();
	}
	
	@Transactional
	@Override
	public int insertRemittance(RemittanceVO remittanceVO) {
		//송금 확인코드 변경
		userMapper.updatePaidCode(remittanceVO.getDonId());
		//송금 테이블에 삽입
		return userMapper.insertRemittance(remittanceVO);
	}

	@Override
	public List<AlertVO> alertList() {
		return userMapper.alertList();
	}

	@Override
	public int getBoardCnt(PageVO pageVO) {
		return userMapper.getBoardCnt(pageVO);
	}

	@Override
	public int getFaqCnt(PageVO pageVO) {
		return userMapper.getFaqCnt(pageVO);
	}

	@Override
	public List<DonaVO> getDonaList(PageVO pageVO) {
		return userMapper.getDonaList(pageVO);
	}

	@Override
	public int getDonaCnt(PageVO pageVO) {
		return userMapper.getDonaCnt(pageVO);
	}

	@Override
	public List<VolunteerVO> meetingList(PageVO pageVO) {
		return userMapper.meetingList(pageVO);
	}

	@Override
	public int meetingCnt(PageVO pageVO) {
		return userMapper.meetingCnt(pageVO);
	}

	@Override
	public List<VolunteerVO> facVolunteerList(PageVO pageVO) {
		return userMapper.facVolunteerList(pageVO);
	}

	@Override
	public int facVolunteerCnt(PageVO pageVO) {
		return userMapper.facVolunteerCnt(pageVO);
	}

	@Override
	public List<DonaVO> donaReviewList(PageVO pageVO) {
		return userMapper.donaReviewList(pageVO);
	}

	@Override
	public int donaReviewCnt(PageVO pageVO) {
		return userMapper.donaReviewCnt(pageVO);
	}

	@Override
	public int updateDonReview(DonaVO vo) {
		return userMapper.updateDonReview(vo);
	}

	@Override
	public DonaVO donaReviewInfo(Integer donRevId) {
		return userMapper.donaReviewInfo(donRevId);
	}

	@Override
	public List<VolunteerVO> dailyAllList() {
		return userMapper.dailyAllList();
	}

}