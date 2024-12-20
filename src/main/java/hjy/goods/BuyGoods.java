package hjy.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

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

public class BuyGoods extends HttpServlet
{
    
    
    private static final long serialVersionUID = 1132L;

   
    public BuyGoods()
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
        
        //从模型中直接拿购物车信息
        HttpSession session = request.getSession(true);
        Login loginBean = (Login)session.getAttribute("loginBean");
        String userName = "myNull";
        userName = loginBean.getUsername();
        LinkedList<String> car = null;
        car = loginBean.getCar();
        
      //购物车是否为空，向数据库中删除与插入数据
        if (car.size()!=0)
        {
            boolean falg = false;
            Connection        conn  = null;
            PreparedStatement pstmtCommodity = null;
            PreparedStatement pstmtOrder = null;
            
            //将商品行集数据遍历到数组中
            for (int i = 0,m=car.size(); i < m; i++)
            {
                    String[] goods = null;
                    conn = DbConn.getConn();
                    goods = car.get(i).split(",");
                    
                    String sqlCommodity = null;
                    String sqlOrder = null;
                    sqlCommodity = "UPDATE Products SET InventoryCount = ? WHERE ProductID = ?";
                    sqlOrder = "INSERT INTO Orders (UserID, Status, TotalAmount) VALUES (?, ?, ?)";
    
                        try
                        {
                            pstmtCommodity = conn.prepareStatement(sqlCommodity);
                            pstmtOrder = conn.prepareStatement(sqlOrder);
                            
                            pstmtOrder.setString(1,userName);
                            pstmtOrder.setInt(4,1);
                            for (int j = 0,n=goods.length; j < n; j++)
                            {
                                switch (j)
                                {
                                    case 0:
                                            String commodity_number = null;
                                            commodity_number = goods[0];
                                            pstmtCommodity.setString(2,commodity_number);
                                        break;
                                    case 1:
                                            String commodity_name= null;
                                            commodity_name = goods[1];
                                            pstmtOrder.setString(2,commodity_name); 
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                            Double commodity_price = 0.00;
                                            commodity_price = Double.parseDouble(goods[3]);
                                            pstmtOrder.setDouble(3,commodity_price); 
                                        break;
                                    case 4:
                                            int commodity_balance = -1;
                                            System.out.println(Integer.parseInt(goods[4]));
                                            commodity_balance = Integer.parseInt(goods[4])-1;//目前是默认每次修改一个
                                            System.out.println(commodity_balance);
                                            if (commodity_balance >= 0)
                                            {
                                                pstmtCommodity.setInt(1,commodity_balance);
                                            }else 
                                                {
                                                    String failNumber = "数据库中商品不足";
                                                    messShopping(request,response,failNumber);
                                                }
                                        break;
                                    default:
                                        System.out.println("defalut01");
                                        break;
                                }
                                System.out.println("defalut02");
                            }
                                
                            
                                int rsCommodity = pstmtCommodity.executeUpdate();
                                int rsOrder = pstmtOrder.executeUpdate();
                                if (!(rsCommodity > 0 && rsOrder > 0))//插入失败
                                {
                                    String failError = "与数据库对接时出现异常";
                                    messShopping(request,response,failError);
                                }else 
                                    {
                                    System.out.println(i+"此次循环商品购买成功");
                                        falg = true;//此次循环商品购买成功
                                    }
                                
                        } catch (SQLException e)
                        {
        //                  此地需要回滚数据！！！未实现
                            /*String backNews = "购物失败"+"<br>"+e;
                            loginBean.setBackNews(backNews);//直接用hjy.entity/Login.java 类
*/                           System.out.println("莫名异常:"+e);
                           
                            PrintWriter out = response.getWriter();
                            out.print(e+"<br>");
                            out.print("返回"+"");
                            out.print("<a href=/hjy/jsp/shoppingCar/lookShoppingCar.jsp>购物车</a>");
                            return;
                        }finally
                        {
                            DbClose.close(pstmtOrder,pstmtCommodity, conn);
                        }
                        
                continue;
             }
                    
                if (falg==true)
                {
                	//付款成功，清除数据模型中的数据
                    car.clear();
                    /*//从行集中更新数据库信息
                     request.getRequestDispatcher("/hjy.dao/GoodsDao?key=3").forward(request, response);*/
                    
                    updateInfo(request,response);
                    
                    String successBackNews = "您已将购物车中的商品买回家了";
                    messShopping(request,response,successBackNews);
                }
            }
        return;
     
    }
    
    /**
     * 
     * 从数据库中更新行集对象
     * <功能详细描述>
     * @param request
     * @param response
     * @throws IOException 
     */
    private void updateInfo(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        CachedRowSet rowSet = null;//行集对象
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Goods goods = null;
        
        HttpSession session = request.getSession(true);
        goods = (Goods)session.getAttribute("goods");
//      ArrayList<Goods> goodsList = new ArrayList<Goods>();
        if (goods==null)
        {
            goods = new Goods();
            session.setAttribute("goods", goods);
        }
        
        conn = DbConn.getConn();
        
        String sqlListClear= "select * from commodity";
        try
        {
            pstmt = conn.prepareStatement(sqlListClear);
            rs = pstmt.executeQuery();
            System.out.println("执行数据库操作");
            while (rs.next())
            {
                rowSet = RowSetProvider.newFactory().createCachedRowSet();
                rowSet.populate(rs);
                goods.setRowSet(rowSet);
                System.out.println("已经从数据库中获取到值，并塞进行集");
            }
        } catch (SQLException e)
        {
            System.out.println("GoodsDao.java k=3 再次查询时出现异常："+e);
            PrintWriter out = response.getWriter();
            out.print(e+"<br>");
            out.print("返回"+"");
            out.print("<a href=/hjy/jsp/shoppingCar/lookShoppingCar.jsp>购物车</a>");
        }finally
                {
                    DbClose.allClose(pstmt, rs, conn);
                }
        
    }

    /**
     * 
     * 商品购买处理信息
     * <功能详细描述>
     * @param request
     * @param response
     * @param failNumber
     */
    public void messShopping(HttpServletRequest request, HttpServletResponse response, String mess)
    {
        try
        {
            PrintWriter out = response.getWriter();
            out.print("<br><br><br>");
            out.print("<center><font size=5 color=red><B>"+mess+"</B></font>&nbsp;");
            out.print("<br><br><br>");
            out.print("<a href=/hjy/jsp/browse/showGoods.jsp>返回继续购物</a>");
            out.print("&nbsp;or&nbsp;");
            out.print("<a href=/hjy/hjy.dao/GoodsDao?key=3>查看订单</a></center>");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }

    public void init()
        throws ServletException
    {
    }
    
}