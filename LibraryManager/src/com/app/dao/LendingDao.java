package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import com.app.exception.DaoException;
import com.app.model.*;

public interface LendingDao {

	/**
	 * Gets the list with all the loans in the database.
	 * @return the list with all the loans in the database
	 * @throws DaoException
	 */
	public List<Lending> getList() throws DaoException;

	/**
	 * Gets the list of the loans where the book has not been returned yet.
	 * @return the list of active loans.
	 * @throws DaoException
	 */
	public List<Lending> getListCurrent() throws DaoException;

	/**
	 * Gets the list of the active loans of a member
	 * @param idMembre
	 * @return	the list of the active loans of the member identified with the id idMembre.
	 * @throws DaoException
	 */
	public List<Lending> getListCurrentByMembre(int idMembre) throws DaoException;

	/**
	 * Gets the list of the active loans for a book.
	 * @param idLivre
	 * @return the list of the active laons of the book identified with the id idLivre
	 * @throws DaoException
	 */
	public List<Lending> getListCurrentByLivre(int idLivre) throws DaoException;

	/**
	 * Gets the loan in the database with that id.
	 * @param id
	 * @return the loan with that id.
	 * @throws DaoException
	 */
	public Lending getById(int id) throws DaoException;

	/**
	 * Creates a loan in the database with the corresponding book, member and date of lending.
	 * @param idMembre member identifier.
	 * @param idLivre book identifier.
	 * @param dateLending Lending date.
	 * @throws DaoException
	 */
	public void create(int idMembre, int idLivre, LocalDate dateLending) throws DaoException;

	/**
	 * Update the value of a loan.
	 * @param lending the loan with all the up-to-date parameters.
	 * @throws DaoException
	 */
	public void update(Lending lending) throws DaoException;

	/**
	 * Gets the number of loans in the database.
	 * @return the number of loans in the dabase.
	 * @throws DaoException
	 */
	public int count() throws DaoException;
}
