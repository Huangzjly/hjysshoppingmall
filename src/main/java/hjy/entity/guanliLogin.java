package hjy.entity;

import java.io.Serializable;
import java.util.LinkedList;
/**
 * 名为 guanliLogin，它实现了 Serializable 接口，
 * 这意味着 guanliLogin 类的对象可以被序列化，
 * 即可以被转换成字节流，
 * 从而可以在网络上传输或者保存到文件中。
 * 
 */


public class guanliLogin implements Serializable
{
	private static final long serialVersionUID = -69203680249861342L;     //序列化版本号
	private String username = "";        //用户名，初始为空字符串。
	private String backNews = "未登录";      //返回消息，初始值为 "未登录"
	private LinkedList<String> car = null;     //购物车，订单
	
	
	public guanliLogin()
	{
		car = new LinkedList<String>();
		//在构造函数中，初始化 car 为一个新的 LinkedList<String> 对象。

	}
	
	
	public String getUsername()
	{
	    if (username.trim()=="")
        {
            return "userNull";
        }
		return username;
		
		//返回用户名 username。如果用户名是空字符串，返回 "userNull"
	}
	public void setUsername(String username)
	{
		this.username = username;
		//设置用户名 username
	}
	public String getBackNews()
	{
		return backNews;
		//返回返回消息 backNews
	}
	public void setBackNews(String backNews)
	{
		this.backNews = backNews;
		//设置返回消息 backNews
	}

}