package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.*;
import com.app.dao.impl.*;
import com.app.exception.*;
import com.app.service.*;
import com.app.model.*;

public class BookServiceImpl implements BookService {

    private static BookServiceImpl instance;
	private BookServiceImpl() { }	
	public static BookService getInstance() {
		if(instance == null) {
			instance = new BookServiceImpl();
		}
		return instance;
    }
    
    @Override
    public List<Book> getList() throws ServiceException{
        BookDao bookDaoImpl = BookDaoImpl.getInstance();
        List<Book> books = new ArrayList<>();
        try {
            books = bookDaoImpl.getList();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getListDispo() throws ServiceException{
        LendingService loanServiceImpl = LendingServiceImpl.getInstance();
        List<Book> books = new ArrayList<>();
        List<Book> booksDispo = new ArrayList<>();
        try {
            books = this.getList();
            for (int i = 0; i < books.size(); i++) {
                if(loanServiceImpl.isBookDispo(books.get(i).getId())){
                    booksDispo.add(books.get(i));
                }
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return booksDispo;
    }

    @Override
    public Book getById(int id) throws ServiceException{
        BookDao bookDaoImpl = BookDaoImpl.getInstance();
        Book book = new Book();
        try {
            book = bookDaoImpl.getById(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws ServiceException{
        BookDao bookDaoImpl = BookDaoImpl.getInstance();
        int id = -1;
        if(titre == null){
            throw new ServiceException("the title cannot be empty");
        } else {
            try {
                id = bookDaoImpl.create(titre, auteur, isbn);
            } catch (DaoException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return id; 
        }        
    }

    @Override
    public void update(Book book) throws ServiceException{
        BookDao bookDaoImpl = BookDaoImpl.getInstance();
        if(book.getTitle() == null){
            throw new ServiceException("the title cannot be empty");
        } else {
            try {
                bookDaoImpl.update(book);
            } catch (DaoException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } 
    }

    @Override
    public void delete(int id) throws ServiceException{
        BookDao bookDaoImpl = BookDaoImpl.getInstance();
        LendingService loanServiceImpl = LendingServiceImpl.getInstance();
        try {
            bookDaoImpl.delete(id);
            loanServiceImpl.returnBook(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
	public int count() throws ServiceException{
        BookDao bookDaoImpl = BookDaoImpl.getInstance();
        int count = -1;
        try {
            count = bookDaoImpl.count();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return count;
    }
}