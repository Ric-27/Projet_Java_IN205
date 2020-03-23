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

public class MembreListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/membre_list")) {
			MemberService memberServiceImpl = MemberServiceImpl.getInstance();
			List<Member> memberList = new ArrayList<>();
			
			try {                
                memberList = memberServiceImpl.getList();
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("memberListJSP", memberList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_list.jsp");
			dispatcher.forward(request, response);
		}
    }
}