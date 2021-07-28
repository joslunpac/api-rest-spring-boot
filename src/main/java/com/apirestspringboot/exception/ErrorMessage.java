package com.apirestspringboot.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ErrorMessage {

	private int status;
	private String error;
	private List<String> message;
	private String path;

}
