package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import com.app.exception.DaoException;
import com.app.model.*;
import com.app.exception.DaoException;

public interface MemberDao {
	public List<Member> getList() throws DaoException;
	public Member getById(int id) throws DaoException;
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException;
	public void update(Member member) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}
