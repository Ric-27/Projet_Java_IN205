package com.app.service;

import java.time.LocalDate;
import java.util.List;

import com.app.exception.ServiceException;
import com.app.model.*;

public interface LendingService {

	/**
	 * Calls the loans Dao to get the list of the loans in the database
	 * @return the list with all the loans in the database
	 * @throws ServiceException
	 */
	public List<Lending> getList() throws ServiceException;

	/**
	 * Calls the loans Dao to get the list of the active loans.
	 * @return the list of the active loans.
	 * @throws ServiceException
	 */
	public List<Lending> getListCurrent() throws ServiceException;

	/**
	 * Calls the loans Dao to get the list of the active loans of the member.
	 * @param idMember
	 * @return the list of the active loans of the member
	 * @throws ServiceException
	 */
	public List<Lending> getListCurrentByMember(int idMember) throws ServiceException;

	/**
	 * Calls the loans Dao to get the list of the active loans for the book with that id.
	 * @param idBook
	 * @return the list with the active loans for the book
	 * @throws ServiceException
	 */
	public List<Lending> getListCurrentByBook(int idBook) throws ServiceException;

	/**
	 *  Calls the loans Dao to get the loan in the database with the given id
	 * @param id
	 * @return the loan.
	 * @throws ServiceException
	 */
	public Lending getById(int id) throws ServiceException;

	/**
	 * Calls the loans Dao to create a new loan in the database
	 * @param idMember id of the member
	 * @param idBook id of the book
	 * @param dateLending lending date
	 * @throws ServiceException
	 */
	public void create(int idMember, int idBook, LocalDate dateLending) throws ServiceException;

	/**
	 * Calls the loans Dao to update the parameter 'dateRetour' of the loan in the database with the given id.
	 * @param id of the loan.
	 * @throws ServiceException
	 */
	public void returnBook(int id) throws ServiceException;

	/**
	 * Calls the loans Dao to get the number of loans in the database.
	 * @return the number of loans in the database.
	 * @throws ServiceException
	 */
	public int count() throws ServiceException;

	/**
	 * Calls the loans Dao to determine if a book is available to be lent.
	 * @param idBook id of the book.
	 * @return true if the book is available.
	 * @throws ServiceException
	 */
	public boolean isBookDispo(int idBook) throws ServiceException;

	/**
	 * Calls the loans Dao to determine if a member is able to lend a book.
	 * @param Member
	 * @return true if the member can lend a book.
	 * @throws ServiceException
	 */
	public boolean isLendingPossible(Member Member) throws ServiceException;
}
