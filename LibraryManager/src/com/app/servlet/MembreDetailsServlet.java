package com.app.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.exception.*;
import com.app.service.*;
import com.app.service.impl.*;
import com.app.model.*;

public class MembreDetailsServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/membre_details")) {
            // Set default value of the "id" option:
            int id = -1;
			// Change it while receiving another value:


            if (request.getParameter("memberId") != null)
				id = Integer.parseInt(request.getParameter("memberId"));

            // Get the list of loans of a chosen member:
            LendingService loanService = LendingServiceImpl.getInstance();
            List<Lending> loanList = new ArrayList<Lending>();

			try {
                if (id > -1) {
                    request.setAttribute("memberId", id);
                    loanList = loanService.getListCurrentByMember(id);
                    request.setAttribute("loanList", loanList);
                }
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
            }
        
			// Submit gathered information th the appropriate .jsp:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
			dispatcher.forward(request, response);
		}
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService memberService = MemberServiceImpl.getInstance();
		LendingService loanService = LendingServiceImpl.getInstance();
		
		try {
			memberService.update(new Member(Integer.parseInt(request.getParameter("id")),
															request.getParameter("nom"),
															request.getParameter("prenom"), 
															request.getParameter("adresse"),
															request.getParameter("email"), 
															request.getParameter("telephone"), 
															Member.Subscription.valueOf(request.getParameter("abonnement"))));

			request.setAttribute("loanList", loanService.getListCurrentByMember(Integer.parseInt(request.getParameter("id"))));
			request.setAttribute("id", Integer.parseInt(request.getParameter("id")));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

			try {
				request.setAttribute("loanList", loanService.getListCurrentByMember(Integer.parseInt(request.getParameter("id"))));
			} catch (ServiceException serviceException) {
				System.out.println(serviceException.getMessage());
				serviceException.printStackTrace();
			}
			
			request.setAttribute("id", Integer.parseInt(request.getParameter("id")));
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}