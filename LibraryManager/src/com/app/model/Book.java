package com.app.model;

/**
 * Class corresponding to the books of the database.
 */
public class Book {
    private Integer id;
	private String title;
    private String author;
    private String isbn;	
	
	/**
	 * Default constructor
	 */
	public Book() {
		this(0,"","","");
	}

	/**
	 * Constructor
	 * @param title
	 * @param author
	 * @param isbn
	 */
	public Book(String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}

	/**
	 * Constructor
	 * @param id
	 * @param title
	 * @param author
	 * @param isbn
	 */
	public Book(Integer id, String title, String author, String isbn) {
		this(title,author,isbn);
		this.id = id;
	}
	
	/**
	 * Gets the id of the book.
	 * @return id of the book.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id of the book
	 * @param id of the book
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the title of the book.
	 * @return the title of the book.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the book.
	 * @param title of the book.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the author of the book.
	 * @return Author of the book.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author of the book.
	 * @param author of the book.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the ISBN of the book.
	 * @return isbn of the book.
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * Sets the isbn of the book.
	 * @param isbn of the book.
	 */
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