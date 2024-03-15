package com.bongsamaru.common.VO;

import java.util.Date;

import lombok.Data;

@Data
public class CommentsVO {
	private Integer commId;
	private String memId;
	private String content;
	private Date commDate;
	private Date modDate;
	private Integer parentId;
	private String code;
	private Integer detailCate;
	private Integer detailCode;
	private Integer boardId;
	  
	private Integer replyId;
	private String replyMemId;
	private String replyContent;
	private Date replyCommDate;
	private Integer replyParentId;
	private String replyCode;
}
