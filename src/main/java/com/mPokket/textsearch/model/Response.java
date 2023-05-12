package com.mPokket.textsearch.model;

import java.util.List;

import com.mPokket.textsearch.entity.Content;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

	private List<Content> response;
	private int errorCode;
	private String errorMessage;
	
}
