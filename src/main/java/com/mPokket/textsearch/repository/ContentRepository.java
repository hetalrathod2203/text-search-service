package com.mPokket.textsearch.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mPokket.textsearch.entity.Content;

@Repository
public interface ContentRepository extends CrudRepository<Content, UUID> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM contents e WHERE lower(e.content) LIKE %:searchText%")
	List<Content> findBySearchText(@Param("searchText") String searchText);
	
	@Query(nativeQuery = true, value ="SELECT * FROM contents e WHERE lower(e.content) LIKE %:searchText% and e.id NOT IN :excludedIds ")
	List<Content> findBySearchTextAndNotInIds(@Param("searchText") String searchText, @Param("excludedIds") List<UUID> excludedIds);

	@Query(nativeQuery = true, value = "SELECT * FROM contents e WHERE lower(e.keywords) LIKE %:keyword%")
	List<Content> findByKeywords(@Param("keyword") String keyword);

}
