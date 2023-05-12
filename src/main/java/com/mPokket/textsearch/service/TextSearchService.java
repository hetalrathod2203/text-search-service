package com.mPokket.textsearch.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mPokket.textsearch.entity.Content;
import com.mPokket.textsearch.model.CreateTextContentRequest;
import com.mPokket.textsearch.repository.ContentRepository;

@Service
public class TextSearchService {

	private ContentRepository contentRepository;
	private Map<String, List<UUID>> myCache;
	
	@Autowired
    public TextSearchService(ContentRepository contentRepository, Map<String, List<UUID>> myCache) {
		super();
		this.contentRepository = contentRepository;
		this.myCache = new HashMap();
	}

	public List<Content> upload(MultipartFile file, String keywords) throws Exception {
		
		Content content = Content.builder().content(new String(file.getBytes(), StandardCharsets.UTF_8)).createdAt(new Date()).fileName(file.getName())
				.type("FILE").keywords(keywords).build();
		contentRepository.save(content);
		return Arrays.asList(content);
	}
	
	public List<Content> save(CreateTextContentRequest req, String keywords) throws Exception {
		Content content = Content.builder().content(new String(req.getContent().getBytes(), StandardCharsets.UTF_8)).createdAt(new Date())
				.type("TEXT").keywords(keywords).build();
		contentRepository.save(content);
		return Arrays.asList(content);
	}
	
	public List<Content> getAll() throws Exception {

		List<Content> contents = new ArrayList();
		for (Content cont : contentRepository.findAll()) {
			contents.add(cont);
		}
		return contents;
	}
  
    @Cacheable(value = "searchCache")
    public List<UUID> searchCache(String text) {
    	return  myCache.get(text);
    }
  
    public List<Content> searchText(String text){
    	
    	List<UUID> results = searchCache(text.toLowerCase());
    	List<Content> contents = new ArrayList();
        if (results == null) {
        	results = new ArrayList<>();
        	List<Content> allSearchedContents = performSearch(text.toLowerCase());
            for(Content content : allSearchedContents) {
            	results.add(content.getId());
            }
            if(!results.isEmpty())
            	myCache.put(text.toLowerCase(), results);
            return allSearchedContents;
        } else {
        	contents.addAll(performSearchAndNotInIds(text.toLowerCase(), results));
            contents.addAll(getContentBasedOnId(results));	
    		return contents;
        }
    }
    
    private List<Content> performSearch(String text) {
    	
    	List<Content> contents = performSearchOnKeywords(text);
    	List<UUID> results = new ArrayList();
        for(Content content : contents) {
        	results.add(content.getId());
        }
    	
        contents.addAll(performSearchAndNotInIds(text, results));
    	return contents;
    }
    
    private List<Content> performSearchOnKeywords(String text) {
    	
    	return contentRepository.findByKeywords(text);
    }
    
    private List<Content> performSearchAndNotInIds(String text, 
    		List<UUID> ids) {

    	return contentRepository.findBySearchTextAndNotInIds(text, ids);
    }
	
    private List<Content> getContentBasedOnId(List<UUID> ids) {
        // perform the search operation and return the results
    	List<Content> contents = new ArrayList();
		for(UUID uuid : ids) {
			contents.add(contentRepository.findById(uuid).get());
		}
    	return contents;
    }
}
