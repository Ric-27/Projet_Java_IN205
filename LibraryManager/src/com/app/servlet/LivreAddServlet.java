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

public class LivreAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/livre_add")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
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
			if (request.getParameter("titre") == null) {
				throw new ServletException("Can't create. Information Missing.");
			} else {
				int bookId = bookServiceImpl.create(request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("isbn"));
				book = bookServiceImpl.getById(bookId);
				lendingList = lendingServiceImpl.getListCurrentByBook(book.getId());

				request.setAttribute("bookJSP", book);
				request.setAttribute("lendingListJSP", lendingList);
				response.sendRedirect("livre_details?id=" + book.getId());
			}
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
			dispatcher.forward(request, response);
		}
	}
}