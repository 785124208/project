package edu.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbPub {
	public static String STU_DRIVER;
	public static String STU_URL;
	public static String STU_USERNAME;
	public static String STU_PASSWORD;
	public static String fileName="/app.properties";
	static {
		java.util.Properties prop =new java.util.Properties();
		try {
			prop.load(DbPub.class.getResourceAsStream(fileName));
			STU_DRIVER=prop.getProperty("STU_DRIVER");
			STU_URL=prop.getProperty("STU_URL");
			STU_USERNAME=prop.getProperty("STU_USERNAME");
			STU_PASSWORD=prop.getProperty("STU_PASSWORD");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("【"+fileName+"】+不存在");
		}
		try {
			Class.forName(DbPub.STU_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("类全名【" + DbPub.STU_DRIVER + "】没有找到。");
		}
	}
	
	public static Connection getConn(){
		Connection conn=null;
		// 使用驱动管理器类Driver Manager,创建一个连接对象conn
		try {
		conn = DriverManager.getConnection(DbPub.STU_URL, DbPub.STU_USERNAME, DbPub.STU_PASSWORD);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("数据库连接失败请检查四大金刚的相关参数。"+e.getMessage());
		}
		return conn;
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, Statement stmt) {
		close(conn,stmt,null);
	}
	
	public static void close(Connection conn) {
		close(conn,null,null);
	}
	
	public static ResultSet query(Connection conn,String sql,Object...params)
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// 使用conn，创建stmt
		try {
			stmt = conn.prepareStatement(sql);
			if(params !=null) {
				for(int i=0;i<params.length;i++) {
					stmt.setObject(i+1, params[i]);
				}
			}
			// stmt,执行sql语句，会返回一个re
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("执行【" + sql + "】出现错误。");
		}
		return rs;
	}

	public static Long queryScalarLong(Connection conn,String sql,Object...params)
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Long result=0L;
		// 使用conn，创建stmt
		try {
			stmt = conn.prepareStatement(sql);
			if(params !=null) {
				for(int i=0;i<params.length;i++) {
					stmt.setObject(i+1, params[i]);
				}
			}
			// stmt,执行sql语句，会返回一个re
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				result=rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("执行【" + sql + "】出现错误。");
		}
		return result;
	}
	
	/**
	 * 执行查询返回受影响行数
	 * @param conn
	 * @param sql
	 * @return
	 */
	public static Long update(Connection conn,String sql,Object...params)
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer num = 0;
		// 使用conn，创建stmt
		try {
			stmt = conn.prepareStatement(sql);
			if(params !=null) {
				for(int i=0;i<params.length;i++) {
					stmt.setObject(i+1, params[i]);
				}
			}
			// stmt,执行sql语句，会返回一个re
			num = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("执行【" + sql + "】出现错误。");
		}
		return num.longValue(); 
	}

}
