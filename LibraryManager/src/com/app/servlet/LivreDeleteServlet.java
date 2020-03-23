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

public class LivreDeleteServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/livre_delete")) {
            int id = -1;
            if (request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id"));

			BookService bookServiceImpl = BookServiceImpl.getInstance();
			Book book = new Book();
			
			try {
                book = bookServiceImpl.getById(Integer.parseInt(request.getParameter("id")));
                if (id > -1)
                    request.setAttribute("id", id);
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
			request.setAttribute("bookJSP", book);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
			dispatcher.forward(request, response);
		}
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookServiceImpl = BookServiceImpl.getInstance();		
		Book book = new Book();
		List<Book> bookList = new ArrayList<>();
		
        try {
			if (request.getParameter("id") == "" || request.getParameter("id") == null) {
				throw new ServletException("Error. No Book Chosen");
			} else {
				bookServiceImpl.delete(Integer.parseInt(request.getParameter("id")));
			
				bookList = bookServiceImpl.getList();
				request.setAttribute("bookListJSP", bookList);
				
				response.sendRedirect("livre_list");
			}			
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			try {
				book = bookServiceImpl.getById(Integer.parseInt(request.getParameter("id")));
			} catch (ServiceException serviceException) {
				System.out.println(serviceException.getMessage());
				serviceException.printStackTrace();
			}
			request.setAttribute("bookJSP", book);
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
			dispatcher.forward(request, response);
		}
	}
}