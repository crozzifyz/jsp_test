package com.natv.myservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditProduct
 */
@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String _nameedit = request.getParameter("_nameedit");
		String _name = request.getParameter("_name");
		String _qty = request.getParameter("_qty");
		String _price = request.getParameter("_price");
		
		Connection connect = null;
		Statement s = null;

		try {

			connect = ConnectDB.connectDb();
//			Class.forName("com.mysql.jdbc.Driver");
//			connect = DriverManager.getConnection("jdbc:mysql://localhost/test" + "?user=root&password=");

			s = connect.createStatement();
			String sql = "UPDATE product set name='"+_name+"', qty='"+_qty+"', price='"+_price+"' WHERE name ='"+_nameedit+"'";
			
			s.execute(sql);
			ResultSet rs = s.getResultSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			out.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			if (s != null) {
				s.close();
				connect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			out.println(e.getMessage());
			e.printStackTrace();
		}
		response.sendRedirect("home.jsp");
	}

}
