<%@page import="java.sql.*"%>
<%@page import="cn.ybzy.jdbc.MyDBUtils"%>
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
		String username = request.getParameter("username");
			String password = request.getParameter("password");
			String phoneNo = request.getParameter("phoneNo");
			String address = request.getParameter("address");
			
			Connection conn = MyDBUtils.getConnection();
			
			String sql = "insert into users(username, password, phone_no, address, reg_date) values (?, ?, ?, ?, ?)";
			
			PreparedStatement stat = conn.prepareStatement(sql);
			
			stat.setObject(1, username);
			stat.setObject(2, password);
			stat.setObject(3, phoneNo);
			stat.setObject(4, address);
			stat.setObject(5, new java.sql.Date(new java.util.Date().getTime()));
			
			stat.executeUpdate();
			
			stat.close();
			conn.close();
	%>
</body>
</html>