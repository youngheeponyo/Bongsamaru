package com.bongsamaru;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bongsamaru.common.VO.SubCodeVO;
import com.bongsamaru.common.service.CommonService;
import com.bongsamaru.securing.EncryptService;
import com.bongsamaru.user.service.UserService;

@SpringBootTest
public class PasswordEncodeTest {
		
	@Autowired
	UserService userService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	EncryptService enc;
	
	//@Test
	public void 결과값보기() {
	    String main = "p";
	    List<SubCodeVO> list = commonService.selectSubCode(main);

	    // 결과 리스트 출력
	    System.out.println(list);
	}
		//@Test
		public void 암호화() {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
			String result = encoder.encode("1234");
			System.out.println(result);
			assertTrue(encoder.matches("1234", result));
		}
		
	@Test
	public void 테스트() {
		String vo = "1234561234567";
		System.out.println(vo + "주민번호는?");
		String ssn = vo;
			String encSsn = enc.encryptSsn(ssn);
			System.out.println(encSsn + "!!!");
		
			byte[] encSsn2 = enc.stringToByteArray(encSsn);
			// 암호화 된것
			System.out.println();
			String encSs3 = enc.byteArrayToString(encSsn2);
			encSsn = enc.decryptSsn(encSs3);
			
			// 복호화 된것
			System.out.println(encSsn);
	}
}
