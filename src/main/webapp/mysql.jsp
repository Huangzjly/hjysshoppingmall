<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mysql</title>
</head>
<body>
<%
	try{
		Class.forName("com.mysql.jdbc.Driver");  			//显示声明将数据库驱动程序注册到jdbc的驱动管理器中
		String url = "jdbc:mysql://localhost:3306/mydatabase";		//数据库名称为mydatabase（需要提前在MySQL里面建立mydatabase数据库）
		String username = "root";                                          //数据库用户名
		String password = "123456";							//数据库密码	
		Connection conn = DriverManager.getConnection(url,username,password);    //连接数据库
		out.print("数据库连接成功！");
		conn.close();
	}
	catch(Exception e){
		out.print("数据库连接失败！");
		out.print("错误信息："+e.toString());
	}
%>
</body>
</html>
