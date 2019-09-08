package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.Exam;
import edu.prj.dao.ExamDao;
import edu.util.DbPub;

public class ExamDaoImpl implements ExamDao{

	@Override
	public Long insert(Exam bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Insert into Exam(studentId,PaperId,StartOn,EndOn,isMark,totalScore)");
		sb.append(" values(?,?,?,?,?,?)");
		paramsList.add(bean.getStudentId());
		paramsList.add(bean.getPaperId());
		paramsList.add(bean.getStartOn());
		paramsList.add(bean.getEndOn());
		paramsList.add(bean.getIsMark());
		paramsList.add(bean.getTotalScore());
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
		sb.append(" Delete from Exam ");
		sb.append(" where Examid=?");
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
	public Long update(Exam bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Update Exam Set");
		sb.append(" studentId = ?");
		sb.append(" ,paperId = ?");
		sb.append(" ,StartOn = ?");
		sb.append(" ,EndOn = ?");
		sb.append(" ,ismark = ?");
		sb.append(" ,totalScore = ?");
		sb.append(" where id = ?");
		paramsList.add(bean.getStudentId());
		paramsList.add(bean.getPaperId());
		paramsList.add(bean.getStartOn());
		paramsList.add(bean.getEndOn());
		paramsList.add(bean.getIsMark());
		paramsList.add(bean.getTotalScore());
		paramsList.add(bean.getExamId());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Exam> list() {
		List<Exam> list=new ArrayList<Exam>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		
		sb.append(" Select * From Exam");
		sb.append(" Where 1=1");
		sb.append(" Order by Examid");
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		
		try {
			Exam bean=null;
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

	private Exam toBean(ResultSet rs) throws SQLException {
		Exam bean;
		bean=new Exam();
		bean.setExamId(rs.getLong("ExamId"));
		bean.setStudentId(rs.getLong("StudentId"));
		bean.setPaperId(rs.getLong("paperId"));;
		bean.setStartOn(rs.getDate("StartON"));
		bean.setEndOn(rs.getDate("EndON"));;
		bean.setIsMark(rs.getLong("isMark"));
		bean.setTotalScore(rs.getFloat("totalScore"));
		return bean;
	}

	@Override
	public Exam load(Long id) {
		Exam bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append(" select * from Exam ");
		sb.append(" where 1=1 ");
		sb.append(" and examId=? ");
		
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
		sb.append("select count(1) from Exam");
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
	public Exam loadByName(String name) {
		Exam bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Exam");
		sb.append("	where 1=1");
		sb.append(" And paperId=?");
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
		sb.append("select count(1) from Exam");
		sb.append("	where 1=1");
		sb.append(" and paperId=?");
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
	public List<Exam> listByName(String name) {
		// TODO Auto-generated method stub
		List<Exam> list=new ArrayList<Exam>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Exam");
		sb.append("	where 1=1");
		sb.append(" and paperId like ? ");
		sb.append(" order by examId");
		name="%"+name+"%";
		paramsList.add(name);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		Exam bean=null;
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
	public List<Exam> listById(Long id) {
		// TODO Auto-generated method stub
		List<Exam> list=new ArrayList<Exam>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Exam");
		sb.append("	where 1=1");
		sb.append(" and studentid like ? ");
		sb.append(" order by examId");
		paramsList.add(id);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		Exam bean=null;
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
	public Long updateMark(Exam bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Update Exam Set");
		sb.append(" isMark = ?");
		sb.append(" where examid = 2");
		paramsList.add(bean.getIsMark());
//		paramsList.add(bean.getExamId());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}
}
