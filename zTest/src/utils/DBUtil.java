package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库帮助类
 * @author Tanken·L
 * @Start  2016-1-8
 * @Done   2016-1-8
 * @Revision 
 * 打开连接
 * 关闭连接
 */
public class DBUtil {
	//静态代码块
	static{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConn(String dbName, String serverName, String tcpPort, String userName, String passWord){
		Connection conn = null;
		try {
			//连接数据库
			conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ":" + tcpPort + ";databaseName="+dbName, userName, passWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭连接
	 * @param rs
	 * @param pstmt
	 * @param conn
	 */
	public static void closeDB(ResultSet rs, PreparedStatement pstmt, Connection conn){
		try {
			if(rs != null){
				rs.close();
			}
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt != null){
				pstmt.close();
			}
			pstmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null){
				conn.close();
			}
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getConn("YXHIS", "192.168.200.32", "1433", "sa", "123123"));
	}
}
