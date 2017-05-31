package com.natv.myservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RequestDispatcher dispatcher;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getMethod().equalsIgnoreCase("GET")) {
			response.getWriter().append("Served at: ").append(request.getContextPath());
		} else if (request.getMethod().equalsIgnoreCase("POST")) {

			dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			String _email = request.getParameter("email");
			String _password = request.getParameter("password");

			if (_email.contains("test@test.com")) {
				HttpSession session = request.getSession();
				session.setAttribute("usr", _email);
				request.setAttribute("success", true);
				request.setAttribute("msg_err", false);
				// dispatcher.forward( request, response );
				response.sendRedirect("home.jsp");
			} else {
				request.setAttribute("success", false);
				request.setAttribute("msg_err", true);
				response.sendRedirect("index.jsp");
			}

		}
	}
}
