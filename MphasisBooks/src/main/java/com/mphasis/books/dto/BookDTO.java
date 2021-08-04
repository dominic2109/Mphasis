package com.mphasis.books.dto;

import com.mphasis.books.entity.BookEntity;
/*
 * Data Transfer Object class for BookEntity
 * Note: Not used in this implementation but may used later.
 */
public class BookDTO {

	Integer bookID;
	String title;
	String authors;
	String average_rating;
	String isbn;
	String language_code;
	String ratings_count;
	Integer price;
	public Integer getBookID() {
		return bookID;
	}
	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getAverage_rating() {
		return average_rating;
	}
	public void setAverage_rating(String average_rating) {
		this.average_rating = average_rating;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getLanguage_code() {
		return language_code;
	}
	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}
	public String getRatings_count() {
		return ratings_count;
	}
	public void setRatings_count(String ratings_count) {
		this.ratings_count = ratings_count;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public static BookDTO valueOf(BookEntity book) {
		BookDTO bookDTO = new BookDTO();
		
		bookDTO.setBookID(book.getBookID());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setAuthors(book.getAuthors());
		bookDTO.setAverage_rating(book.getAverage_rating());
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setLanguage_code(book.getLanguage_code());
		bookDTO.setRatings_count(book.getRatings_count());
		bookDTO.setPrice(book.getPrice());
		
		return bookDTO;
	}
	
	public BookEntity createEntity() {
		BookEntity book = new BookEntity();
		book.setTitle(this.getTitle());
		book.setAuthors(this.getAuthors());
		book.setAverage_rating(this.getAverage_rating());
		book.setIsbn(this.getIsbn());
		book.setLanguage_code(this.getLanguage_code());
		book.setRatings_count(this.getRatings_count());
		book.setPrice(this.getPrice());
		
		return book;
	}
}
