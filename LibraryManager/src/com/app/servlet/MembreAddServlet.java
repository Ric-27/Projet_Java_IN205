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

public class MembreAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/membre_add")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService memberServiceImpl = MemberServiceImpl.getInstance();
		LendingService lendingServiceImpl = LendingServiceImpl.getInstance();
		Member member = new Member();
		List<Lending> lendingList = new ArrayList<>();
		
		try {
			if (request.getParameter("nom") == null || request.getParameter("prenom") == null) {
				throw new ServletException("Can't create. Information Missing.");
			} else {
				int memberId = memberServiceImpl.create(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("adresse"),request.getParameter("email"),request.getParameter("telephone"));
				member = memberServiceImpl.getById(memberId);
				lendingList = lendingServiceImpl.getListCurrentByMember(member.getId());

				request.setAttribute("memberJSP", member);
				request.setAttribute("lendingListJSP", lendingList);
				response.sendRedirect("membre_details?id=" + member.getId());
			}
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
			dispatcher.forward(request, response);
		}
	}
}