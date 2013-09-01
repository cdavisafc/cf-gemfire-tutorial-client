<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Gemfire Tutorial</h1>

<form action="MainClientServlet" method="post">
Add person: <input type="text" name="person"/> <input type="submit" value="Add" />
</form>
<h3>People:</h3>
${people}
<hr>
<form action="MainClientServlet" method="post">
Add post: <input type="text" name="post"/> <input type="submit" value="Add" />
</form>
<h3>Posts:</h3>
${posts}

</body>
</html>