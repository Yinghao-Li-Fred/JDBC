<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="cn.ybzy.dao.BaseDao" %>
<%@page import="cn.ybzy.model.User" %>
<%@page import="cn.ybzy.jdbc.MyDBUtils" %>
<%@page import="java.sql.Connection" %>
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
		BaseDao baseDao = new BaseDao();
			
			//String sql = "insert into users set username = ?, password = ?, phone_no = ?, address = ?, reg_date = ?";
			// baseDao.IUD(sql, "Fred2", "123456", "909090", "America", "2019-4-8");
			
			/* 		String sql="update users set username=? where id=?";
			baseDao.IUD(sql, "Fredddddddd", 9); */
			
		/* 	String sql = "delete from users where id=?";
			baseDao.IUD(sql, 9); */
			
			/* 		String sql = "select id id, username userName, password password, phone_no phoneNo, address address, reg_date regDate from users where id = ?";
			User user = baseDao.getOneData(User.class, sql, 8);
			out.print(user); */
			
			/* 		String sql = "select id id, username userName, password password, phone_no phoneNo, address address, reg_date regDate from users";
			List<User> list = baseDao.getListData(User.class, sql);
			out.println(Arrays.toString(list.toArray())); */
			
			/* 		String sql = "select count(*) from users";
			long count = (long) baseDao.getOneColumn(sql);
			out.println(count); */
			
			String sql="update users set username=? where id=?";
			Connection conn = MyDBUtils.getConnection();
			conn.setAutoCommit(false);
			baseDao.IUD(conn, sql, "Freddddddddddd", 9);
			conn.commit();
	%>
</body>
</html>