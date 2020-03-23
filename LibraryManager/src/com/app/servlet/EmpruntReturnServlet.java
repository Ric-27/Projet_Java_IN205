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

public class EmpruntReturnServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/emprunt_return")) {
            int id = -1;
            if (request.getParameter("id") != null){
				id = Integer.parseInt(request.getParameter("id"));
			}
			LendingService lendingServiceImpl = LendingServiceImpl.getInstance();
			List<Lending> loanList = new ArrayList<>();
			
			try {
                loanList = lendingServiceImpl.getListCurrent();
                if (id > -1)
                    request.setAttribute("id", id);
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("lendingListJSP", loanList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
			dispatcher.forward(request, response);
		}
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LendingService lendingServiceImpl = LendingServiceImpl.getInstance();
		List<Lending> lendingList = new ArrayList<>();
		
        try {
            if (request.getParameter("id") == null)
                throw new ServletException("Error. Choose a Loan");
            else {
				lendingServiceImpl.returnBook(Integer.parseInt(request.getParameter("id")));
			
				lendingList = lendingServiceImpl.getListCurrent();
				
				request.setAttribute("lendingListJSP", lendingList);
				//request.setAttribute("show", "all");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

			try {
				lendingList = lendingServiceImpl.getListCurrent();
			} catch (ServiceException serviceException) {
				System.out.println(serviceException.getMessage());
				serviceException.printStackTrace();
			}

			request.setAttribute("lendingListJSP", lendingList);
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}