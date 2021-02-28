<%@page import="cn.ybzy.jdbc.MyDBUtils"%>
<%@page import="cn.ybzy.model.User" %>
<%@page import="java.sql.*"%>
<%@page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		User u = new User();
		
			u.setUserName(request.getParameter("username"));
			u.setPassword(request.getParameter("password"));
			u.setPhoneNo(request.getParameter("phoneNo"));
			u.setAddress(request.getParameter("address"));
			u.setRegDate(new Date());
			
			
			/*String sql = "INSERT INTO users (username, password, phone_no, address, "
			+ "reg_date) VALUES ('"+ u.getUserName() +"', '"+ u.getPassword() +"', '"+ u.getPhoneNo() +"', '"+ u.getAddress() 
			+"', '"+ new java.sql.Date(u.getRegDate().getTime()) +"');";*/
			
			String sql = "INSERT INTO users (username, password, phone_no, address, reg_date) VALUES (?, ?, ?, ?, ?)";
			
			int count = MyDBUtils.IUD(sql, u.getUserName(), u.getPassword(), u.getPhoneNo(), u.getAddress(), new java.sql.Date(new java.util.Date().getTime()));
			
			if (count > 0) {
		out.print("insert success!");
			}
	%>
</body>
</html>