<%@page import="java.sql.*" %>
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
		String userid = request.getParameter("userid");
			
			Connection conn = null;
			Statement stat = null;
			ResultSet rs = null;
			
			try {
		conn = MyDBUtils.getConnection();
		String sql = "select id, username, password, phone_no, address, reg_date from users";
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String phoneNo = rs.getString("phone_no");
			String address = rs.getString("address");
			String regDate = rs.getDate("reg_Date").toString();
			
			out.println(id + "---" + username + "---" + password + "---" + phoneNo + "---" + address + "---" + regDate);
			out.print("<br><br>");
		} 
			} catch(Exception e) {
		
			} finally {
		MyDBUtils.close(conn, stat, rs);
			}
	%>
</body>
</html>