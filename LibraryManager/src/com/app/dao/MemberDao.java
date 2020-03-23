package com.app.dao;

import java.util.List;
import com.app.exception.DaoException;
import com.app.model.*;

public interface MemberDao {

	/**
	 * Gets the list with all the members in the database.
	 * @return the list with all the members in the database.
	 * @throws DaoException
	 */
	public List<Member> getList() throws DaoException;

	/**
	 * Gets the member in the database identified with the given id.
	 * @param id of the member.
	 * @return the member identified with the given id.
	 * @throws DaoException
	 */
	public Member getById(int id) throws DaoException;

	/**
	 * Creates a new member with the given parameters
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param email
	 * @param telephone
	 * @return the id of the member in the database.
	 * @throws DaoException
	 */
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException;

	/**
	 * Updates the parameters of a member in the database
	 * @param member with the up-to-date values.
	 * @throws DaoException
	 */
	public void update(Member member) throws DaoException;

	/**
	 * Deletes a member in the database
	 * @param id of the member
	 * @throws DaoException
	 */
	public void delete(int id) throws DaoException;

	/**
	 * Gets the number of members in the database.
	 * @return the number of members in the database.
	 * @throws DaoException
	 */
	public int count() throws DaoException;
}
