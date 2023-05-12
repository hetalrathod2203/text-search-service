package com.mPokket.textsearch.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="contents")
public class Content {
	
    @Id
    @GeneratedValue()
    private UUID id;
    
    @Column(name = "file_name")
	private String fileName;
    
    @Column(nullable = false, name = "content")
    @Lob
	private String content;
    
    @Column(name = "type")
	private String type;
    
    @Column(name = "keywords")
	private String keywords;    
    
    @Column(name = "created_at", nullable = false)
	private Date createdAt;

}
