package hjy.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import hjy.db.DbConn;
import hjy.db.DbClose;

import hjy.entity.Login;


public class HandleLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L; //设置序列号
	public HandleLogin()
	{
		super();
	}
	public void init() throws ServletException
	{
	}
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");//servlet中也要此项，否则取值乱码
		String Username = "";
		String Password = "";
		String cookies  = "";
		Username = request.getParameter("Username");
		Password = request.getParameter("Password");
		cookies = request.getParameter("isCookie");
		System.out.println(Username);
		handleCookies(request,response,Username,Password,cookies);//处理cookies信息
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DbConn.getConn();
		
		String sql = "SELECT * FROM `mydatabase`.`Users` WHERE `Username`=? AND `Password`=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Username);
			pstmt.setString(2, Password);
			rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				//登陆成功
				success(request,response,Username);
				request.getRequestDispatcher("/jsp/join/landing.jsp").forward(request, response);
			}else 
				{
					String backNews = "用户名或者密码错误";
					fail(request, response, backNews);
				}
		} catch (SQLException e)
		{
			String backNews = "登录失败"+e.getMessage();
			fail(request, response, backNews);
			e.printStackTrace();
		}finally
			{
				DbClose.allClose(pstmt, rs, conn);
			}
	}
	
	/**
	 * 处理用户cookies信息
	 * @param request
	 * @param response
	 * @param username
	 * @param userpass
	 */
	public void handleCookies(HttpServletRequest request, HttpServletResponse response, 
	        String name, String pass, String isCookie) throws ServletException, IOException {
	    if ("isCookie".equals(isCookie)) { // 用户选择了记住密码
	        // 检查name和pass是否为null，如果是null则不设置cookie
	        if (name != null) {
	            String Username = URLEncoder.encode(name, "UTF-8"); // 编码，解决cookie无法保存字符串的问题
	            Cookie nameCookie = new Cookie("username", Username); // 设置与登陆时的name对应的键值对
	            nameCookie.setPath("/"); // 设置的cookie的存储路径很重要，不然取不到值
	            nameCookie.setMaxAge(864000); // 设置生命期限十天 单位秒
	            response.addCookie(nameCookie); // 保存信息
	        }
	        if (pass != null) {
	            String Password = URLEncoder.encode(pass, "UTF-8");
	            Cookie passCookie = new Cookie("userpass", Password);
	            passCookie.setPath("/");
	            passCookie.setMaxAge(864000);
	            response.addCookie(passCookie);
	        }
	    } else {
	        // 用户未选择记住密码，删除浏览器中可能存在的cookie
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null && cookies.length > 0) {
	            for (Cookie c : cookies) {
	                if ("username".equals(c.getName()) || "userpass".equals(c.getName())) {
	                    c.setMaxAge(0); // 设置cookie失效
	                    c.setPath("/"); // 务必设置
	                    response.addCookie(c);
	                }
	            }
	        }
	    }
	}
	
	/**
	 * 登陆成功，储存用户信息
	 */
	public void success(HttpServletRequest request,
			HttpServletResponse response, String Username)
	{
		Login loginBean = null;
		HttpSession session = request.getSession(true);
		
		try
		{
			loginBean = (Login) session.getAttribute("loginBean");//获取session中可能存在的loginBean对象
			if (loginBean == null)
			{
				loginBean = new Login();
				session.setAttribute("loginBean", loginBean);//注意jsp获取时需要用到该name的属性名字
				session.setMaxInactiveInterval(600);//十分钟的存活期 单位：秒
				loginBean = (Login) session.getAttribute("loginBean");
			}
			
			String name = loginBean.getUsername();
			if (Username.equals(name))
			{
				loginBean.setBackNews(Username + "您已登陆，无需再次登录");
				loginBean.setUsername(Username);
			} else
				{
					loginBean.setBackNews(Username + "登陆成功");
					loginBean.setUsername(Username);
				}
			  // 记录登录信息到数据库
            recordLoginLog(Username);
            
		} catch (Exception e)
		{
			String backNews = "登录失败"+e;
			fail(request, response, backNews);
		}
	
	}
	
	
	/**
	 * 登陆失败
	 */
	public void fail(HttpServletRequest request,
			HttpServletResponse response,String backNews)
	{
		try
		{
			PrintWriter out = response.getWriter();
			out.print(backNews+"<br>");
			out.print("返回"+"<a href=/hjy1/jsp/join/login.jsp>登陆界面</a>");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}


private void recordLoginLog(String username) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = DbConn.getConn(); // 获取数据库连接

        String sql = "INSERT INTO CustomerBrowsingLogs (UserID, ProductID,BrowsingDate) VALUES (?, 1,NOW())";
        pstmt = conn.prepareStatement(sql);

       
        int userId = getUserIdByUsername(username);
        pstmt.setInt(1, userId);

        pstmt.executeUpdate(); // 执行插入操作

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        DbClose.allClose(pstmt, null, conn); // 关闭资源
    }
}

/**
 * 根据用户名获取用户ID
 */
private int getUserIdByUsername(String username) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int userId = 0;

    try {
        conn = DbConn.getConn(); // 获取数据库连接

        String sql = "SELECT UserID FROM Users WHERE Username = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);

        rs = pstmt.executeQuery();

        if (rs.next()) {
            userId = rs.getInt("UserID");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        // 处理异常，可能需要记录日志或抛出自定义异常
    } finally {
        DbClose.allClose(pstmt, rs, conn); // 关闭资源
    }

    return userId;
}
}