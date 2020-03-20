package com.app.model;

/**
 * Book
 */
public class Book {
    private Integer id;
	private String title;
    private String author;
    private String isbn;	
	
	public Book() {
		this(0,"","","");
	}
	public Book(String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}
	public Book(Integer id, String title, String author, String isbn) {
		this(title,author,isbn);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" 
				+ "Id: " + id + ", "
				+ "Title:" + title + ", "
				+ "Author: " + author + ", "
				+ "ISBN:" + isbn
				+ "}";
	}
}