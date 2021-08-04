package com.mphasis.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mphasis.books.entity.BookEntity;
import com.mphasis.books.exception.NoBookFoundException;
import com.mphasis.books.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	/*
	 * This method fetches all the books from the database, if no book(s) are present
	 * NoBookFoundException (Custom Exception) is thrown
	 */
	public List<BookEntity> getAllBooks() throws NoBookFoundException{
		
		List<BookEntity> returnable = bookRepository.getAllBooks();
		if(returnable.size()==0) {
			throw new NoBookFoundException();
		}
		return returnable;
	}
	
	/*
	 * This method fetches books from the database, according to the given bookID, if no book is found
	 * NoBookFoundException (Custom Exception) is thrown
	 */
	public BookEntity getBookByID(Integer bookId) throws NoBookFoundException{
		
		BookEntity returnable = bookRepository.getBookByBookID(bookId);
		if(returnable==null) {
			throw new NoBookFoundException();
		}
		return returnable;
	}
	
	/*
	 * This method fetches books from the database, according to the given search parameter
	 * titleReference, if no book is found NoBookFoundException (Custom Exception) is thrown
	 */
	public List<BookEntity> getBooksByTitleRefernce(String titleReference) throws NoBookFoundException{
		
		List<BookEntity> returnable = bookRepository.getBooksByReference(titleReference);
		if(returnable.size()==0) {
			throw new NoBookFoundException();
		}
		return returnable; 
	}
	
}
