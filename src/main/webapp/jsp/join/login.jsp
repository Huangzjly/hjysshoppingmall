<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/daohang.jsp" %>
<%@ page import="java.net.URLDecoder"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<% 
		/* 从本地磁盘中获取已保存的cookie */
		request.setCharacterEncoding("UTF-8");
		String Username = "";
		String Password = "";
		Cookie[] cookies = null;
		cookies = request.getCookies();
		if(cookies!=null && cookies.length>0)
		{
			for(Cookie c:cookies)
			{
				if("Username".equals(c.getName()))
				{
					Username = URLDecoder.decode(c.getValue(),"UTF-8");//解码并取值、赋值
				}
				if("Password".equals(c.getName()))
				{
					Password = URLDecoder.decode(c.getValue(),"UTF-8");
				}
			}
		}
	%>
	<div align="center">
		<form action="<%= path %>/hjy.control/HandleLogin" method="post">
		
			<table border="0" cellpadding="15" cellspacing="1">
				<tr>
					<td colspan="2">
						<input name="Username" value="<%= Username %>" placeholder="Username"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="Password" name="Password" value="<%=Password %>" placeholder="Password"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="isCookie" value="isCookie" checked="checked">十天内保存登录状态
					</td>
					
					<td>
						<input type="submit" value="登陆"/>
					</td>
				</tr>
			</table>
			
		</form>
	</div>
</body>
</html>