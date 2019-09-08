package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.ExamItem;
import edu.prj.dao.ExamItemDao;
import edu.util.DbPub;

public class ExamItemDaoImpl  implements ExamItemDao{

	@Override
	public Long insert(ExamItem bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Insert into ExamItem(ExamId,QuestionId,stuAnswer,stdAnswer,stdscore,Markresult,GainScore)");
		sb.append(" values(?,?,?,?,?,?,?)");
		paramsList.add(bean.getExamId());
		paramsList.add(bean.getQuestionId());
		paramsList.add(bean.getStuAnswer());
		paramsList.add(bean.getStdAnswer());
		paramsList.add(bean.getStdScore());
		paramsList.add(bean.getMarkResult());
		paramsList.add(bean.getGainScore());
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
		sb.append(" Delete from ExamItem ");
		sb.append(" where ExamItemid=?");
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
	public Long update(ExamItem bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Update ExamItem Set");
		sb.append(" ExamId = ?");
		sb.append(" ,questionId = ?");
		sb.append(" ,stuAnswer = ?");
		sb.append(" ,stdAnswer = ?");
		sb.append(" ,stdScore = ?");
		sb.append(" ,MarkResult = ?");
		sb.append(" ,GainScore = ?");
		sb.append(" where itemid = ?");
		paramsList.add(bean.getExamId());
		paramsList.add(bean.getQuestionId());
		paramsList.add(bean.getStuAnswer());
		paramsList.add(bean.getStdAnswer());
		paramsList.add(bean.getStdScore());
		paramsList.add(bean.getMarkResult());
		paramsList.add(bean.getGainScore());
		paramsList.add(bean.getItemId());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<ExamItem> list() {
		List<ExamItem> list=new ArrayList<ExamItem>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		
		sb.append(" Select * From ExamItem");
		sb.append(" Where 1=1");
		sb.append(" Order by Itemid");
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		
		try {
			ExamItem bean=null;
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

	private ExamItem toBean(ResultSet rs) throws SQLException {
		ExamItem bean;
		bean=new ExamItem();
		bean.setItemId(rs.getLong("ItemId"));
		bean.setExamId(rs.getLong("ExamId"));
		bean.setQuestionId(rs.getLong("questionId"));
		bean.setStuAnswer(rs.getString("stuAnswer"));
		bean.setStdAnswer(rs.getString("stdAnswer"));
		bean.setStdScore(rs.getFloat("StdScore"));
		bean.setMarkResult(rs.getLong("MarkResult"));
		bean.setGainScore(rs.getFloat("GainScore"));
		return bean;
	}

	@Override
	public ExamItem load(Long id) {
		ExamItem bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append(" select * from ExamItem ");
		sb.append(" where 1=1 ");
		sb.append(" and itemId=? ");
		
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
		sb.append("select count(1) from ExamItem");
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
	public ExamItem loadByName(String name) {
		ExamItem bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from ExamItem");
		sb.append("	where 1=1");
		sb.append(" And examId=?");
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
		sb.append("select count(1) from ExamItem");
		sb.append("	where 1=1");
		sb.append(" and examId=?");
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
	public List<ExamItem> listByName(String name) {
		// TODO Auto-generated method stub
		List<ExamItem> list=new ArrayList<ExamItem>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from ExamItem");
		sb.append("	where 1=1");
		sb.append(" and examId like ? ");
		sb.append(" order by itemId");
		name="%"+name+"%";
		paramsList.add(name);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		ExamItem bean=null;
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
	public List<ExamItem> listById(Long id) {
		// TODO Auto-generated method stub
		List<ExamItem> list=new ArrayList<ExamItem>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Exam");
		sb.append("	where 1=1");
		sb.append(" and examId like ? ");
		sb.append(" order by itemId");
		paramsList.add(id);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		ExamItem bean=null;
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
