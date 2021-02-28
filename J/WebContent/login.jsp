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
		//sql injection attack!
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			Connection conn = null;
			//PreparedStatement stat = null;
			Statement stat = null;
			ResultSet rs = null;
			int count = 0;
			
			try{
		conn = MyDBUtils.getConnection();
		String sql = "select * from users where username = '"+ username +"' and password = '"+ password +"';";
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		
		if(rs.next()) {
			out.println("login success!");
		} else {
			out.println("login failed!");
		}
		
		
			} catch(Exception e) {
		e.printStackTrace();
			} finally {
		MyDBUtils.close(conn, stat, rs);
			}
	%>
</body>
</html>