package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import com.app.exception.DaoException;
import com.app.model.*;
import com.app.exception.DaoException;

public interface LendingDao {
	public List<Lending> getList() throws DaoException;
	public List<Lending> getListCurrent() throws DaoException;
	public List<Lending> getListCurrentByMembre(int idMember) throws DaoException;
	public List<Lending> getListCurrentByLivre(int idBook) throws DaoException;
	public Lending getById(int id) throws DaoException;
	public void create(int idMember, int idBook, LocalDate dateLending) throws DaoException;
	public void update(Lending alending) throws DaoException;
	public int count() throws DaoException;
}
