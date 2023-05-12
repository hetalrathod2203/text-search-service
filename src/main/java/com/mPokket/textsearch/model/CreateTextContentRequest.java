package com.mPokket.textsearch.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTextContentRequest  {
	
	@NotBlank(message = "content connot be null or empty")
	private String content;

}
