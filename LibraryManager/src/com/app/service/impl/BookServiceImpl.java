package com.app.service.impl;

import com.app.service.*;
import com.app.model.*;
import java.util.List;
import com.app.exception.ServiceException;

public class BookServiceImpl implements BookService {
    @Override
    public List<Book> getList() throws ServiceException{
        try {
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public List<Book> getListDispo() throws ServiceException{

    }
    @Override
    public Book getById(int id) throws ServiceException{

    }
    @Override
    public int create(String titre, String auteur, String isbn) throws ServiceException{

    }
    @Override
    public void update(Book book) throws ServiceException{

    }
    @Override
    public void delete(int id) throws ServiceException{

    }
    @Override
	public int count() throws ServiceException{
        
    }
}