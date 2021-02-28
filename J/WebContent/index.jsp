<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="insertUser2.jsp" method="GET" style="margin:0 auto; width:500px; padding:20px; border:1px #ccc solid; text-align:center;" >
		username:<input type="text" name="username"><br><br>
		password:<input type="text" name="password"><br><br>
		phone no:<input type="text" name="phoneNo"><br><br>
		address: <input type="text" name="address"><br><br>
		<input type="submit">
	</form>
	
	<br><br><br><br><br><br><br>
	
	<form action="getOneUser.jsp" method="POST" style="margin:0 auto; width:500px; padding:20px; border:1px #ccc solid; text-align:center;" >
		userId: <input type="text" name="userid"><br><br>
		<input type="submit">
	</form>
	
	<br><br><br><br><br><br><br>
	
	<form action="login.jsp" method="POST" style="margin:0 auto; width:500px; padding:20px; border:1px #ccc solid; text-align:center;" >
		username:<input type="text" name="username"><br><br>
		password:<input type="text" name="password"><br><br>
		<input type="submit">
	</form>
	
	
</body>
</html>