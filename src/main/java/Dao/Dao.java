package Dao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
    // TODO: Auto-generated Javadoc

    /**
     * The Class Dao.
     *数据库操作文件
     * @author afsummer
     * @date 2020年7月3日
     */
    
public class Dao {
	
	/** The connection. */
	private Connection connection = null;
	
	/** The rs. */
	private ResultSet rs = null;
	
	/** The pstm. */
	private  PreparedStatement pstm=null;
	
	/** The url sql. */
	//sqlServer数据库url
	private String urlSql ;
	
	/** The user sql. */
	private String userSql ;
	
	/** The password sql. */
	private String passwordSql ;
	
	/** The driver name. */
	private String driverName;
	
	/**
	 * Instantiates a new dao.
	 */
	public Dao() {
		FileRead("src\\jdbcbegin.txt");//储存数据库信息的文件
		
	}
	
	/**
	 * File read.
	 *数据库信息连接
	 * @param filename the filename
	 */
	public void FileRead(String filename) {
		String filePath=filename;
		 BufferedReader reader = null;
			String tempString = null;
		File file=new File(filePath);
					try {
						FileReader fr = new FileReader(file);
						
						reader = new BufferedReader(fr);
						while ((tempString = reader.readLine()) != null) {
							if(tempString.equals("urlSql")) {
								urlSql = reader.readLine();
							}else if(tempString.equals("userSql")) {
								userSql= reader.readLine();
							}else if(tempString.equals("passwordSql")) {
								passwordSql= reader.readLine();
							}else if(tempString.equals("driverName")) {
								driverName= reader.readLine();
							}
						
						}
						reader.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (reader != null) {
							try {
								reader.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
	
	/**
	 * Connect.
	 * 数据库连接
	 */
	public  void connect() {
		try {
			
				Class.forName(driverName);
			 connection = DriverManager.getConnection(urlSql, userSql, passwordSql);
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Disconnect.
	 * 关闭连接操作
	 */
	public void disconnect(){
		try{
			if(connection != null){
				connection.close();
				
				connection = null;
			}
			if(pstm!=null) {
				pstm.close();
				pstm=null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * Execute query 1.
	 *搜索操作
	 * @param sql the sql
	 * @return the result set
	 */
	public ResultSet executeQuery1(String sql) {
		try {
			pstm = connection.prepareStatement(sql);

			
			
			// 执行查询
			rs = pstm.executeQuery();
			
		} 
		catch(SQLException ex) { 
			ex.printStackTrace();
		}

		return rs;
	}
	
	/**
	 * Insert.
	 *插入操作
	 * @param sql the sql
	 * @return the int
	 */
	//插入
		public int insert(String sql) {
			int count=this.executeUpdate(sql);
				return count;
		}
		//删除
	
	//executeUpdate 的返回值是一个整数，指示受影响的行数（即更新计数）。
	//executeUpdate用于执行 INSERT、UPDATE 或 DELETE 语句
	//以及 SQL DDL（数据定义语言）语句，例如 CREATE TABLE 和 DROP TABLE。
	
	/**
		 * Execute update.
		 *
		 * @param sql the sql
		 * @return the int
		 */
		//执行增、删、改语句的方法
	public int executeUpdate(String sql) {
		int count = 0;
		connect();
		try {
			pstm = connection.prepareStatement(sql);
			count = pstm.executeUpdate();
			
		} 
		catch(SQLException ex) { 
			System.err.println(ex.getMessage());		
		}
		
		return count;
	}

/**
 * The main method.
 *
 * @param args the arguments
 */
public static void main(String[] args) {
	Dao dao=new Dao();
}
}
