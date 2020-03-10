package com.app.model;

/**
 * book
 */
public class book {
    private Integer id;
	private String title;
    private String author;
    private String isbn;	
	
	public book() {
		this(0,"","","");
	}
	public book(String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}
	public book(Integer id, String title, String author, String isbn) {
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
				+ "Author: " + author
				+ "ISBN:" + isbn
				+ "}";
	}
    
}