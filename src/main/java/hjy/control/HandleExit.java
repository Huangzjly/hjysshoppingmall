package hjy.control;

//处理 HTTP 请求和响应时需要用到的类

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//处理请求和响应时抛出
import java.io.IOException;




public class HandleExit extends HttpServlet 
{

    private static final long serialVersionUID = 1L;//序列化版本号
	
	public HandleExit()
	{
		super();//无参构造方法
	}

	public void destroy()
	{
		super.destroy(); 
		//Servlet 被销毁前执行清理工作。
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			//处理 POST 请求
	{

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);//true一定要写。否则，获取不到当前session时会自动创建一个
		session.invalidate();
		response.sendRedirect("/jsp/join/login.jsp");//重定向到登录页面，提示用户已经成功退出登录。
		
	}

	public void init() throws ServletException
	{
		
	}

}