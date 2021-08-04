package com.mphasis.books.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*
 * BookEntity class to provide object relational mapping to the MYSQL database
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name="books")
public class BookEntity {

	@Id
	@Column(name="bookID", nullable=false)
	Integer bookID;
	
	@Column(name="title")
	String title;
	
	@Column(name="authors")
	String authors;
	
	@Column(name="average_rating")
	String average_rating;
	
	@Column(name="isbn")
	String isbn;
	
	@Column(name="language_code")
	String language_code;
	
	@Column(name="ratings_count")
	String ratings_count;
	
	@Column(name="price")
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

	public BookEntity() {
		super();
	}
	
	
}
