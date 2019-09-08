	 package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import com.liuvei.common.SysFun;

import edu.prj.bean.Book;
import edu.prj.dao.*;
import edu.util.DbPub;


public class BookDaoImpl implements BookDao{

	@Override
	public Long insert(Book bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Insert into Book(name,remainnum,borrownum)");
		sb.append(" values(?,?,?)");
		paramsList.add(bean.getName());
		paramsList.add(bean.getRemainnum());
		paramsList.add(bean.getBorrownum());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		if(num>0) {
			//sql="select max(id) from Manager";
			sql="select @@identity";	//或者sql="SELECT LAST_INSERT_ID()";
			Long result =DbPub.queryScalarLong(conn, sql);
			if(result>0) {
			bean.setId(num);
			num=result;
		}
		}
		DbPub.close(conn);
		return num;
	}

	@Override
	public Long delete(Long id) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Delete from Book ");
		sb.append(" where id=?");
		paramsList.add(id);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Long update(Book bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Update Book Set");
		sb.append(" name = ?");
		sb.append(" ,remainnum=?");
		sb.append(" ,borrownum=?");
		sb.append(" where id = ?");
		paramsList.add(bean.getName());
		paramsList.add(bean.getRemainnum());
		paramsList.add(bean.getBorrownum());
		paramsList.add(bean.getId());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Book> list() {
		List<Book> list=new ArrayList<Book>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		
		sb.append(" Select * From Book");
		sb.append(" Where 1=1");
		sb.append(" Order by id");
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		
		try {
			Book bean=null;
			while(rs.next()) {
				bean = toBean(rs);
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

	private Book toBean(ResultSet rs) throws SQLException {
		Book bean;
		bean=new Book();
		bean.setId(rs.getLong("id"));
		bean.setName(rs.getString("name"));
		bean.setRemainnum(rs.getLong("Remainnum"));
		bean.setBorrownum(rs.getLong("Borrownum"));
		return bean;
	}
	
	private Book toBeanEx(ResultSet rs) throws SQLException {
		Book bean;
		bean=new Book();
		bean.setId(rs.getLong("id"));
		bean.setName(rs.getString("name"));
		bean.setRemainnum(rs.getLong("Remainnum"));
		bean.setBorrownum(rs.getLong("Borrownum"));
		return bean;
	}

	@Override
	public Book load(Long id) {
		// TODO Auto-generated method stub
		Book bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append(" select * from Book ");
		sb.append(" where 1=1 ");
		sb.append(" and id=? ");
		
		paramsList.add(id);
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		try {
			if(rs.next());{
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);
		return bean;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select count(1) from Book");
		sb.append("	where 1=1");
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Book loadByName(String name) {
		Book bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Book");
		sb.append("	where 1=1");
		sb.append(" And name=?");
		paramsList.add(name);
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		try {
			while(rs.next());{
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);
		return bean;
	}

	@Override
	public Long countByName(String name) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select count(1) from Book");
		sb.append("	where 1=1");
		sb.append(" and name=?");
		paramsList.add(name);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Book> listByName(String stuName,String bookName) {
		List<Book> list=new ArrayList<Book>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append(" select a.* ");
		sb.append(" ,b.Name as stuName ");
		sb.append(" ,c.Name as bookName ");
		sb.append("	From Borrow a ");
		sb.append(" 	left join student b on a.stuId=b.id ");
		sb.append(" 	left join book c on a.bookId=c.id ");
		sb.append(" where 1=1 ");

		if (!SysFun.isNullOrEmpty(stuName)) {
			sb.append(" 	And b.name like ? ");
			stuName = "%" + stuName + "%";
			paramsList.add(stuName);
		}
		if (!SysFun.isNullOrEmpty(bookName)) {
			sb.append(" 	And c.name like ? ");
			bookName = "%" + bookName + "%";
			paramsList.add(bookName);
		}
		sb.append("   order by a.id ");
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		Book bean=null;
		try {
			while(rs.next()) {
				bean=toBeanEx(rs);
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

}
