package com.bongsamaru.common.VO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserFacilityVO {
	   private String id;
	    private String pwd;
	    private String nick;
	    private String phone;
	    private String zip;
	    private String addr;
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date bdate;
	    private String dept;
	    private String email;
	    private String donReceipt;
	    private String ssn;
	    private String emailAgree;
	    private String smsAgree;
	    private String acctNum;
	    private String bankName;
	    private String memStat;
	    private String memName;
	    private String userType;
	    // Facility 테이블에만 존재하는 컬럼들
	    private String bizNum;
	    private String facType;
	    private String addrDetail;
	    private Date estDate;
	    private String facEmail;
	    private String facWeb;
	    private String facIntro;
	    private String repName;
	    private String repPhone;
	    private String facBank;
	    private String donAcct;
	    private String donApp;
	    private String memApp;
	    private String profile;
}
