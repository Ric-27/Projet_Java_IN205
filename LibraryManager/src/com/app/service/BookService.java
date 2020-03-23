package com.app.service;

import java.util.List;
import com.app.exception.ServiceException;
import com.app.model.Book;

public interface BookService {

	/**
	 * Calls the Book Dao to get the list of books in the database.
	 * @return the list of books in the database.
	 * @throws ServiceException
	 */
	public List<Book> getList() throws ServiceException;

	/**
	 * Calls the Book Dao to get the list of available books in the database.
	 * @return the list of available books in the database.
	 * @throws ServiceException
	 */
	public List<Book> getListDispo() throws ServiceException;


	/**
	 * Calls the Book Dao to get the book identified with the given id.
	 * @param id of the book.
	 * @return the book with the given id.
	 */
	public Book getById(int id) throws ServiceException;

	/**
	 * Calls the Dao to create a book with the given parameters.
	 * @param titre
	 * @param auteur
	 * @param isbn
	 * @return the id in the database of the book created.
	 * @throws ServiceException
	 */
	public int create(String titre, String auteur, String isbn) throws ServiceException;

	/**
	 * Calls the Book Dao to update the values of a book in the database.
	 * @param book with the up-to-date parameters.
	 * @throws ServiceException
	 */
	public void update(Book book) throws ServiceException;

	/**
	 * Calls the Book Dao to delete a book in the database.
	 * @param id of the book.
	 * @throws ServiceException
	 */
	public void delete(int id) throws ServiceException;

	/**
	 * Calls the Boo Dao to get the number of books in the database.
	 * @return the number of the books in the database.
	 * @throws ServiceException
	 */
	public int count() throws ServiceException;
}
