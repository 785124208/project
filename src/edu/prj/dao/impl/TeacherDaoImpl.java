package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.Student;
import edu.prj.bean.Teacher;
import edu.prj.dao.*;
import edu.util.DbPub;


public class TeacherDaoImpl implements TeacherDao{

	@Override
	public Long insert(Teacher bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Insert into Teacher(LoginName,LoginPwd,NickName,isDisabled)");
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
		sb.append(" Delete from Teacher ");
		sb.append(" where teacherid=?");
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
	public Long update(Teacher bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Update Teacher Set");
		sb.append(" LoginName = ?");
		sb.append(" ,LoginPwd = ?");
		sb.append(" ,NickName = ?");
		sb.append(" ,isDisabled = ?");
		sb.append(" where Teacherid = ?");
		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getNickName());
		paramsList.add(bean.getIsDisabled());
		paramsList.add(bean.getTeacherId());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Teacher> list() {
		List<Teacher> list=new ArrayList<Teacher>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();

		sb.append(" Select * From Teacher");
		sb.append(" Where 1=1");
		sb.append(" Order by TeacherId");
//		sb.append(" Select A.*, S.name As stuName, B.name As bookName");
//		sb.append(" From Borrow A");
//		sb.append(" Left Join Student S on A.stuid = S.id");
//		sb.append(" Left Join Book B on A.bookid=B.id");
//		sb.append(" Where 1=1");
//		sb.append(" Order by A.id");
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		
		try {
			Teacher bean=null;
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

	private Teacher toBean(ResultSet rs) throws SQLException {
		Teacher bean;
		bean=new Teacher();
		bean.setTeacherId(rs.getLong("TeacherId"));
		bean.setLoginName(rs.getString("loginName"));
		bean.setLoginPwd(rs.getString("loginPwd"));
		bean.setNickName(rs.getString("nickName"));
		bean.setIsDisabled(rs.getLong("IsDisabled"));
		return bean;
	}

	@Override
	public Teacher load(Long id) {
		// TODO Auto-generated method stub
		Teacher bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append(" select * from Teacher ");
		sb.append(" where 1=1 ");
		sb.append(" and TeacherId=? ");
		
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
		sb.append("select count(1) from Teacher");
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
	public Teacher loadByName(String name) {
		// TODO Auto-generated method stub
		Teacher bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append(" select * from Teacher ");
		sb.append(" where 1=1 ");
		sb.append(" and LoginName=? ");
		
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
		// TODO Auto-generated method stub
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select count(1) from Teacher");
		sb.append("	where 1=1");
		sb.append(" and loginname=?");
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
	public List<Teacher> listByName(String name) {
		List<Teacher> list=new ArrayList<Teacher>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Teacher");
		sb.append("	where 1=1");
		sb.append(" and loginName like ? ");
		sb.append(" order by TeacherId");
		name="%"+name+"%";
		paramsList.add(name);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		Teacher bean=null;
		try {
			while(rs.next()) {
				bean=toBean(rs);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}
	
	@Override
	public Long updatepwd(Teacher bean) {
		// TODO Auto-generated method stub
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Update Student Set");
		sb.append(" loginPwd = ?");
		sb.append(" where loginName = ?");
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getLoginName());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}
}
