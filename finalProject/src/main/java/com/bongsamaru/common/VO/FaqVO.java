package com.bongsamaru.common.VO;

import lombok.Data;

@Data
public class FaqVO {
	private Integer faqId;
	private String category;
	private String title;
	private String content;
	private boolean expanded;
	private Integer RN;
}
