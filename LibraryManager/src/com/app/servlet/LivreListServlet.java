package com.app.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.exception.*;
import com.app.service.*;
import com.app.model.*;

public class LivreListServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		
		if (servletPath.equals("/livre_list")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_list.jsp");
			dispatcher.forward(request, response);
		}
    }

}