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
			MemberService memberServiceImpl = MemberServiceImpl.getInstance();
			Member member = new Member();
			LendingService lendingServiceImpl = LendingServiceImpl.getInstance();
			List<Lending> lendingList = new ArrayList<>();
            int id = -1;
            if (request.getParameter("id") != null){
				id = Integer.parseInt(request.getParameter("id"));
			}
			try {
                if (id > -1) {
                    member = memberServiceImpl.getById(Integer.parseInt(request.getParameter("id")));
					lendingList = lendingServiceImpl.getListCurrentByMember(id);

					request.setAttribute("memberJSP", member);
                    request.setAttribute("lendingListJSP", lendingList);
                }
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
            }

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
			dispatcher.forward(request, response);
		}
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService memberServiceImpl = MemberServiceImpl.getInstance();
		Member member = new Member();
		LendingService lendingServiceImpl = LendingServiceImpl.getInstance();
		List<Lending> lendingList = new ArrayList<>();

		try {
			memberServiceImpl.update(new Member(Integer.parseInt(request.getParameter("id")),request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("adresse"),request.getParameter("email"),request.getParameter("telephone"),Member.Subscription.valueOf(request.getParameter("abonnement"))));
			
			member = memberServiceImpl.getById(Integer.parseInt(request.getParameter("id")));			
			lendingList = lendingServiceImpl.getListCurrentByMember(Integer.parseInt(request.getParameter("id")));
			
			request.setAttribute("memberJSP", member);
			request.setAttribute("lendingListJSP", lendingList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			try {
				member = memberServiceImpl.getById(Integer.parseInt(request.getParameter("id")));
				lendingList = lendingServiceImpl.getListCurrentByMember(Integer.parseInt(request.getParameter("id")));

				request.setAttribute("memberJSP", member);
				request.setAttribute("lendingListJSP", lendingList);

			} catch (ServiceException ee) {
				System.out.println(ee.getMessage());
				ee.printStackTrace();
			}
			request.setAttribute("errorMessage", e.getMessage());

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}