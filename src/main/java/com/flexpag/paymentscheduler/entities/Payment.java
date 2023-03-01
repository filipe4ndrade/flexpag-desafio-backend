package com.flexpag.paymentscheduler.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.enums.PaymentStatus;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valueOfPayment;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Instant date;
	
	@NonNull
	private PaymentStatus status;

	public Payment() {

	}

	public Payment(Long id, Double valueOfPayment, Instant date) {
		this.valueOfPayment = valueOfPayment;
		this.date = date;
		this.status = PaymentStatus.PENDING;
	}
}
