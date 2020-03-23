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

public class MembreDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/membre_delete")) {
            int id = -1;
            if (request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id"));

			MemberService memberServiceImpl = MemberServiceImpl.getInstance();
			Member member = new Member();
			
			try {
                member = memberServiceImpl.getById(Integer.parseInt(request.getParameter("id")));
                if (id > -1)
                    request.setAttribute("id", id);
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
			request.setAttribute("memberJSP", member);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
			dispatcher.forward(request, response);
		}
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService memberServiceImpl = MemberServiceImpl.getInstance();		
		Member member = new Member();
		List<Member> memberList = new ArrayList<>();
		
        try {
			if (request.getParameter("id") == "" || request.getParameter("id") == null) {
				throw new ServletException("Error. No Member Chosen");
			} else {
				memberServiceImpl.delete(Integer.parseInt(request.getParameter("id")));
			
				memberList = memberServiceImpl.getList();
				request.setAttribute("memberListJSP", memberList);
				
				response.sendRedirect("membre_list");
			}			
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			try {
				member = memberServiceImpl.getById(Integer.parseInt(request.getParameter("id")));
			} catch (ServiceException serviceException) {
				System.out.println(serviceException.getMessage());
				serviceException.printStackTrace();
			}
			request.setAttribute("memberJSP", member);
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
			dispatcher.forward(request, response);
		}
	}
}