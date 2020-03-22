package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.service.*;
import com.app.model.*;
import com.app.dao.*;
import com.app.dao.impl.*;
import com.app.exception.*;

public class MemberServiceImpl implements MemberService {

    private static MemberServiceImpl instance;
	private MemberServiceImpl() { }	
	public static MemberService getInstance() {
		if(instance == null) {
			instance = new MemberServiceImpl();
		}
		return instance;
    }

    @Override
    public List<Member> getList() throws ServiceException{
        MemberDao memberDaoImpl = MemberDaoImpl.getInstance();
        List<Member> members = new ArrayList<>();
        try {
            members = memberDaoImpl.getList();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public List<Member> getListMemberEmpruntPossible() throws ServiceException{
        LendingService loanServiceImpl = LendingServiceImpl.getInstance();
        List<Member> members = new ArrayList<>();
        List<Member> membersLoanPossible = new ArrayList<>();
        try {
            members = this.getList();
            for (int i = 0; i < members.size(); i++) {
                if(loanServiceImpl.isLendingPossible(members.get(i))){
                    membersLoanPossible.add(members.get(i));
                }
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return membersLoanPossible;
    }

    @Override
    public Member getById(int id) throws ServiceException{
        MemberDao memberDaoImpl = MemberDaoImpl.getInstance();
        Member member = new Member();
        try {
            member = memberDaoImpl.getById(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException{
        MemberDao memberDaoImpl = MemberDaoImpl.getInstance();
        int id = -1;
        if(nom == null || prenom == null || nom == "" || prenom == ""){
            throw new ServiceException("the name and/or lastname cannot be empty");
        } else {
            try {
                id = memberDaoImpl.create(nom.toUpperCase(), prenom, adresse, email, telephone);
            } catch (DaoException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return id; 
        }
    }

    @Override
    public void update(Member member) throws ServiceException{
        MemberDao memberDaoImpl = MemberDaoImpl.getInstance();
        if(member.getName() == null || member.getLastname() == null || member.getName() == "" || member.getLastname() == ""){
            throw new ServiceException("the name and/or lastname cannot be empty");
        } else {
            try {
                member.setLastname(member.getLastname().toUpperCase());
                memberDaoImpl.update(member);
            } catch (DaoException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } 
    }

    @Override
    public void delete(int id) throws ServiceException{
        MemberDao memberDaoImpl = MemberDaoImpl.getInstance();
        LendingService loanServiceImpl = LendingServiceImpl.getInstance();
        try {
            memberDaoImpl.delete(id);
            List<Lending> loanList = loanServiceImpl.getListCurrentByMember(id);
			for (Lending loan : loanList)
				loanServiceImpl.returnBook(loan.getBook().getId());
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
	public int count() throws ServiceException{
        MemberDao memberDaoImpl = MemberDaoImpl.getInstance();
        int count = -1;
        try {
            count = memberDaoImpl.count();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return count;
    }
}