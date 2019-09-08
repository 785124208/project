package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.Manager;
import edu.prj.dao.*;
import edu.util.DbPub;


public class ManagerDaoImpl implements ManagerDao{

	@Override
	public Long insert(Manager bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Insert into Manager(LoginName,LoginPwd,NickName,isDisabled)");
		sb.append(" values(?,?,?,?)");
		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getNickName());
		paramsList.add(bean.getIsDisabled());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Long delete(Long id) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Delete from Manager ");
		sb.append(" where Managerid=?");
		sb.append(" and LoginName <> ? ");
		paramsList.add(id);
		String uesr="admin";
		paramsList.add(uesr);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Long update(Manager bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Update Manager Set");
		sb.append(" LoginName = ?");
		sb.append(" ,LoginPwd = ?");
		sb.append(" ,NickName = ?");
		sb.append(" ,isDisabled = ?");
		sb.append(" where managerid = ?");
		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getNickName());
		paramsList.add(bean.getIsDisabled());
		paramsList.add(bean.getManagertId());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Manager> list() {
		List<Manager> list=new ArrayList<Manager>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		
		sb.append(" Select * From Manager");
		sb.append(" Where 1=1");
		sb.append(" Order by Managerid");
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		
		try {
			Manager bean=null;
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

	private Manager toBean(ResultSet rs) throws SQLException {
		Manager bean;
		bean=new Manager();
		bean.setManagertId(rs.getLong("managerid"));
		bean.setLoginName(rs.getString("loginName"));
		bean.setLoginPwd(rs.getString("loginPwd"));
		bean.setNickName(rs.getString("nickName"));
		bean.setIsDisabled(rs.getLong("IsDisabled"));
		return bean;
	}

	@Override
	public Manager load(Long id) {
		Manager bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append(" select * from Manager ");
		sb.append(" where 1=1 ");
		sb.append(" and managerid=? ");
		
		paramsList.add(id);
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		try {
			if(rs.next());{
				bean=toBean(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);
		return bean;
	}

	@Override
	public Long count() {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select count(1) from Manager");
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
	public Manager loadByName(String name) {
		Manager bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Manager");
		sb.append("	where 1=1");
		sb.append(" And Loginname=?");
		paramsList.add(name);
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		try {
			if(rs.next()){
				bean=toBean(rs);
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
		sb.append("select count(1) from Manager");
		sb.append("	where 1=1");
		sb.append(" and loginName=?");
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
	public List<Manager> listByName(String name) {
		// TODO Auto-generated method stub
		List<Manager> list=new ArrayList<Manager>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Manager");
		sb.append("	where 1=1");
		sb.append(" and loginName like ? ");
		sb.append(" order by managerid");
		name="%"+name+"%";
		paramsList.add(name);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		Manager bean=null;
		try {
			while(rs.next()) {
				bean=toBean(rs);
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
