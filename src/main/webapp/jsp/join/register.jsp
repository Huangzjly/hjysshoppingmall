<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/daohang.jsp" %>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
</head>
<body>
		<jsp:useBean id="userBean" class="hjy.entity.Register" scope="request"/>
		<% request.setCharacterEncoding("UTF-8"); %>
		<div align="center">
			<form action="<%= path %>/hjy.control/HandleRegister" method="post">
				<table border="1" cellpadding="10" cellspacing="1">
					<tr>
						<td>用户姓名:<input name="Username" placeholder="*必填"/></td>
						<td>用户密码：<input type="Password" name="Password" placeholder="*必填(6-16字符之间)"/></td>
					</tr>
					<tr>
						<td>邮箱：<input type="Email" name="Email" placeholder="必填"/></td>
					</tr>
						<tr>
							<td>
								状态:<FONT color=red><jsp:getProperty name="userBean" property="backNews"/></FONT>
							</td>
							<td>
								<input type="image" src="<%= path %>/image/page/submit.png" alt="submit" height="40" width="100"/>
							</td>
						</tr>
				</table>
			</form>
		</div>
</body>
</html>