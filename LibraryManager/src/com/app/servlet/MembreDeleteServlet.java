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
            // Set default value of the "id" option:
            int id = -1;
			// Change it while receiving another value:
            if (request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id"));
				
			// Get the list of all members:
			MemberService memberService = MemberServiceImpl.getInstance();
			List<Member> memberList = new ArrayList<>();
			
			try {
                memberList = memberService.getList();
                if (id > -1)
                    request.setAttribute("id", id);
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("memberList", memberList);

			// Submit gathered information th the appropriate .jsp:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
			dispatcher.forward(request, response);
		}
    }


}