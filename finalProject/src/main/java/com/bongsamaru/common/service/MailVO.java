package com.bongsamaru.common.service;

import lombok.Data;

@Data
public class MailVO {
	private String recipientEmail;
	private String emailContent;
}
