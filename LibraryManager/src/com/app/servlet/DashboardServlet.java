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
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String action = request.getServletPath();

        if (action == "/dashboard") {
            final MemberService memberServiceImpl = MemberServiceImpl.getInstance();
            int countOfMembers = -1;
            try {
                countOfMembers = memberServiceImpl.count();
            } catch (final ServiceException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            request.setAttribute("countOfMembersJSP", countOfMembers);

            final BookService bookServiceImpl = BookServiceImpl.getInstance();
            int countOfBooks = -1;

            try {
                countOfBooks = bookServiceImpl.count();
            } catch (final ServiceException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            request.setAttribute("countOfBooksJSP", countOfBooks);

            final LendingService loanServiceImpl = LendingServiceImpl.getInstance();
            int countOfLoans = -1;

            try {
                countOfLoans = loanServiceImpl.count();
            } catch (final ServiceException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            request.setAttribute("countOfLoansJSP", countOfLoans);

            List<Lending> currentLoans = new ArrayList<>();

            try {
                currentLoans = loanServiceImpl.getListCurrent();
            } catch (final ServiceException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            request.setAttribute("currentLoansJSP", currentLoans);

            final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
            dispatcher.forward(request, response);
        }
    }
}