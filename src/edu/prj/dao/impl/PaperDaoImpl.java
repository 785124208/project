package edu.prj.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.Paper;
import edu.prj.dao.PaperDao;
import edu.util.DbPub;

public class PaperDaoImpl implements PaperDao {

	@Override
	public Long insert(Paper bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Insert into Paper(PaperName,TotalScore,PerScore,QuestionNum,ExamMinute,StartOn,EndOn,HasGenerate)");
		sb.append(" values(?,?,?,?,?,?,?,?)");
		paramsList.add(bean.getPaperName());
		paramsList.add(bean.getTotalScore());
		paramsList.add(bean.getPerScore());
		paramsList.add(bean.getQuestionNum());
		paramsList.add(bean.getExamMinute());
//		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
//		String startOn=df.format(bean.getStartOn());
//		Date t=new Date(bean.getStartOn().getDate());
		paramsList.add(bean.getStartOn());
//		DateFormat ef=new SimpleDateFormat("yyyy-MM-dd");
//		String endOn=ef.format(bean.getStartOn());
//		Date et=new Date(bean.getEndOn().getDate());
		paramsList.add(bean.getEndOn());
		paramsList.add(bean.getHasGenerate());
		
//		System.out.println(bean.getStartOn());
//		System.out.println(bean.getEndOn());
		
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
		sb.append(" Delete from Paper ");
		sb.append(" where Paperid=?");
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
	public Long update(Paper bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Update Paper Set");
		sb.append(" PaperName = ?");
		sb.append(" ,TotalScore = ?");
		sb.append(" ,PerScore = ?");
		sb.append(" ,QuestionNum = ?");
		sb.append(" ,ExamMinute = ?");
		sb.append(" ,StartOn = ?");
		sb.append(" ,EndOn = ?");
		sb.append(" ,HasGenerate = ?");
		sb.append(" where id = ?");
		paramsList.add(bean.getPaperName());
		paramsList.add(bean.getTotalScore());
		paramsList.add(bean.getPerScore());
		paramsList.add(bean.getQuestionNum());
		paramsList.add(bean.getExamMinute());
		paramsList.add(bean.getStartOn());
		paramsList.add(bean.getEndOn());
		paramsList.add(bean.getHasGenerate());
		paramsList.add(bean.getPaperId());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Paper> list() {
		List<Paper> list=new ArrayList<Paper>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		
		sb.append(" Select * From Paper");
		sb.append(" Where 1=1");
		sb.append(" Order by Paperid");
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		
		try {
			Paper bean=null;
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

	private Paper toBean(ResultSet rs) throws SQLException {
		Paper bean;
		bean=new Paper();
		bean.setPaperId(rs.getLong("paperid"));
		bean.setPaperName(rs.getString("paperName"));
		bean.setTotalScore(rs.getFloat("TotalScore"));
		bean.setPerScore(rs.getFloat("perScore"));
		bean.setQuestionNum(rs.getLong("questionnum"));
		bean.setExamMinute(rs.getLong("ExamMinute"));
		bean.setStartOn(rs.getDate("StartON"));
		bean.setEndOn(rs.getDate("EndON"));
		bean.setHasGenerate(rs.getLong("HasGenerate"));
		return bean;
	}

	@Override
	public Paper load(Long id) {
		Paper bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append(" select * from Paper ");
		sb.append(" where 1=1 ");
		sb.append(" and paperid=? ");
		
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
		sb.append("select count(1) from Paper");
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
	public Paper loadByName(String name) {
		Paper bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Paper");
		sb.append("	where 1=1");
		sb.append(" And PaperName=?");
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
		sb.append("select count(1) from Paper");
		sb.append("	where 1=1");
		sb.append(" and PaperName=?");
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
	public List<Paper> listByName(String name) {
		// TODO Auto-generated method stub
		List<Paper> list=new ArrayList<Paper>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Paper");
		sb.append("	where 1=1");
		sb.append(" and PaperName like ? ");
		sb.append(" order by paperid");
		name="%"+name+"%";
		paramsList.add(name);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		Paper bean=null;
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
