package com.mPokket.textsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mPokket.textsearch.model.CreateTextContentRequest;
import com.mPokket.textsearch.model.Response;
import com.mPokket.textsearch.service.TextSearchService;
import com.mPokket.textsearch.util.Constants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/text")
@Slf4j
public class TextSearchController {

	private TextSearchService textSearchService;
	
	@Autowired
	public TextSearchController(TextSearchService textSearchService) {
		super();
		this.textSearchService = textSearchService;
	}

	@PostMapping(path = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String keywords) throws Exception {

		log.info(Constants.REQUEST_LOG, "File Upload");
		Response response = Response.builder().errorCode(0).errorMessage("")
				.response(textSearchService.upload(file, keywords)).build();
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> saveText(@RequestBody CreateTextContentRequest content,  @RequestParam String keywords) throws Exception {

		log.info(Constants.REQUEST_LOG, "Save Text");
		Response response = Response.builder().errorCode(0).errorMessage("")
				.response(textSearchService.save(content, keywords)).build();
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Response> search(@RequestParam String searchText) throws Exception {

		log.info(Constants.REQUEST_LOG, "Search");
		Response response = Response.builder().errorCode(0).errorMessage("")
				.response(textSearchService.searchText(searchText)).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
