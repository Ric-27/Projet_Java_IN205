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

public class EmpruntListServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/emprunt_list")) {
            String show = "current";
            if (request.getParameter("show") != null && request.getParameter("show").contains("all"))
                show = "all";

			LendingService loanServiceImpl = LendingServiceImpl.getInstance();
			List<Lending> loanList = new ArrayList<>();
			
			try {
                if (show.equals("current")) {
                    loanList = loanServiceImpl.getListCurrent();
                } else {
                    loanList = loanServiceImpl.getList();
                }
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("lendingListJSP", loanList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
			dispatcher.forward(request, response);
		}
    }
}