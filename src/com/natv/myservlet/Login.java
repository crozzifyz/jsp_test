package com.natv.myservlet;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getMethod().equalsIgnoreCase("GET")) {
			response.sendRedirect("index.jsp");
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			String _email = request.getParameter("email");
			String _password = request.getParameter("password");

			HttpSession session = request.getSession();
			if (_email.contains("test@test.com")) {
				session.setAttribute("usr", _email);
				request.setAttribute("success", true);
				request.setAttribute("msg_err", "");
				response.sendRedirect("home.jsp");
			} else {
				request.setAttribute("success", false);
				session.setAttribute("msg_err", "login failed. please try again!");
				response.sendRedirect("index.jsp");
				
//				   request.setAttribute("msg_err", "Unknown username/password. Please retry."); // Store error message in request scope.
//			        request.getRequestDispatcher("/index.jsp").forward(request, response); // Forward to JSP page to redisplay login form with error.
			    
			}
		}
	}
}
