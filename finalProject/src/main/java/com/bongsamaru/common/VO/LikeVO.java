package com.bongsamaru.common.VO;

import lombok.Data;

@Data
public class LikeVO {
	private Integer likeId;
	private String memId;
	private Integer boardId;
	private String category;
	private Integer heartNo;
}
