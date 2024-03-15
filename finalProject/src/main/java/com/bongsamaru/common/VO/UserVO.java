package com.bongsamaru.common.VO;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String memId;
	private String memPwd;
	private String memNick;
	private String memPhone;
	private String memZip;
	private String memAddr;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date memBdate;
	private String memDept;
	private String memEmail;
	private String donReceipt;
	private String memSsn;
	private String emailAgree;
	private String smsAgree;
	private String acctNum;
	private String bankName;
	private String memStat;
	private String memName;
	// 파일테이블의 프로필 정보 때문에 넣음
	private String memProfile;
	private Integer RN;
	
	
	@Builder
	public UserVO(String memEmail, String memId,String memPhone, String memPwd) { // 응답, 요청 다됨
		this.memId = memId;
		this.memEmail = memEmail;
		this.memPhone = memPhone;
		this.memPwd = memPwd;
	}
}
