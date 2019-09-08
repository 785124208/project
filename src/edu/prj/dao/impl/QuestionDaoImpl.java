package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.Question;
import edu.prj.dao.QuestionDao;
import edu.util.DbPub;

public class QuestionDaoImpl  implements QuestionDao{

	@Override
	public Long insert(Question bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Insert into Question(Qtype,Question,ItemA,ItemB,ItemC,ItemD,ItemE,ItemF,answer,subjectid,tag)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?,?)");
		paramsList.add(bean.getqType());
		paramsList.add(bean.getQuestion());
		paramsList.add(bean.getItemA());
		paramsList.add(bean.getItemB());
		paramsList.add(bean.getItemC());
		paramsList.add(bean.getItemD());
		paramsList.add(bean.getItemE());
		paramsList.add(bean.getItemF());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getSubjectid());
		paramsList.add(bean.getTag());
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
		sb.append(" Delete from Question ");
		sb.append(" where Questionid=?");
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
	public Long update(Question bean) {
		Long num=0L;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		sb.append(" Update Question Set");
		sb.append(" Qtype = ?");
		sb.append(" ,Question = ?");
		sb.append(" ,ItemA = ?");
		sb.append(" ,ItemB = ?");
		sb.append(" ,ItemC = ?");
		sb.append(" ,ItemD = ?");
		sb.append(" ,ItemE = ?");
		sb.append(" ,ItemF = ?");
		sb.append(" ,answer = ?");
		sb.append(" ,subjectId = ?");
		sb.append(" ,tag = ?");
		sb.append(" where questionid = ?");
		paramsList.add(bean.getqType());
		paramsList.add(bean.getQuestion());
		paramsList.add(bean.getItemA());
		paramsList.add(bean.getItemB());
		paramsList.add(bean.getItemC());
		paramsList.add(bean.getItemD());
		paramsList.add(bean.getItemE());
		paramsList.add(bean.getItemF());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getSubjectid());
		paramsList.add(bean.getTag());
		paramsList.add(bean.getQuestionId());
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		Connection conn=null;
		conn=DbPub.getConn();
		num=DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Question> list() {
		List<Question> list=new ArrayList<Question>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList =new ArrayList<Object>();
		
		sb.append(" Select * From Question");
		sb.append(" Where 1=1");
		sb.append(" Order by Questionid");
		
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		
		try {
			Question bean=null;
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

	private Question toBean(ResultSet rs) throws SQLException {
		Question bean;
		bean=new Question();
		bean.setQuestionId(rs.getLong("questionId"));
		bean.setqType(rs.getLong("qType"));
		bean.setQuestion(rs.getString("Question"));
		bean.setItemA(rs.getString("ItemA"));
		bean.setItemB(rs.getString("ItemB"));
		bean.setItemC(rs.getString("ItemC"));
		bean.setItemD(rs.getString("ItemD"));
		bean.setItemE(rs.getString("ItemE"));
		bean.setItemF(rs.getString("ItemF"));
		bean.setAnswer(rs.getString("answer"));
		bean.setSubjectid(rs.getLong("subjectid"));
		bean.setTag(rs.getString("tag"));
		return bean;
	}

	@Override
	public Question load(Long id) {
		Question bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append(" select * from Question ");
		sb.append(" where 1=1 ");
		sb.append(" and questionid=? ");
		
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
		sb.append("select count(1) from Question");
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
	public Question loadByName(String name) {
		Question bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Question");
		sb.append("	where 1=1");
		sb.append(" And Question=?");
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
	public Question loadByAnswer(String answer) {
		Question bean=null;
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Question");
		sb.append("	where 1=1");
		sb.append(" And answer=?");
		paramsList.add(answer);
		
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
		sb.append("select count(1) from Question");
		sb.append("	where 1=1");
		sb.append(" and Question=?");
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
	public List<Question> listByName(String name) {
		// TODO Auto-generated method stub
		List<Question> list=new ArrayList<Question>();
		StringBuffer sb=new StringBuffer();
		List<Object> paramsList=new ArrayList<Object>();
		sb.append("select * from Question");
		sb.append("	where 1=1");
		sb.append(" and Question like ? ");
		sb.append(" order by questionid");
		name="%"+name+"%";
		paramsList.add(name);
		String sql=sb.toString();
		Object[] params=paramsList.toArray();
		
		Connection conn=null;
		ResultSet rs=null;
		conn=DbPub.getConn();
		rs=DbPub.query(conn, sql, params);
		Question bean=null;
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
