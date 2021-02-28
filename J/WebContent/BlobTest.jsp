<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="cn.ybzy.dao.BaseDao" %>
<%@page import="java.sql.Blob" %>
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
	/*String sql = "insert into users(username, password, phone_no, address, reg_date, pic_head) values (?, ?, ?, ?, ?, ?)";
	out.println(request.getContextPath() + "/Resources/lss.jpg");
	InputStream in = new FileInputStream(request.getSession().getServletContext().getRealPath("/") + "Resources/lss.jpg"); */
	
	
//  InputStream in = new FileInputStream("C:/Users/fred0/Downloads/lss.jpg");
//	baseDao.IUD(sql, "Mr Fred", "2222", "3333", "Mars", "2020-2-26", in);	 
	
	String sql = "select pic_head from users where id = ?";
	Blob blob = baseDao.getOneColumnBlob(sql, 10);
	InputStream in = blob.getBinaryStream();
	OutputStream outpt = new FileOutputStream(request.getSession().getServletContext().getRealPath("/") + "Resources/abc.jpg");
	byte[] buffer = new byte[1024];
	int len = 0;
	len = in.read(buffer);
	
	while ((len = in.read(buffer)) != -1) {
		out.println(len);
		outpt.write(buffer, 0, len);
	}
	
	in.close();
	out.close();
	%>
</body>
</html>