package hjy.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import hjy.db.DbClose;
import hjy.db.DbConn;
import hjy.entity.Goods;
import hjy.entity.Login;


public class Logs extends HttpServlet
{
	
    private static final long serialVersionUID = 135785434567L;
 
	
	public Logs()
	{
		super();
		System.out.println("正在构造 Logs 对象...");
	}


	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;chartset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String value = "";
		value = request.getParameter("key");
		int key = Integer.parseInt(value);
		System.out.println("检测是否有key:"+key);
		
		String keyWord = "";
		keyWord = request.getParameter("keyWord");
		System.out.println(keyWord);
		queryGoods(request, response, key,keyWord);
	}

	public void init() throws ServletException
	{
		// Put your code here
	}
	

	public void queryGoods(HttpServletRequest request, HttpServletResponse response,int key,String keyWord)
			throws ServletException, IOException
	{
	    response.setContentType("text/html;charset=UTF-8");//HTTP响应的内容类型为HTML
        PrintWriter out = response.getWriter();//获取PrintWriter对象用于向客户端发送字符文本响应。
       
        /*初始化了一些数据库操作需要的对象，
         * 包括CachedRowSet（用于存储查询结果的行集），
         * Connection（数据库连接），
         * PreparedStatement（预编译SQL语句）
         * ，ResultSet（查询结果集），
         * 以及Logs和Login实体对象。
         * 
         */
        CachedRowSet rowSet = null;//行集对象
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		Goods goods = null;
		Login username = null;
//		OrderForm orderForm = null;
		
		//从HTTP会话中获取用户登录信息和商品信息。
		HttpSession session = request.getSession(true);
		username = (Login)session.getAttribute("loginBean");
//		goods = (Goods)session.getAttribute("goods");
//		orderForm = (OrderForm)session.getAttribute("orderForm");
//		ArrayList<Goods> goodsList = new ArrayList<Goods>();
//		if (goods==null)
//		{
//			goods = new Goods();
//			session.setAttribute("goods", goods);
//		}
	if (username==null)
	{
		    username = new Login();
		    session.setAttribute("username", username);
		}
//		if (orderForm==null)
//		{
//		    orderForm = new OrderForm();
//		    session.setAttribute("orderForm", orderForm);
//		}
		  //判断管理员是否登陆
		  String user = "";
          user = username.getUsername();//登陆者的用户名
          System.out.println("我是管理员："+user);
          if (user.equals("userNull"))
          {
              out.print("<br>");
              out.print("<center><font color=#008B8B> 登陆之后才能看哦  </font>");
              out.print("<a href=/hjy1/jsp/join/guanlilogin.jsp><font color=red size=6>登陆</font></a></center>");
              return;
          }
		
		conn = DbConn.getConn();	

		switch (key)
		{
			case 1:
						
					String sqlGnum = "SELECT * FROM customerbrowsinglogs";
					try
					{
						pstmt = conn.prepareStatement(sqlGnum);
						rs = pstmt.executeQuery();
						while (rs.next())
						{
							
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}finally
							{
								DbClose.allClose(pstmt, rs, conn);
							}
					
				break;
			case 2:
				
				String sqlGnum2 = "SELECT * FROM customerpurchaselogs";
				try
				{
					pstmt = conn.prepareStatement(sqlGnum2);
					rs = pstmt.executeQuery();
					while (rs.next())
					{
						
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}finally
						{
							DbClose.allClose(pstmt, rs, conn);
						}
				
			break;
			
		}
	}

}