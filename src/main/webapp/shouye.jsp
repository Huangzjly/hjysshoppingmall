<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="daohang.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="javax.sql.rowset.CachedRowSet"%>
<%@page import="javax.sql.rowset.RowSetProvider"%>

<html>
  <head>
  <link rel="stylesheet" type="text/css" href="style.css">
    <base href="<%=basePath%>">
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
 	 <jsp:useBean id="loginBean" class="hjy.entity.Login" scope="session"/>
 
 	 <% request.setCharacterEncoding("UTF-8"); %>
 		<ul class="user">
    			<li>
    				<%
    				    String str = null;
    				    str = loginBean.getUsername();
    				  
    					if(str.equals("userNull")||str==null)
    					{
    					   HttpSession s= request.getSession(true);
                           s.invalidate();
                        %>
	     					<a href="jsp/join/login.jsp">登录</a>or<a href="jsp/join/register.jsp">注册</a>
    					<%
    					   return;
    					}
    				 %>
   						<dl>
   							<dt>
	    						<a>欢迎您,<b><font color="red"><%= str %></font></b></a>
	    						<a href="<%= path %>/hjy.control/HandleExit"><font color="#CDC9C9">退出</font></a>
   							</dt>
   						</dl>
    			</li>
    			
    		</ul>
    		<figure>
    		<img src ="image/goods/001.jpg" width="500" height="400"alt="沃特篮球鞋"/>
    		
    		 <figcaption>沃特篮球鞋</figcaption>
    		</figure>
    		
    		<figure>
    		 <img src ="image/goods/002.jpg" width="500" height="400"alt="安踏运动鞋"/>
    		 
    		  <figcaption>安踏运动鞋</figcaption>
    		 </figure>
    		 
    		 <figure>
    		 <img src ="image/goods/003.jpg" width="500" height="400"alt="耐克运动鞋"/>
    		 
    		  <figcaption>耐克运动鞋</figcaption>
    		 </figure>
    		 <figure>
    		<img src ="image/goods/004.jpg" width="500" height="400"alt="阿迪达斯T血衫"/>
    		
    		 <figcaption>阿迪达斯T血衫</figcaption>
    		</figure>
    		<figure>
    		<img src ="image/goods/005.jpg" width="500" height="400"alt="李宁文化衫"/>
    		 <figcaption>李宁文化衫</figcaption>
    		</figure>
    		<figure>
    		<img src ="image/goods/006.jpg" width="500" height="400"alt="小米3"/>
    		 <figcaption>小米3</figcaption>
    		</figure>
    		<figure>
    		<img src ="image/goods/007.jpg" width="500" height="400"alt="小米2S"/>
    		 <figcaption>小米2S</figcaption>
    		</figure>
    		<figure>
    		<img src ="image/goods/008.jpg" width="500" height="400"alt="thinkpad笔记本"/>
    		 <figcaption>thinkpad笔记本</figcaption>
    		</figure>
    		<figure>
    		<img src ="image/goods/009.jpg" width="500" height="400"alt="dell笔记本"/>
    		 <figcaption>dell笔记本</figcaption>
    		</figure>
    		<figure>
    		<img src ="image/goods/010.jpg" width="500" height="400"alt="ipad5"/>
    		 <figcaption>ipad5</figcaption>
    		</figure>
    		
            
  </body>
</html>