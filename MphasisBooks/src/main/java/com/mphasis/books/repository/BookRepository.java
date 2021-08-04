package com.mphasis.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mphasis.books.entity.BookEntity;
/*
 * Repository interface to communicate to DB. Language used in @Query is JPQL
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, String>{
	
	@Query(value="SELECT b FROM books b")
	public List<BookEntity> getAllBooks();
	
	public BookEntity getBookByBookID(Integer id);
	
	@Query(value="SELECT b FROM books b WHERE b.title LIKE %?1%")
	public List<BookEntity> getBooksByReference(String titleReference);

}
