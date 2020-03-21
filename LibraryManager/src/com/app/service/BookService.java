package com.app.service;

import java.util.List;

import java.util.List;
import com.app.exception.ServiceException;
import com.app.model.Book;

public interface BookService {

	public List<Book> getList() throws ServiceException;
	public List<Book> getListDispo() throws ServiceException;
	public Book getById(int id) throws ServiceException;
	public int create(String titre, String auteur, String isbn) throws ServiceException;
	public void update(Book book) throws ServiceException;
	public void delete(int id) throws ServiceException;
	public int count() throws ServiceException;
}
