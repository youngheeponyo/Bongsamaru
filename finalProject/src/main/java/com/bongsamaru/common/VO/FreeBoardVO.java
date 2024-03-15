package com.bongsamaru.common.VO;

import java.util.Date;
import lombok.Data;

@Data
public class FreeBoardVO {
	private Integer boardNo;
	private String title;
	private String content;
	private Date writeDate;
	private Integer viewCnt;
	private String writer;
	private Integer RN;
	private Integer volId;
}
