<%@page import="cn.ybzy.jdbc.MyDBUtils"%>
<%@page import="java.sql.*"%>
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
			
			Connection conn = null;
			Statement stat = null;
			int count = 0;
			try {
		conn = MyDBUtils.getConnection();
		String sql = "update users set username='" + username + "', password='" + password + "', phone_no='" + phoneNo + "', address='" + address + "' where id=3;";
		stat = conn.createStatement();
		count = stat.executeUpdate(sql);
			} catch(Exception e) {
		e.printStackTrace();
			} finally {
		try {
			if(stat != null) stat.close();
		} catch(Exception e) {
			e.printStackTrace();
		} 
		
		try {
			if(conn != null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}	
			}

			if (count > 0) {
		out.print("update success!");
			}
	%>
</body>
</html>