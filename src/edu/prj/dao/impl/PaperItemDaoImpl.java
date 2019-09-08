package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.PaperItem;
import edu.prj.dao.PaperItemDao;
import edu.util.DbPub;

public class PaperItemDaoImpl implements PaperItemDao {

	@Override
	public Long insert(PaperItem bean) {
		Long num = 0L;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert into PaperItem(PaperId,QuestionId,Answer,Score)");
		sb.append(" values(?,?,?,?)");
		paramsList.add(bean.getPaperId());
		paramsList.add(bean.getQuestionId());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getScore());
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Long delete(Long id) {
		Long num = 0L;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Delete from PaperItem ");
		sb.append(" where Itemid=?");
		paramsList.add(id);
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Long update(PaperItem bean) {
		Long num = 0L;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Update PaperItem Set");
		sb.append(" PaperId = ?");
		sb.append(" ,QuestionId = ?");
		sb.append(" ,Answer = ?");
		sb.append(" ,Score = ?");
		sb.append(" where Itemid = ?");
		paramsList.add(bean.getPaperId());
		paramsList.add(bean.getQuestionId());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getScore());
		paramsList.add(bean.getItemId());
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<PaperItem> list() {
		List<PaperItem> list = new ArrayList<PaperItem>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

//		sb.append(" Select P.*,Q.question as question ");
//		sb.append(" From PaperItem P");
//		sb.append(" Left Join Question Q on P.questionid = Q.questionid");
//		sb.append(" Where 1=1");
//		sb.append(" Order by P.itemid");

//		select pi.*, p.*, q.* from PaperItem pi 
//		left join paper p on p.Paperid = pi.PaperId
//		left join question q on pi.Questionid = q.Questionid 
//		where 1=1 
//		and p.paperid=?
		sb.append(" Select P.*,Q.question as question ");
		sb.append(" From PaperItem P,question q");
		sb.append(" Where 1=1");
		sb.append(" 	and q.questionid=p.questionid");
		sb.append(" Order by P.itemid");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			PaperItem bean = null;
			while (rs.next()) {
				bean = toBeanEx(rs);
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

	private PaperItem toBean(ResultSet rs) throws SQLException {
		PaperItem bean;
		bean = new PaperItem();
		bean.setItemId(rs.getLong("ItemId"));
		bean.setPaperId(rs.getLong("PaperId"));
		bean.setQuestionId(rs.getLong("questionId"));
		bean.setAnswer(rs.getString("Answer"));
		bean.setScore(rs.getFloat("Score"));
		return bean;
	}

	
	private PaperItem toBeanEx(ResultSet rs) throws SQLException {
		PaperItem bean;
		bean = new PaperItem();
		bean.setItemId(rs.getLong("ItemId"));
		bean.setPaperId(rs.getLong("PaperId"));
		bean.setQuestionId(rs.getLong("questionId"));
		bean.setQuestion(rs.getString("question"));
		bean.setAnswer(rs.getString("Answer"));
		bean.setScore(rs.getFloat("Score"));
		return bean;
	}
	
	private PaperItem toBeanMax(ResultSet rs) throws SQLException {
		PaperItem bean;
//		System.out.println("question:"+rs.getString("question"));
//		System.out.println("itmeA:"+rs.getString("itemA"));
//		System.out.println("Answer"+rs.getString("Answer"));
		bean = new PaperItem();
		bean.setItemId(rs.getLong("ItemId"));
		bean.setPaperId(rs.getLong("PaperId"));
		bean.setQuestionId(rs.getLong("questionId"));
		bean.setQuestion(rs.getString("question"));
		bean.setAnswer(rs.getString("Answer"));
		bean.setScore(rs.getFloat("Score"));
//		bean.setQusetion(rs.getString("question"));
		return bean;
	}
	
	@Override
	public List<PaperItem> listByItem(Long id) {
		List<PaperItem> list = new ArrayList<>();
		List<PaperItem> listAns = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
//		sb.append(" select pi.*, p.*, q.* from PaperItem pi ");
//		sb.append(" left join paper p on p.PaperID = pi.PaperID ");
//		sb.append("	left join question q on p.questionID = q.questionID ");
//		sb.append(" where 1=1 ");
//		sb.append(" and paperid=? ");
		
		sb.append(" select pi.*, p.*, q.* from PaperItem pi" );
		sb.append(" left join paper p on p.Paperid = pi.PaperId");
		sb.append(" left join question q on pi.Questionid = q.Questionid"); 
		sb.append(" where 1=1");
		sb.append(" and p.paperid=?");

		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			while (rs.next())	
			{
				PaperItem bean = null;
				bean = toBeanMax(rs);
//				listAns.add(bean);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);
//		System.out.println("list长度"+list.size());
		return list;
	}
	
	@Override
	public PaperItem load(Long id) {
		PaperItem bean = null;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from PaperItem ");
		sb.append(" where 1=1 ");
		sb.append(" and questionid=? ");

		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			if (rs.next())
			{
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);
		return bean;
	}

//	@Override
//	public Long count() {
//		Long num=0L;
//		StringBuffer sb=new StringBuffer();
//		List<Object> paramsList=new ArrayList<Object>();
//		sb.append("select count(1) from PaperItem");
//		sb.append("	where 1=1");
//		String sql=sb.toString();
//		Object[] params=paramsList.toArray();
//		
//		Connection conn=null;
//		conn=DbPub.getConn();
//		num=DbPub.queryScalarLong(conn, sql, params);
//		DbPub.close(conn);
//		return num;
//	}

	@Override
	public PaperItem loadByName(String name) {
		PaperItem bean = null;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("select * from PaperItem");
		sb.append("	where 1=1");
		sb.append(" And Paperid=?");
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			if (rs.next()) {
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
		Long num = 0L;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("select count(1) from PaperItem");
		sb.append("	where 1=1");
		sb.append(" and PaperItem=?");
		paramsList.add(name);
		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<PaperItem> listByName(String name) {
		// TODO Auto-generated method stub
		List<PaperItem> list = new ArrayList<PaperItem>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("select * from PaperItem");
		sb.append("	where 1=1");
		sb.append(" and Paperid like ? ");
		sb.append(" order by itemid");
		name = "%" + name + "%";
		paramsList.add(name);
		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		PaperItem bean = null;
		try {
			while (rs.next()) {
				bean = toBeanEx(rs);
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
	public List<PaperItem> listById(Long PaperId, Long questionId) {
		// TODO Auto-generated method stub
		List<PaperItem> list = new ArrayList<PaperItem>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("select * from PaperItem");
		sb.append("	where 1=1");
		sb.append(" and PaperId like ? ");
		sb.append(" and questionId like ? ");
		sb.append(" order by questionid");
		paramsList.add(PaperId);
		paramsList.add(questionId);
		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		PaperItem bean = null;
		try {
			while (rs.next()) {
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

}
