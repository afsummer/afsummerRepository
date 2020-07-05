package Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

import Dao.Dao;
    // TODO: Auto-generated Javadoc

 
    
public class Daotest {
	
	/**
	 * Textexecute query 1.
	 */
	@Test
	public void TextexecuteQuery1() {
		 Dao daotest=new Dao();
		 daotest.connect();
		 String sql = "SELECT * from Studio ";
		 ResultSet rs = null;
		 assertNull(rs);
		 rs=daotest.executeQuery1(sql);
		 assertNotNull(rs);
	}

/**
 * The main method.
 *
 
 */
public static void main(String[] args) {
	Daotest dao=new Daotest();
}
}
