package com.flexpag.paymentscheduler.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.enums.PaymentStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valuePayment;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Instant date;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	public Payment() {

	}

	public Payment(Long id, Double valuePayment, Instant date) {
		this.valuePayment = valuePayment;
		this.date = date;
		this.status = PaymentStatus.PENDING;
	}
}
