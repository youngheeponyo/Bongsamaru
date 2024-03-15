package com.bongsamaru.admin.mapper;


import java.util.List;

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



public interface AdminMapper {
	//스케줄러를 위해
	public List<VolunteerVO> dailyAllList();
	
	public List<CodeVO> subCodeList(String mainCodeId);
	//회원목록
	public List<UserVO> getUserList(PageVO pageVO);
	public int getUserListCnt(PageVO pageVO);
	//게시판
	public List<BoardVO> getBoardList(PageVO pageVO);
	public int getBoardCnt(PageVO pageVO);
	public List<FaqVO> getFaqList(PageVO pageVO);
	public int getFaqCnt(PageVO pageVO);
	//시설회원목록
	public List<FacilityVO> getFacilityList(PageVO pageVO);
	public int getFacilityCnt(PageVO pageVO);
	
	//기부리스트
	public List<DonaVO> getDonaList(PageVO pageVO);
	public int getDonaCnt(PageVO pageVO);
	
	//모임리스트
	public List<VolunteerVO> meetingList(PageVO pageVO);
	public int meetingCnt(PageVO pageVO);
	public List<TagVO> tagList(TagVO vo);
	//기부금 신청
	public List<DonationVO> getDonationList(PageVO vo);
	public int getDonationCnt(PageVO pageVO);
	
	public List<ReportVO> getReportList(PageVO pageVO);
	public int getReportCnt(PageVO pageVO);
	
	public List<DonledgerVO> DonationKing();
	//시설봉사리스트
	public List<VolunteerVO> facVolunteerList(PageVO pageVO);
	public int facVolunteerCnt(PageVO pageVO);
	
	public UserVO getUserOne(String memId);
	//공지사항
	public void insertNotice(BoardVO boardVO);
	public int updateNotice(BoardVO boardVO);
	public BoardVO getNoticeOne(String category,Integer detailCate);
	
	public int faqInsert(FaqVO faqVO);
	public VolunteerVO volCount(String memId);
	public DonationLedgerVO donCount(String memId);
	public FacilityVO getFacilityInfo(String facId);
	public DonationVO getDonOne(Integer donId);
	public int updateFacApp(String facId);
	public int updateDonApp(String facId);
	public int updateDonRegApp(String donId);

	public int delNotice(String category,String detailCate);
	public FaqVO getFaqOne(Integer faqId);
	public int delFaq(Integer faqId);
	
	public int inquireComments(CommentsVO commentsVO);
	public int updateInquire(Integer detailCate);
	public CommentsVO inquireCommentOne(Integer detailCode);
	public int updateReport(Integer reqCode,Integer repId);
	public List<FilesVO> selectFile(String codeNo);
	public int delFile(String filePath);
	public List<VolunteerVO> memMeetList(String memId);
	//기부금 장부 리스트
	public List<DonaVO> donationLedgerList(PageVO pageVO);
	public int donationLedgerCnt(PageVO pageVO);
	
	//시설별 장부 리스트
	public List<DonaVO> facDonLedgerList(PageVO pageVO);
	public int facDonLedgerCnt(PageVO pageVO);
	
	public List<DonaVO> donationSettlement();
	public DonaVO checkFacDonation(Integer donId);
	public List<RemittanceVO> remittanceList();
	public int updatePaidCode(Integer donId);
	public int insertRemittance(RemittanceVO remittanceVO);
	public List<AlertVO> alertList();
	
	//기부영수증 후기 승인처리리스트
	public List<DonaVO> donaReviewList(PageVO pageVO);
	public int donaReviewCnt(PageVO pageVO);
	public DonaVO donaReviewInfo(Integer donRevId);
	
	//기부후기 승인처리
	public int updateDonReview(DonaVO vo);
}
