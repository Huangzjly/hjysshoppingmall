package hjy.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hjy.db.DbClose;
import hjy.db.DbConn;
import hjy.entity.Register;

public class HandleRegister extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public HandleRegister()
	{
		super();
	}

	
	public void destroy()
	{
		super.destroy(); 
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
		request.setCharacterEncoding("UTF-8");
		
		Register userBean = new Register();
		request.setAttribute("userBean", userBean);
		
		String Username = "";
		String Password = "";
		
		String Email = "";
		
		Username = request.getParameter("Username").trim();
		Password = request.getParameter("Password").trim();
		
		
		Email = request.getParameter("Email").trim();
		
		if (Username==null)
		{
			Username = "";
		}
		if (Password==""|Password==null)
		{
			Password = "error";
		}
		

						String backNews = ""; 

						boolean userFlag = Password.length()>5;
						if (userFlag)
						{
							Connection        conn  = null;
							PreparedStatement pstmt = null;
							
							conn = DbConn.getConn();
							String sql = "INSERT INTO users(Username,Password,Email) VALUES(?,?,?)";
							
							try
							{
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1,Username);
								pstmt.setString(2,Password); 
								pstmt.setString(3,Email);
								
								
								int rs = pstmt.executeUpdate();
								if (rs > 0)
								{
									backNews = "注册成功";
									userBean.setBackNews(backNews);
									request.getRequestDispatcher("/jsp/join/registerSuccess.jsp").forward(request, response);
								}
							} catch (SQLException e)
							{
							    System.out.println(e);
								backNews = "该用户名已被注册"+"<br>";
								userBean.setBackNews(backNews);
								request.getRequestDispatcher("/jsp/join/register.jsp").forward(request, response);
							}finally
							{
								DbClose.close(pstmt, conn);
							}
						}else 
						{
							userBean.setBackNews("密码不合法");
							request.getRequestDispatcher("/jsp/join/register.jsp").forward(request, response);
						}
					}
	

	public void init() throws ServletException
	{
	}

}