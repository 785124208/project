package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.Subject;
import edu.prj.dao.SubjectDao;
import edu.util.DbPub;

public class SubjectDaoImpl implements SubjectDao{

	@Override
	public Long insert(Subject bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Insert into Subject(SubjectName,STATUS)");
		sb.append(" values(?,?)");
		paramsList.add(bean.getSubjectName());
		paramsList.add(bean.getSTATUS());

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
		sb.append(" Delete from Subject ");
		sb.append(" where Subjectid=?");
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
	public Long update(Subject bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Update Subject Set");
		sb.append(" SubjectName = ?");
		sb.append(" ,STATUS = ?");
		sb.append(" where subjectid = ?");
		paramsList.add(bean.getSubjectName());
		paramsList.add(bean.getSTATUS());
		paramsList.add(bean.getSubjectId());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Subject> list() {
		List<Subject> list=new ArrayList<Subject>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		
		sb.append(" Select * From Subject");
		sb.append(" Where 1=1");
		sb.append(" Order by Subjectid");
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		
		try {
			Subject bean=null;
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

	private Subject toBean(ResultSet rs) throws SQLException {
		Subject bean;
		bean=new Subject();
		bean.setSubjectId(rs.getLong("subjectid"));
		bean.setSubjectName(rs.getString("subjectName"));
		bean.setSTATUS(rs.getString("STATUS"));
		return bean;
	}

	@Override
	public Subject load(Long id) {
		Subject bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append(" select * from Subject ");
		sb.append(" where 1=1 ");
		sb.append(" and Subjectid=? ");
		
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
		sb.append("select count(1) from Subject");
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
	public Subject loadByName(String name) {
		Subject bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Subject");
		sb.append("	where 1=1");
		sb.append(" And SubjectName=?");
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
		sb.append("select count(1) from Subject");
		sb.append("	where 1=1");
		sb.append(" and SubjectName=?");
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
	public List<Subject> listByName(String name) {
		// TODO Auto-generated method stub
		List<Subject> list=new ArrayList<Subject>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Subject");
		sb.append("	where 1=1");
		sb.append(" and SubjectName like ? ");
		sb.append(" order by Subjectid");
		name="%"+name+"%";
		paramsList.add(name);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		Subject bean=null;
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