package com.app.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.app.service.*;
import com.app.model.*;
import com.app.dao.LendingDao;
import com.app.dao.impl.LendingDaoImpl;
import com.app.exception.DaoException;
import com.app.exception.ServiceException;

public class LendingServiceImpl implements LendingService {

    private static LendingServiceImpl instance;
	private LendingServiceImpl() { }	
	public static LendingService getInstance() {
		if(instance == null) {
			instance = new LendingServiceImpl();
		}
		return instance;
    }


    @Override
    public List<Lending> getList() throws ServiceException{
        List<Lending> lendings = new ArrayList<>();
        LendingDao lendingDao = LendingDaoImpl.getInstance();
        try {
            lendings = lendingDao.getList();
            
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return lendings;
    }
    
    @Override
    public List<Lending> getListCurrent() throws ServiceException{
        List<Lending> lendings = new ArrayList<>();
        LendingDao lendingDao = LendingDaoImpl.getInstance();
        try {
            lendings = lendingDao.getListCurrent();
            
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return lendings;
    }
    @Override
    public List<Lending> getListCurrentByMember(int idMember) throws ServiceException{
        List<Lending> lendings = new ArrayList<>();
        LendingDao lendingDao = LendingDaoImpl.getInstance();
        try {
            lendings = lendingDao.getListCurrentByMembre(idMember);
            
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return lendings;
    }

    @Override
    public List<Lending> getListCurrentByBook(int idBook) throws ServiceException{
        List<Lending> lendings = new ArrayList<>();
        LendingDao lendingDao = LendingDaoImpl.getInstance();
        try {
            lendings = lendingDao.getListCurrentByLivre(idBook);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return lendings;
    }

    @Override
    public Lending getById(int id) throws ServiceException{
        Lending lending = new Lending();
        LendingDao lendingDao = LendingDaoImpl.getInstance();
        try {
            lending = lendingDao.getById(id);
            
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return lending;
    }

    @Override
    public void create(int idMember, int idBook, LocalDate dateLending) throws ServiceException{
        LendingDao lendingDao = LendingDaoImpl.getInstance();
        try {
            lendingDao.create(idMember, idBook, dateLending);
            
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void returnBook(int id) throws ServiceException{
        LendingDao lendingDao = LendingDaoImpl.getInstance();
        try {
            Lending loan = lendingDao.getById(id);
            loan.setReturnDate(LocalDate.now());
            lendingDao.update(loan);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int count() throws ServiceException{
        LendingDao lendingDao = LendingDaoImpl.getInstance();
        int loans = -1;
        try {
            loans = lendingDao.count();
            
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
        return loans;
    }

    @Override
    public boolean isBookDispo(int idBook) throws ServiceException{
        List<Lending> lendings = new ArrayList<>();
        boolean disponibility = true;
        try {
            lendings = this.getListCurrentByBook(idBook);
                if(!lendings.isEmpty())
                    disponibility = false;

            
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        return disponibility;
    }
    
    @Override
	public boolean isLendingPossible(Member member) throws ServiceException{
        LendingDao lendingDao = LendingDaoImpl.getInstance();
		List<Lending> lendings = new ArrayList<>();
		try {
			 lendings = lendingDao.getListCurrentByMembre(member.getId());
             return lendings.size() < member.getSubscription().getIndex()+1;		
				
		}  catch (DaoException e) {
			System.out.println(e.getMessage());			
		}
		return false;
    }
    
}