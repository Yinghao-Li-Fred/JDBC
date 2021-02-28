<%@page import="java.sql.*"%>
<%@page import="cn.ybzy.jdbc.MyDBUtils"%>
<%@page import="cn.ybzy.model.User"%>
<%@page import="cn.ybzy.model.Student" %>
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
			
			String sql = "select id id, username userName, password password from users where id=?";

			User user = MyDBUtils.getOneData(User.class, sql, userid);

			if (user != null) {
		out.println(user);
			} else {
		out.println("No such user!");
			} 
			
		/*  String sql = "select id id, sname sname, age_id ageId from students where id=1";
			Student st = DBUtils.getOneData(Student.class, sql);
			
			out.println(st); */
	%>
</body>
</html>