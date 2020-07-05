package PhotoServer;

import java.text.SimpleDateFormat;
import java.util.Random;

import Dao.Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class addCustomer.
 *
 * @author nieming
 * @version  v1.0
 * @date 2020-7-4
 */
public class addCustomer {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		int c;Random r =new Random();
		String tempfield1;
		String tempfield2;
		String tempfield3;
		String tempfield4;
		String tempfield5;
		Dao Dao2 = new Dao();
		for(int i=481;i<600;i++) {
			 tempfield1="123"+i%100; 
			 tempfield2="myClone"+i;
			 c=r.nextInt(89999)+10000+1;
			 tempfield3="135000"+c;
			c=r.nextInt(89999)+10000+1;
			 tempfield4="7631"+c;
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
			 tempfield5 = tempDate.format(new java.util.Date());
		String sql = "INSERT into Customer(CPassword,Cname,CtelNumber,Caddtime,Cqqnumber) values('" + tempfield1
				+ "','" + tempfield2 + "'," + tempfield3 + ",'" + tempfield5 + "','" + tempfield4 + "')";
		 Dao2.insert(sql);
		 if(i%1000==0)
			 System.out.println("²åÈë"+i);
		}
		System.out.println("yes");
	}
}
