package com.flexpag.paymentscheduler.enums;

public enum PaymentStatus {

	PENDING(1), 
	PAID(2);

	private int code;

	private PaymentStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PaymentStatus valueOf(int code) {
		for (PaymentStatus value : PaymentStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Código de status inválido.");
	}
}
