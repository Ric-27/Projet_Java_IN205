package com.app.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.exception.*;
import com.app.service.*;
import com.app.service.impl.*;
import com.app.model.*;

public class EmpruntAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        
        if (action.equals("/emprunt_add")) {
            MemberService memberServiceImpl = MemberServiceImpl.getInstance();
            List<Member> availableMemberList = new ArrayList<>();
            
            try {
                availableMemberList = memberServiceImpl.getListMemberEmpruntPossible();
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            
            request.setAttribute("availableMemberListJSP", availableMemberList);

            BookService bookServiceImpl = BookServiceImpl.getInstance();
            List<Book> availableBookList = new ArrayList<>();
            
            try {
                availableBookList = bookServiceImpl.getListDispo();
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            
            request.setAttribute("availableBookListJSP", availableBookList);
        
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LendingService lendingServiceImpl = LendingServiceImpl.getInstance();
        List<Lending> lendingList = new ArrayList<>();
        try {
            if (request.getParameter("idMembre") == null || request.getParameter("idLivre") == null)
                throw new ServletException("Can't lend. Information Missing.");
            else {
                lendingServiceImpl.create(Integer.parseInt(request.getParameter("idMembre")), Integer.parseInt(request.getParameter("idLivre")), LocalDate.now());
                lendingList = lendingServiceImpl.getListCurrent();
                request.setAttribute("lendingListJSP", lendingList);
                response.sendRedirect("emprunt_list");
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ServletException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

            MemberService memberServiceImpl = MemberServiceImpl.getInstance();
            List<Member> availableMemberList = new ArrayList<>();
            try {
                availableMemberList = memberServiceImpl.getListMemberEmpruntPossible();
            } catch (ServiceException ee) {
                System.out.println(ee.getMessage());
                ee.printStackTrace();
            }
            request.setAttribute("availableMemberListJSP", availableMemberList);

            BookService bookServiceImpl = BookServiceImpl.getInstance();
            List<Book> availableBookList = new ArrayList<>();
            try {
                availableBookList = bookServiceImpl.getListDispo();
            } catch (ServiceException ee) {
                System.out.println(ee.getMessage());
                ee.printStackTrace();
            }
            
            request.setAttribute("availableBookListJSP", availableBookList);
            request.setAttribute("errorMessage", e.getMessage());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
            dispatcher.forward(request, response);
        }
    }
}