package com.ninyo.pacing.client.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ClientException extends RuntimeException {

	private HttpStatus status;
	private String code;
	private String message;

	public ClientException(HttpStatus status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}
}
