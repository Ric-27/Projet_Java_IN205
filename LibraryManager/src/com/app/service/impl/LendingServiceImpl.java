package com.app.service.impl;

import java.time.LocalDate;
import java.util.List;
import com.app.service.*;
import com.app.model.*;
import com.app.exception.ServiceException;

public class LendingServiceImpl implements LendingService {
    @Override
    public List<Lending> getList() throws ServiceException{

    }
    @Override
    public List<Lending> getListCurrent() throws ServiceException{

    }
    @Override
    public List<Lending> getListCurrentByMember(int idMember) throws ServiceException{

    }
    @Override
    public List<Lending> getListCurrentByBook(int idBook) throws ServiceException{

    }
    @Override
    public Lending getById(int id) throws ServiceException{

    }
    @Override
    public void create(int idMember, int idBook, LocalDate dateLending) throws ServiceException{

    }
    @Override
    public void returnBook(int id) throws ServiceException{

    }
    @Override
    public int count() throws ServiceException{

    }
    @Override
    public boolean isBookDispo(int idBook) throws ServiceException{

    }
    @Override
	public boolean isLendingPossible(Member Member) throws ServiceException{
        
    }
    
}