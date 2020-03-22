package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import com.app.exception.DaoException;
import com.app.model.*;
import com.app.exception.DaoException;

public interface LendingDao {
	public List<Lending> getList() throws DaoException;
	public List<Lending> getListCurrent() throws DaoException;
	public List<Lending> getListCurrentByMembre(int idMembre) throws DaoException;
	public List<Lending> getListCurrentByLivre(int idLivre) throws DaoException;
	public Lending getById(int id) throws DaoException;
	public void create(int idMembre, int idLivre, LocalDate dateLending) throws DaoException;
	public void update(Lending lending) throws DaoException;
	public int count() throws DaoException;
}
