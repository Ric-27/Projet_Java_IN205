package com.app.service.impl;

import com.app.service.*;
import com.app.model.*;
import java.util.List;
import com.app.exception.ServiceException;

public class MemberServiceImpl implements MemberService {
    @Override
    public List<Member> getList() throws ServiceException{

    }
    @Override
    public List<Member> getListMemberEmpruntPossible() throws ServiceException{

    }
    @Override
    public Member getById(int id) throws ServiceException{

    }
    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException{

    }
    @Override
    public void update(Member member) throws ServiceException{

    }
    @Override
    public void delete(int id) throws ServiceException{

    }
    @Override
	public int count() throws ServiceException{
        
    }
}