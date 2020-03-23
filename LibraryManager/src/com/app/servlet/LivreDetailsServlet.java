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

public class LivreDetailsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/livre_details")) {
			BookService bookServiceImpl = BookServiceImpl.getInstance();
			Book book = new Book();
			LendingService lendingServiceImpl = LendingServiceImpl.getInstance();
			List<Lending> lendingList = new ArrayList<>();
            int id = -1;
            if (request.getParameter("id") != null){
				id = Integer.parseInt(request.getParameter("id"));
			}
			try {
                if (id > -1) {
                    book = bookServiceImpl.getById(Integer.parseInt(request.getParameter("id")));
					lendingList = lendingServiceImpl.getListCurrentByBook(id);

					request.setAttribute("bookJSP", book);
                    request.setAttribute("lendingListJSP", lendingList);
                }
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
            }

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
			dispatcher.forward(request, response);
		}
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookServiceImpl = BookServiceImpl.getInstance();
		Book book = new Book();
		LendingService lendingServiceImpl = LendingServiceImpl.getInstance();
		List<Lending> lendingList = new ArrayList<>();

		try {
			bookServiceImpl.update(new Book(Integer.parseInt(request.getParameter("id")), request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("isbn")));
			
			book = bookServiceImpl.getById(Integer.parseInt(request.getParameter("id")));
			lendingList = lendingServiceImpl.getListCurrentByBook(Integer.parseInt(request.getParameter("id")));
			
			request.setAttribute("bookJSP", book);
			request.setAttribute("lendingListJSP", lendingList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			try {
				book = bookServiceImpl.getById(Integer.parseInt(request.getParameter("id")));
				lendingList = lendingServiceImpl.getListCurrentByBook(Integer.parseInt(request.getParameter("id")));

				request.setAttribute("bookJSP", book);
				request.setAttribute("lendingListJSP", lendingList);

			} catch (ServiceException ee) {
				System.out.println(ee.getMessage());
				ee.printStackTrace();
			}
			request.setAttribute("errorMessage", e.getMessage());

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}