package com.bongsamaru.common.VO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "token")
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_seq_generator")
	@SequenceGenerator(name="token_seq_generator", sequenceName = "token_seq", allocationSize=1)
	private long id;
	
	 @Column(name = "token")
    private String token;

	 @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
	 @Column(name = "user_id")
    private String userId;
}