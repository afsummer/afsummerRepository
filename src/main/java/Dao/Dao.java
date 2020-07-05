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
     *���ݿ�����ļ�
     * @author afsummer
     * @date 2020��7��3��
     */
    
public class Dao {
	
	/** The connection. */
	private Connection connection = null;
	
	/** The rs. */
	private ResultSet rs = null;
	
	/** The pstm. */
	private  PreparedStatement pstm=null;
	
	/** The url sql. */
	//sqlServer���ݿ�url
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
		FileRead("src\\jdbcbegin.txt");//�������ݿ���Ϣ���ļ�
		
	}
	
	/**
	 * File read.
	 *���ݿ���Ϣ����
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
	 * ���ݿ�����
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
	 * �ر����Ӳ���
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
	 *��������
	 * @param sql the sql
	 * @return the result set
	 */
	public ResultSet executeQuery1(String sql) {
		try {
			pstm = connection.prepareStatement(sql);

			
			
			// ִ�в�ѯ
			rs = pstm.executeQuery();
			
		} 
		catch(SQLException ex) { 
			ex.printStackTrace();
		}

		return rs;
	}
	
	/**
	 * Insert.
	 *�������
	 * @param sql the sql
	 * @return the int
	 */
	//����
		public int insert(String sql) {
			int count=this.executeUpdate(sql);
				return count;
		}
		//ɾ��
	
	//executeUpdate �ķ���ֵ��һ��������ָʾ��Ӱ��������������¼�������
	//executeUpdate����ִ�� INSERT��UPDATE �� DELETE ���
	//�Լ� SQL DDL�����ݶ������ԣ���䣬���� CREATE TABLE �� DROP TABLE��
	
	/**
		 * Execute update.
		 *
		 * @param sql the sql
		 * @return the int
		 */
		//ִ������ɾ�������ķ���
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
