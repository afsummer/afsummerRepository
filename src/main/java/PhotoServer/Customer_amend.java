package PhotoServer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Dao.Dao;


    // TODO: Auto-generated Javadoc
/**
     * The Class Customer_amend.
     *顾客信息修改页面
     * @author afsummer
     * @date 2020年7月3日
     */
    
public class Customer_amend extends JFrame implements ActionListener{
	
	/** The content pane. */
	public JPanel contentPane;
	
	/** The Password. */
	private static JTextField Password;
	
	/** The Password 2. */
	private static JTextField Password2;
	
	/** The address. */
	private static JTextField address;
	
	/** The name. */
	private static JTextField name;
	
	/** The qqnumber. */
	private static JTextField qqnumber;
	
	/** The tel number. */
	private static JTextField telNumber;
	
	/** The id2. */
	private JLabel ID2=null;
	
	/** The Select query field str. */
	private String SelectQueryFieldStr;
	
	/** The Select query field str 2. */
	private String SelectQueryFieldStr2;
	
	/** The Cusid. */
	private String Cusid=null;
	
	/** The Customvectors. */
	private  Vector Customvectors=null;
	
	/**
	 * Instantiates a new customer amend.
	 *页面组件初始化
	 * @param id the id
	 * 传入id为顾客id
	 */
	public Customer_amend(String id) {
		Cusid=id;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 300, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("个人信息修改");
		lblNewLabel.setBounds(180, 10, 200, 51);
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 17));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);

		JPanel panel = new JPanel();
		panel.setBounds(5, 65, 450, 500);
		panel.setLayout(null);
		
		try{
			// 建立查询条件
			String sql="SELECT * from Customer where Customid=";
				sql = sql  + id+";";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro =new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
			while(rs.next()){//生存输出的列表的所需的信息
			
				 ID2 = new JLabel(rs.getString("Customid"));
				name = new JTextField(rs.getString("Cname"));
				Password = new JTextField(rs.getString("CPassword"));
				telNumber = new JTextField(rs.getString("CtelNumber"));
				qqnumber = new JTextField(rs.getString("Cqqnumber"));

		}
			DbPro.disconnect();
		}catch(SQLException sqle){
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}

	
		JLabel ID = new JLabel("    ID");
		ID.setFont(new Font("楷体", Font.PLAIN, 14));
		ID.setBounds(68, 20, 87, 20);
		
		ID2.setFont(new Font("楷体", Font.PLAIN, 14));
		ID2.setBounds(165, 20, 87, 20);
		JLabel Name = new JLabel("    用户名");
		Name.setFont(new Font("楷体", Font.PLAIN, 14));
		Name.setBounds(68, 60, 87, 20);
		JLabel MI = new JLabel("    密码");
		MI.setFont(new Font("楷体", Font.PLAIN, 14));
		MI.setBounds(68, 100, 87, 20);
		JLabel ReMI = new JLabel("   确认密码");
		ReMI.setFont(new Font("楷体", Font.PLAIN, 14));
		ReMI.setBounds(68, 140, 87, 20);
		JLabel Tel = new JLabel("   电话号码");
		Tel.setFont(new Font("楷体", Font.PLAIN, 14));
		Tel.setBounds(68, 180, 87, 20);
		JLabel QQ = new JLabel("   QQ号码");
		QQ.setFont(new Font("楷体", Font.PLAIN, 14));
		QQ.setBounds(68, 220, 87, 20);

		
		// 输入栏设置
		
		name.setBounds(165, 60, 128, 25);
		name.setColumns(20);
		Password.setBounds(165, 100, 128, 25);
		Password.setColumns(20);

		Password2 = new JTextField();
		Password2.setBounds(165, 140, 128, 25);
		Password2.setColumns(20);
		telNumber.setBounds(165, 180, 128, 25);
		telNumber.setColumns(20);
		qqnumber.setBounds(165, 220, 128, 25);
		qqnumber.setColumns(20);
		// 下拉框元素
		
		
		// 注册按钮
		JButton button = new JButton("确认修改");
		button.setFont(new Font("楷体", Font.PLAIN, 15));
		button.setBounds(324, 320, 120, 23);
		button.addActionListener(this);

		// 退出按钮
		JButton button_1 = new JButton("取消返回");
		button_1.setFont(new Font("楷体", Font.PLAIN, 15));
		button_1.setBounds(324, 350, 120, 23);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// sleep();
				setVisible(false);

			}
		});

		// 页面容器
		contentPane.add(lblNewLabel);
		contentPane.add(panel);
        panel.add(ID);
        panel.add(ID2);
		panel.add(Name);// 加入填空框名称
		panel.add(MI);
		panel.add(ReMI);
		panel.add(Tel);
		panel.add(QQ);
		// 填空文本框
		panel.add(name);
		panel.add(Password);
		panel.add(Password2);
		panel.add(telNumber);
		panel.add(qqnumber);

		
		// 按钮框
		panel.add(button);
		panel.add(button_1);

	}

	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 此方法中前三位格式有： 13+任意数 145,147,149
	 * 15+除4的任意数(不要写^4，这样的话字母也会被认为是正确的) 166 17+3,5,6,7,8 18+任意数 198,199.
	 *
	 * @param str the str
	 * @return true, if is tel
	 * @throws PatternSyntaxException the pattern syntax exception
	 */
	public static boolean isTel(String str) throws PatternSyntaxException {
		// ^ 匹配输入字符串开始的位置
		// \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
		// $ 匹配输入字符串结尾的位置
		String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" + "|(18[0-9])|(19[8,9]))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * Checks if is qq.
	 *qq号码必须是数字
	 * @param str the str
	 * @return true, if is qq
	 * @throws PatternSyntaxException the pattern syntax exception
	 */
	public static boolean isQQ(String str) throws PatternSyntaxException {
		String regex = "[1-9][0-9]{4,14}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	
	    /* (非 Javadoc)
	    * 
	    * 对各个按钮的事件应答
	    * @param arg0
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("确认修改") && !name.getText().isEmpty() && !Password.getText().isEmpty()
				&& !Password2.getText().isEmpty() && !telNumber.getText().isEmpty()) {
			System.out.println("actionPerformed(). 确认开始修改");
			boolean flag = false;
			if (name.getText().trim().contains(" ")) {
				JOptionPane.showMessageDialog(null, "名称含有空格！", "修改失败", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (Password.getText().trim().contains(" ")) {
				JOptionPane.showMessageDialog(null, "密码含有空格！", "修改失败", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (!Password.getText().equals(Password2.getText())) {
				JOptionPane.showMessageDialog(null, "密码不一致！", "修改失败", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (!isTel(telNumber.getText().trim())) {
				JOptionPane.showMessageDialog(null, "电话号码格式错误！", "修改失败", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (!qqnumber.getText().trim().isEmpty()) {
				if (!isQQ(qqnumber.getText().trim())) {
					JOptionPane.showMessageDialog(null, "QQ号码有误！", "修改失败", JOptionPane.ERROR_MESSAGE);
					flag = true;
				}
			}
			if ( !flag) {
				String tempfield1 = Password.getText().trim();
				String tempfield2 = name.getText().trim();
				String tempfield3 = telNumber.getText().trim();
				String tempfield4 = "NULL";
				if (!qqnumber.getText().isEmpty()) {
					tempfield4 = qqnumber.getText().trim();
				}
				String sql = "UPDATE Customer SET CPassword='" + tempfield1
						+ "',Cname='" + tempfield2 + "',CtelNumber='" + tempfield3 + "',Cqqnumber='"  + tempfield4 + "' where Customid="+Cusid;
				

				Dao Dao2 = new Dao();
				Dao2.connect();
				int sure = Dao2.executeUpdate(sql);
				if (sure > 0) {
					 JOptionPane.showMessageDialog(null, "修改成功。");
			
							// 建立查询条件
							String sql2="SELECT * from Customer where Customid=";
								sql2 = sql2  + Cusid+";";
							ResultSet rs2 = Dao2.executeQuery1(sql2);
						
							try {
								while(rs2.next()){//生存输出的列表的所需的信息
									Customvectors=new Vector();
									Customvectors.add(rs2.getString("Customid"));
									 Customvectors.add(rs2.getString("Cname"));
									 Customvectors.add(rs2.getString("CtelNumber"));
									 Customvectors.add(rs2.getString("CaddTime"));
									 Customvectors.add(rs2.getString("Cqqnumber"));						
								
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//							Dao2.disconnect();
						
					 setVisible(false);
				}
					
			}
		}
	}

	/**
	 * Return new.
	 *将跟新数据返回（弃用）
	 * @return the vector
	 */
	public  Vector returnNew() {
		return Customvectors;
	}
}
