package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import com.app.exception.DaoException;
import com.app.model.*;
import com.app.exception.DaoException;

public interface BookDao {
	/**
	 * Gets a list with all the books in the database.
	 * @return the list of all the books.
	 * @throws DaoException
	 */
	public List<Book> getList() throws DaoException;

	/**
	 * Finds the book in the database by comparing the id with the parameter given.
	 * @param id of the book desired.
	 * @return	the book.
	 * @throws DaoException
	 */
	public Book getById(int id) throws DaoException;

	/**
	 * Creates a new book in the database.
	 * @param titre title of the book.
	 * @param auteur author of the book.
	 * @param isbn	International code of the book.
	 * @return	the id of the book created.
	 * @throws DaoException
	 */
	public int create(String titre, String auteur, String isbn) throws DaoException;

	/**
	 * Update the values of the book with the same id in the database, with the ones given by the parameter.
	 * @param book up-to-date book.
	 * @throws DaoException
	 */
	public void update(Book book) throws DaoException;

	/**
	 * Erase a the book in the database with the id given.
	 * @param id of the book to be erased.
	 * @throws DaoException
	 */
	public void delete(int id) throws DaoException;

	/**
	 * Get the number of books in the database.
	 * @return the number of books in the database.
	 * @throws DaoException
	 */
	public int count() throws DaoException;
	
}
