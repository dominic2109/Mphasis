package com.mphasis.books.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.mphasis.books.entity.BookEntity;
import com.mphasis.books.exception.NoBookFoundException;
import com.mphasis.books.service.BookService;

/*
 * REST Controller class to handle incoming HTTP requests
 */

@RestController
@CrossOrigin
public class BookController {
	
	@Autowired
	BookService bookService;

	//Request Mapping to fetch all the books from database
	@GetMapping()
	public List<BookEntity> getAllBooks()throws NoBookFoundException{
		return bookService.getAllBooks();
	}
	
	/*Request Mapping to fetch book from database with given bookID reference variable
	 *If bookID is given in wrong format (non integer) MethodArgumentTypeMismatchException is thrown
	 */
	@GetMapping(value="/{bookID}")
	public BookEntity getBookById(@PathVariable Integer bookID)throws NoBookFoundException,MethodArgumentTypeMismatchException{
		return bookService.getBookByID(bookID);
	}
	
	//Request Mapping to fetch books from database with given search parameter 'titleTerm'
	@GetMapping(value="/search/{titleTerm}")
	public List<BookEntity> getBookById(@PathVariable String titleTerm)throws NoBookFoundException{
		return bookService.getBooksByTitleRefernce(titleTerm);
	}
}
