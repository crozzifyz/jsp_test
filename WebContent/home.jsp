<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<%
			if (session.getAttribute("usr") == null) {
				response.sendRedirect("index.jsp");
			}
			out.print("<h1> Welcome : " + session.getAttribute("usr") + "</h1>");
		%>
		<form action="Logout" method="POST">
			<input class="btn btn-danger" type="submit" value="Logout"
				style="position: absolute; top: 24px; right: 20px;">
		</form>


		<ul class="nav nav-tabs">
			<li class="active"><a href="#allProduct">Product</a></li>
			<li><a href="#addProduct">Add Product</a></li>
			<li><a href="#editProduct">Edit Product</a></li>
			<li><a href="#deleteProduct">Delete Product</a></li>
		</ul>

		<div class="tab-content">
			<div id="allProduct" class="tab-pane fade in active">
				<h3>Product</h3>
				<%
					Connection connect = null;
					Statement s = null;

					try {

						Class.forName("com.mysql.jdbc.Driver");
						connect = DriverManager.getConnection("jdbc:mysql://localhost/test" + "?user=root&password=");

						s = connect.createStatement();
						String sql = "Select * from product";
						s.execute(sql);
						ResultSet rs = s.getResultSet();

						out.println("<table class='table'>");
						out.println("<thead><tr><th>Id</th><th>Name</th><th>QTY</th><th>Price</th></tr></thead><tbody>");
						while (rs.next()) {
							String _id = rs.getString("id");
							String _name = rs.getString("name");
							String _qty = rs.getString("qty");
							String _price = rs.getString("price");

							out.println("<tr>");
							out.println("<td>" + _id + "</td>");
							out.println("<td>" + _name + "</td>");
							out.println("<td>" + _qty + "</td>");
							out.println("<td>" + _price + "</td>");
							out.println("</tr>");
						}
						out.println("</tbody></table>");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						out.println(e.getMessage());
						e.printStackTrace();
					}

					try {
						if (s != null) {
							s.close();
							connect.close();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						out.println(e.getMessage());
						e.printStackTrace();
					}
				%>
			</div>
			<div id="addProduct" class="tab-pane fade">
				<h3>Add Product</h3>
				<form action="AddProduct" method="POST" class="form-signin">
					<label for="inputEmail" class="sr-only">Product name</label> <input
						type="text" id="inputName" name="_name" class="form-control"
						placeholder="Product name" required="" autofocus=""> <label
						for="inputQty" class="sr-only">Qty</label> <input type="number"
						id="inputQty" name="_qty" class="form-control" placeholder="Qty"
						required=""> <label for="inputPrice" class="sr-only">Price</label>
					<input type="decimal" id="inputPrice" name="_price"
						class="form-control" placeholder="Price" required="">

					<button class="btn btn-lg btn-success btn-block" type="submit">Submit</button>
				</form>
			</div>
			<div id="editProduct" class="tab-pane fade">
				<h3>Edit Product</h3>
				<form action="EditProduct" method="POST" class="form-signin">
				<label for="inputNameSearch" class="sr-only">Search Product name</label> <input
						type="text" id="inputNameSearch" name="_nameedit" class="form-control"
						placeholder="Search Product name" required="" autofocus=""> 
					<label for="inputName" class="sr-only">Change product name to</label> <input
						type="text" id="inputName" name="_name" class="form-control"
						placeholder="Change product name to" required="" autofocus=""> <label
						for="inputQty" class="sr-only">Qty</label> <input type="number"
						id="inputQty" name="_qty" class="form-control" placeholder="Qty"
						required=""> <label for="inputPrice" class="sr-only">Price</label>
					<input type="decimal" id="inputPrice" name="_price"
						class="form-control" placeholder="Price" required="">

					<button class="btn btn-lg btn-success btn-block" type="submit">Submit</button>
				</form>
			</div>
			<div id="deleteProduct" class="tab-pane fade">
				<h3>Delete Product</h3>
				<form action="DeleteProduct" method="POST" class="form-signin">
					<label for="inputName" class="sr-only">Product name</label> <input
						type="text" id="inputName" name="_name" class="form-control"
						placeholder="Product name" required="" autofocus="">

					<button class="btn btn-lg btn-success btn-block" type="submit">Submit</button>
				</form>
			</div>
		</div>
	</div>


	<script>
		$(document).ready(function() {
			$(".nav-tabs a").click(function() {
				$(this).tab('show');
			});
		});
	</script>
</body>
</html>