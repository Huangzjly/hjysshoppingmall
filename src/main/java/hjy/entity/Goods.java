package hjy.entity;

import javax.sql.rowset.*; 

/** CachedRowSet 接口和相关的实现类，用于在内存中缓存数据库查询结果，
*以及在不同的数据源之间传输数据。
*/

/**
 * 表示商品信息，并且包含了分页功能,
 * 将数据库查询结果与分页信息封装在一起。
 */

public class Goods 
{
	
	CachedRowSet rowSet = null; 
	//CachedRowSet 对象，
	//用于存储从数据库查询得到的数据行。初始值为 null
	private int pageSize = 5;	
	//定义每页显示的记录数，默认为 5
	private int currentPage = 1;
	//定义当前页码，默认为 1
	private int totalRecord = 1;
	//定义总记录数，默认为 1
	private int totalPage = 1;	 
	//定义总页数，默认为 1
	
	public Goods(){}//无参构造方法
	
	public Goods(CachedRowSet rowSet, int pageSize, int currentPage,
			int totalPRecord, int totalPage)
	//带参数的构造方法
	{
		this.rowSet = rowSet;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalPRecord;
		this.totalPage = totalPage;
	}

    public CachedRowSet getRowSet()
    {
        return rowSet;
        //获取 rowSet 成员变量的值。
    }

    public void setRowSet(CachedRowSet rowSet)
    {
        this.rowSet = rowSet;
        //设置 rowSet 成员变量的值。
        
    }

    public int getPageSize()
    {
        return pageSize;
        
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getTotalRecord()
    {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord)
    {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

	
}