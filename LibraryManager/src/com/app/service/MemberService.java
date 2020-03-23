package com.app.service;

import java.util.List;

import com.app.exception.ServiceException;
import com.app.model.Member;

public interface MemberService {

	/**
	 * Calls the members Dao to get the list of the members in the database.
	 * @return the list of the members in the database.
	 * @throws ServiceException
	 */
	public List<Member> getList() throws ServiceException;

	/**
	 * Calls the loans service and the members Dao to get the list of the members that can lend a book.
	 * @return the list of members that can lend a book.
	 * @throws ServiceException
	 */
	public List<Member> getListMemberEmpruntPossible() throws ServiceException;

	/**
	 * Calls the members Dao to get a member of the database
	 * @param id of the member
	 * @return the member
	 * @throws ServiceException
	 */
	public Member getById(int id) throws ServiceException;

	/**
	 * Calls the members Dao to create a new member in the database.
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param email
	 * @param telephone
	 * @return the id of the member created
	 * @throws ServiceException
	 */
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException;

	/**
	 * Calls the members Dao to update the update a member in the database.
	 * @param member with the up-to-date parameters.
	 * @throws ServiceException
	 */
	public void update(Member member) throws ServiceException;

	/**
	 * Calls the members Dao to delete a member of the database.
	 * @param id of the member.
	 * @throws ServiceException
	 */
	public void delete(int id) throws ServiceException;

	/**
	 * Calls the members Dao to get the number of members in the database.
	 * @return
	 * @throws ServiceException
	 */
	public int count() throws ServiceException;

}
