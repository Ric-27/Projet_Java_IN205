package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import com.app.exception.DaoException;
import com.app.model.*;
import com.app.exception.DaoException;

public interface BookDao {
	public List<Book> getList() throws DaoException;
	public Book getById(int id) throws DaoException;
	public int create(String title, String autor, String isbn) throws DaoException;
	public void update(Book book) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}
