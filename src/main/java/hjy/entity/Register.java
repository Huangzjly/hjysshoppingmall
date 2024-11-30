package hjy.entity;

import java.io.Serializable;

/**
 *实现了 Serializable 接口，表示这个类的对象可以被序列化。
 *这个类用于封装用户注册时提交的信息。
 */
public class Register implements Serializable
{
	private static final long serialVersionUID = -1465928336863533909L;
	//序列化版本号
	private String username;
	private String userpass;
	private String phone;
	private String address;
	private String realname;
	private String backNews = "";

	public Register()
	{
		super();
		//调用父类（Object）的无参构造方法。
	}
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUserpass()
	{
		return userpass;
	}
	public void setUserpass(String userpass)
	{
		this.userpass = userpass;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getRealname()
	{
		return realname;
	}
	public void setRealname(String realname)
	{
		this.realname = realname;
	}

	public String getBackNews()
	{
		return backNews;
	}

	public void setBackNews(String backNews)
	{
		this.backNews = backNews;
	}
	
	
}