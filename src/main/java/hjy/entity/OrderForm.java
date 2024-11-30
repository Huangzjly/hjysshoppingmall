package hjy.entity;

import java.util.Date;

/**
 * 
 * 
 */
public class OrderForm
{
    private int id = 0;//订单的唯一标识符，初始值为 0
    private String username = "";//下订单的用户名，初始为空字符串
    private Date orderDate = null;//订单的日期，初始值为 null
    private String commodity_name = "";//商品的名称，初始为空字符串
    private double commodity_price = 0.00;//商品的价格，初始值为 0.00。
    private int sum = 0;                    //订单的总数，初始值为 0
    
    public OrderForm(){}
    
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public Date getOrderDate()
    {
        return orderDate;
    }
    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }
    public String getCommodity_name()
    {
        return commodity_name;
    }
    public void setCommodity_name(String commodity_name)
    {
        this.commodity_name = commodity_name;
    }
    public double getCommodity_price()
    {
        return commodity_price;
    }
    public void setCommodity_price(double commodity_price)
    {
        this.commodity_price = commodity_price;
    }
    public int getSum()
    {
        return sum;
    }
    public void setSum(int sum)
    {
        this.sum = sum;
    }
 
    
}