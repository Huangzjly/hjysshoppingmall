<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>

<title>管理员导航条</title>
</head>
<style type="text/css">
  
  *{
	margin:0; 
	padding:0; 
	font-size:16px
 }
.nav{
	list-style:none; 
	height:50px;
	border-bottom:5px solid #F60;
	padding-left:30px;
  }
.nav li{
	float:left;
	margin-top:25px;
  }
.nav li a{
	background-color:#ccc;
	text-decoration:none;
	display:block;
	height:25px;
	line-height:25px;
	width:120px;
	margin-right:1px;
	text-align:center;
	background:url(/hjy1/image/page/nav.png);
 }
.nav li a.on, .nav li a:hover
	{
		color:#fff;
		background-position:0 -30px;
    }
    
.user{list-style:none;padding-right:18px; }
.user li{float:right;}
.user li a{
	text-decoration:none;
	color:#f60;
	height:15px;
	line-height:25px;
 }
  
  </style>
  
  <body>
   		<div>
  			<ul class="nav">
    			<li><a class="on" href="guanli.jsp">管理员首页</a></li>
    			
    			<li><a href="hjy.dao/Logs?key=1">客户浏览记录</a></li>
    			<li><a href="hjy.dao/Logs?key=2">客户购买记录</a></li>
    			
    		</ul>
   		</div>
   		
  </body>

</html>