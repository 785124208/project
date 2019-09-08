package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.Student;
import edu.prj.dao.*;
import edu.util.DbPub;


public class StudentDaoImpl implements StudentDao{

	@Override
	public Long insert(Student bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Insert into Student(loginName,loginPwd,nickName,isDisabled,roomId)");
		sb.append(" values(?,?,?,?,?)");
		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getNickName());
		paramsList.add(bean.getIsDisabled());
		paramsList.add(bean.getRoomId());
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
		sb.append(" Delete from Student ");
		sb.append(" where studentid=?");
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
	public Long update(Student bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Update Student Set");
		sb.append(" loginName = ?");
		sb.append(" ,loginPwd = ?");
		sb.append(" ,nickName = ?");
		sb.append(" ,isdisabled = ?");
		sb.append(" ,roomId = ?");
		sb.append(" where studentid = ?");
		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getNickName());
		paramsList.add(bean.getIsDisabled());
		paramsList.add(bean.getRoomId());
		paramsList.add(bean.getStudentId());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Student> list() {
		List<Student> list=new ArrayList<Student>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		
		sb.append(" Select * From Student");
		sb.append(" Where 1=1");
		sb.append(" Order by studentid");
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		
		try {
			Student bean=null;
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

	private Student toBean(ResultSet rs) throws SQLException {
		Student bean;
		bean=new Student();
		bean.setStudentId(rs.getLong("studentId"));
		bean.setLoginName(rs.getString("loginName"));
		bean.setLoginPwd(rs.getString("loginPwd"));
		bean.setNickName(rs.getString("nickName"));
		bean.setIsDisabled(rs.getLong("isDisabled"));
		bean.setRoomId(rs.getLong("roomId"));
		return bean;
	}

	@Override
	public Student load(Long id) {
		Student bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append(" select * from Student ");
		sb.append(" where 1=1 ");
		sb.append(" and studentId=? ");
		
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
		sb.append("select count(1) from Student");
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
	public Student loadByName(String name) {
		Student bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Student");
		sb.append("	where 1=1");
		sb.append(" And loginName=?");
		paramsList.add(name);
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		try {
			if(rs.next()){
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
		sb.append("select count(1) from Student");
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
	public List<Student> listByName(String name) {
		// TODO Auto-generated method stub
		List<Student> list=new ArrayList<Student>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Student");
		sb.append("	where 1=1");
		sb.append(" and loginname like ? ");
		sb.append(" order by studentid");
		name="%"+name+"%";
		paramsList.add(name);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		Student bean=null;
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

	@Override
	public Long updatepwd(Student bean) {
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
