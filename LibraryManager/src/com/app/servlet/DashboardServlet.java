package com.app.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.app.exception.*;
import com.app.service.*;
import com.app.service.impl.*;
import com.app.model.*;

public class DashboardServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("dashboard");
        System.out.println(action);
            if (action.equals("/dashboard")) {
            System.out.println(action);
            MemberService memberServiceImpl = MemberServiceImpl.getInstance();
            int countOfMembers = -1;
            try {
                countOfMembers = memberServiceImpl.count();
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            request.setAttribute("countOfMembersJSP", countOfMembers);

            BookService bookServiceImpl = BookServiceImpl.getInstance();
            int countOfBooks = -1;

            try {
                countOfBooks = bookServiceImpl.count();
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            request.setAttribute("countOfBooksJSP", countOfBooks);

            LendingService loanServiceImpl = LendingServiceImpl.getInstance();
            int countOfLoans = -1;

            try {
                countOfLoans = loanServiceImpl.count();
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            request.setAttribute("countOfLoansJSP", countOfLoans);

            List<Lending> currentLoans = new ArrayList<>();

            try {
                currentLoans = loanServiceImpl.getListCurrent();
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            request.setAttribute("currentLoansJSP", currentLoans);
            System.out.println("here");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
            System.out.println("there");
            dispatcher.forward(request, response);
        }
    }
}