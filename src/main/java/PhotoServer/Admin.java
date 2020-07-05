package PhotoServer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import Dao.Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Admin.
 *
 * @author nieming
 * @version  v1.0
 * @date 2020-7-4
 */
public class Admin extends JFrame implements ActionListener {
	
	/** The j L custom info table. */
	// 执行程序时，直接初始化
	JLabel jLCustomInfoTable = null;// 学生信息表；jlabel该标签在其显示区内垂直和水平居中对齐。


	/** The Select query field str. */
	private String SelectQueryFieldStr;// 查询字段//JTextField文本框。JTextField 用来编辑单行的文本
	
	/** The Select query field str 2. */
	private String SelectQueryFieldStr2;
	
	/** The j TFS no. 搜索字段的输入文本框*/
	JTextField jTFSNo = null;
	
	/** The j P 4. 各个组件放置的第二层容器*/
	JPanel jP1, jP2, jP3, jP4 = null;
	
	/** The j P bottom.第一次分为上下2层容器 */
	JPanel jPTop, jPBottom = null;

	/** The Custom J scroll pane 1. */
	private JScrollPane CustomJScrollPane_1;// 滚动窗口
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The Custom J table 1. */
	JTable CustomJTable1 = null;
	
	/** The Custom J table 2. */
	JTable CustomJTable2 = null;
	
	/** The CJ scroll pane 2. */
	private JScrollPane CJScrollPane_2;

	/** The Vector. */
	Vector Vector = null;// vector它实现了动态数组，用于元素数量变化的对象数组。像数组一样
	//title储存表头的属性
	/** The title vector. */
	Vector titleVector = null;
	
	/** The title vector 1. */
	Vector titleVector1 = null;
	
	/** The title vector 2. */
	Vector titleVector2 = null;
	
	/** The title vector 3. */
	Vector titleVector3 = null;
	//massage储存表格内容信息
	/** The massaget vector 1. */
	Vector massagetVector1 = null;
	
	/** The massaget vector 2. */
	Vector massagetVector2 = null;
	
	/** The massaget vector 3. */
	Vector massagetVector3 = null;
	
	/** The row. */
	int row = -1;
	
	/** The col. */
	int col = -1;
	
	/** The j B out. */
	private JButton jBOut;

	/** The button 1. */
	private JButton button_1;
	
	/** The button new. */
	private JButton buttonNew;
	
	/** The Doctor ID. */
	private String DoctorID = "";
	
	/** The where flag. */
	private static int whereFlag = -1;// 鼠标点击的表格类型
	
	/** The table. */
	private JTable table;// 表格


	/**
	 * Instantiates a new admin.
	 * 管理者页面各个组件的初始化
	 */
	// 构造函数
	public Admin() {
		// 创建组件

		jLCustomInfoTable = new JLabel("影楼管理表");

		Vector = new Vector();// 位置数组
		titleVector = new Vector();

		// 定义表头

		massagetVector1 = new Vector();// 表格 数组
		massagetVector2 = new Vector();// 数组
		massagetVector3 = new Vector();// 数组
		titleVector1 = new Vector();
		titleVector2 = new Vector();
		titleVector3 = new Vector();
		
		// 顾客表
		titleVector2.add("顾客ID");
		titleVector2.add("用户名");
		titleVector2.add("电话号码");
		titleVector2.add("注册时间");
		titleVector2.add("QQ号码");

//			studentJTable= new DefaultTableModel(tableTitle, 15);
		CustomJTable2 = new JTable(massagetVector2, titleVector2) {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};// 表格的代码
		CustomJTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 禁止自适应，就会开启滚动条
//		studentJTable2.setPreferredScrollableViewportSize(new Dimension(455, 150));

		CJScrollPane_2 = new JScrollPane(CustomJTable2);
		CJScrollPane_2.setBounds(10, 10, 216, 127);
		// studentJScrollPane.setBounds(0, 0, 462, 83);
		// 分别设置水平和垂直滚动条自动出现
		CJScrollPane_2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		CJScrollPane_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// 为表格添加监听器
		CustomJTable2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				whereFlag = 1;
				row = CustomJTable2.rowAtPoint(event.getPoint());
				col = CustomJTable2.columnAtPoint(event.getPoint());
				// 获取指定行和列的值
			}
		});
		// ***************************

		jP1 = new JPanel();
		jP1.setBounds(212, 5, 70, 25);
		jP2 = new JPanel();
		jP2.setBounds(20, 40, 462, 120);
		jPTop = new JPanel();
		jPTop.setBounds(0, 0, 494, 150);
		jPBottom = new JPanel();
		jPBottom.setBounds(10, 188, 474, 224);

		buttonNew = new JButton("刷新");
		buttonNew.setBounds(300, 5, 70, 25);
		buttonNew.addActionListener(this);

		jP1.add(jLCustomInfoTable, BorderLayout.SOUTH);
		jP2.setLayout(null);
		jPTop.setLayout(null);
		// 第一排顶部表格与信息容器添加
		jPTop.add(jP1);
		jPTop.add(jP2);
		jPTop.add(buttonNew);
//表头影楼信息
		titleVector1.add("影楼ID");
		titleVector1.add("影楼名");
		titleVector1.add("影楼地址");
		titleVector1.add("影楼人数");
		titleVector1.add("影楼信息");

		CustomJTable1 = new JTable(massagetVector1, titleVector1) {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};// 表格的代码
//			studentJTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//禁止自适应，就会开启滚动条
//		studentJTable1.setPreferredScrollableViewportSize(new Dimension(455, 150));
		CustomJTable1.setRowHeight(20);
		CustomJScrollPane_1 = new JScrollPane(CustomJTable1);
		CustomJScrollPane_1.setBounds(0, 0, 455, 100);
		jP2.add(CustomJScrollPane_1);
		// 分别设置水平和垂直滚动条自动出现
		CustomJScrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		CustomJScrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jPBottom.setLayout(null);

		CustomJTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				whereFlag = 3;
				row = CustomJTable1.rowAtPoint(event.getPoint());
				col = CustomJTable1.columnAtPoint(event.getPoint());
				System.out.println("行:" + row + "列" + col);// 注意起始索引
				// 获取指定行和列的值
				System.out.println("内容:" + CustomJTable1.getValueAt(row, col).toString());
				if (2 == event.getClickCount()) {// 双击事件
					
					Studio stu = new Studio(CustomJTable1.getValueAt(row, 0).toString(), "0");
					stu.setVisible(true);
				}
			}
		});
		getContentPane().setLayout(null);
		// 添加顶部容器和底部容器在内容窗格
		getContentPane().add(jPTop);
		getContentPane().add(jPBottom);

		// 第三排容器
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 454, 137);
		panel.setLayout(null);
//			panel.add(studentJScrollPane_2);

		// 第三排拖动式表格窗口
		jPBottom.add(panel);

		titleVector3.add("摄影师ID");
		titleVector3.add("用户名");
		titleVector3.add("电话号码");
		titleVector3.add("注册时间");
		titleVector3.add("QQ号码");
		titleVector3.add("地址");
		titleVector3.add("所属影楼ID");

		// studentVector2
//摄影师表 
		table = new JTable(massagetVector3, titleVector3) {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};// 表格的代码
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 禁止自适应，就会开启滚动条
//		table.setPreferredScrollableViewportSize(new Dimension(455, 150));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(228, 10, 216, 127);
		// studentJScrollPane.setBounds(0, 0, 462, 83);
		// 分别设置水平和垂直滚动条自动出现
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// 水平
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);// 垂直
		// 为表格添加监听器
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				whereFlag = 2;
				row = table.rowAtPoint(event.getPoint());
				col = table.columnAtPoint(event.getPoint());
				System.out.println("行:" + row + "列" + col);// 注意起始索引
				// 获取指定行和列的值
				System.out.println("内容:" + table.getValueAt(row, col).toString());
				if (2 == event.getClickCount()) {// 双击事件
					DoctorMassage DoctorMassage = new DoctorMassage("0", table.getValueAt(row, 0).toString());
					DoctorMassage.setVisible(true);
					// 获取指定行和列的值
				}
			}
		});
		panel.add(scrollPane);
		panel.add(CJScrollPane_2);
		// 第四排按钮组件
//		jBQueryAll = new JButton("预约表打印");
//		jBQueryAll.setBounds(330, 5, 120, 23);
//		jBQueryAll.addActionListener(this);

		jP3 = new JPanel();
		jP3.setBounds(10, 152, 474, 32);
		jP3.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(jP3);
		jP3.setLayout(null);
//		jP3.add(jBQueryAll);
		jP3.setPreferredSize(new Dimension(20, 20));
		
		JButton btnNewButton = new JButton("查找字段");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(30, 5, 86, 23);
		// 第三排按钮组件添加

		NewcomboBox();
		jP3.add(btnNewButton);
		// 第五排按钮组件
		jP4 = new JPanel();
		jP4.setBounds(0, 162, 474, 38);
		jPBottom.add(jP4);
		jP4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jP4.setLayout(null);
		jP4.setPreferredSize(new Dimension(20, 20));

		JButton button = new JButton("删除账号");
		button.addActionListener(this);
		button.setBounds(22, 10, 138, 23);

		button_1 = new JButton("影楼添加");
		button_1.setBounds(312, 10, 138, 23);
		button_1.addActionListener(this);

		jBOut = new JButton("保存并退出");
		jBOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				login frame1 = new login();
				frame1.setVisible(true);
				setVisible(false);
			}
		});
		jBOut.setBounds(182, 10, 102, 23);
		jBOut.addActionListener(this);
		// 第五排按钮组件添加
		jP4.add(button);
		jP4.add(button_1);
		jP4.add(jBOut);

		this.setTitle("管理员页面");
		this.setSize(500, 451);
		this.setLocation(150, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		titleVector1.add("摄影师ID");
		titleVector1.add("用户名");
		titleVector1.add("电话号码");
		titleVector1.add("注册时间");
		titleVector1.add("QQ号码");
		titleVector1.add("地址");
		titleVector1.add("所属影楼ID");
		// 登录用户信息填入第二排表格中

		// 第四排表格填入预约情况

		Renew();
	}

	/**
	 * Custom new.
	 * 顾客信息表更新数据
	 */
	public void CustomNew() {
		try {
			// 建立查询条件
			String sql = "SELECT * from Customer ";

			Dao DbPro = new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
//		 将查询获得的记录数据，转换成适合生成JTable的数据形式
			// 对表格的更新（其实可以不要这一步）
			massagetVector2.clear();

			while (rs.next()) {// 生存输出的列表的所需的信息
				Vector v = new Vector();
				v.add(rs.getString("Customid"));
				v.add(rs.getString("Cname"));
				v.add(rs.getString("CtelNumber"));
				v.add(rs.getString("CaddTime"));
				v.add(rs.getString("Cqqnumber"));
				massagetVector2.add(v);
			}

			CustomJTable2.updateUI();
			DbPro.disconnect();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Studio new.
	 * 影楼表更新数据
	 */
	public void StudioNew() {
		try {
			// 建立查询条件
			String sql = "SELECT * from Studio ";
			Dao DbPro = new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
//	 将查询获得的记录数据，转换成适合生成JTable的数据形式
			// 对表格的更新（其实可以不要这一步）
			massagetVector1.clear();

			while (rs.next()) {// 生存输出的列表的所需的信息
				Vector v = new Vector();
				v.add(rs.getString("Studioid"));
				v.add(rs.getString("StudioName"));
				v.add(rs.getString("Sadress"));
				v.add(rs.getString("PeopleNum"));
				v.add(rs.getString("Smassage"));
				massagetVector1.add(v);
			}

			CustomJTable1.updateUI();
			DbPro.disconnect();
		} catch (SQLException sqle) {
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Doctor new.
	 * 摄影师表更新数据
	 */
	public void DoctorNew() {
		try {
			// 建立查询条件
			String sql = "SELECT * from DoctorInfo";

			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro2 = new Dao();
			DbPro2.connect();
			ResultSet rs = DbPro2.executeQuery1(sql);
//	 将查询获得的记录数据，转换成适合生成JTable的数据形式
			// 对表格的更新（其实可以不要这一步）
			massagetVector3.clear();
			while (rs.next()) {// 生存输出的列表的所需的信息

				Vector v = new Vector();
				v.add(rs.getString("Doctorid"));
				v.add(rs.getString("Dname"));
				v.add(rs.getString("DtelNumber"));
				v.add(rs.getString("Daddtime"));
				v.add(rs.getString("Dqqnumber"));
				v.add(rs.getString("Daddress"));
				v.add(rs.getString("Studioid"));

				massagetVector3.add(v);
			}
			table.updateUI();
			DbPro2.disconnect();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Renew.
	 * 3个表数据更新
	 */
	public void Renew() {
		DoctorNew();
		CustomNew();
		StudioNew();
	}

	/**
	 * Find.
	 * 搜索功能
	 */
	public void find() {
		String str = new String();// 身份字段
		String str2 = new String();// 查找条件
		int flag = 0;
		if (SelectQueryFieldStr.equals("摄影师")) {
			str = "DoctorInfo";
			flag = 1;
			if (SelectQueryFieldStr2.equals("用户名"))
				str2 = "Dname";
			else if (SelectQueryFieldStr2.equals("摄影师ID"))
				str2 = "Doctorid";
			else if (SelectQueryFieldStr2.equals("影楼ID"))
				str2 = "Studioid";
		}

		else if (SelectQueryFieldStr.equals("顾客")) {
			str = "Customer";
			flag = 2;
			if (SelectQueryFieldStr2.equals("用户名"))
				str2 = "Cname";
			else if (SelectQueryFieldStr2.equals("顾客ID"))
				str2 = "Customid";

		}

		else if (SelectQueryFieldStr.equals("影楼")) {
			str = "Studio";
			flag = 3;
			if (SelectQueryFieldStr2.equals("影楼名"))
				str2 = "StudioName";
			else if (SelectQueryFieldStr2.equals("影楼ID"))
				str2 = "Studioid";
		}
		String sql = "SELECT * from " + str + " where " + str2 + " LIKE '%";
		sql = sql + jTFSNo.getText() + "%';";
		System.out.println("queryProcess(). sql = " + sql);
		Dao DbPro = new Dao();
		DbPro.connect();
		ResultSet rs = DbPro.executeQuery1(sql);
		// 摄影师
		if (flag == 1) {
			massagetVector3.clear();
			try {
				while (rs.next()) {// 生存输出的列表的所需的信息
					Vector v = new Vector();
					v.add(rs.getString("Doctorid"));
					v.add(rs.getString("Dname"));
					v.add(rs.getString("DtelNumber"));
					v.add(rs.getString("Daddtime"));
					v.add(rs.getString("Dqqnumber"));
					v.add(rs.getString("Daddress"));
					v.add(rs.getString("Studioid"));
					massagetVector3.add(v);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			table.updateUI();
		}
		// 顾客
		if (flag == 2) {
			massagetVector2.clear();
			try {
				while (rs.next()) {// 生存输出的列表的所需的信息
					Vector v = new Vector();
					v.add(rs.getString("Customid"));
					v.add(rs.getString("Cname"));
					v.add(rs.getString("CtelNumber"));
					v.add(rs.getString("CaddTime"));
					v.add(rs.getString("Cqqnumber"));
					massagetVector2.add(v);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			CustomJTable2.updateUI();
		}
		// 影楼
		if (flag == 3) {
			massagetVector1.clear();
			try {
				while (rs.next()) {// 生存输出的列表的所需的信息
					Vector v = new Vector();
					v.add(rs.getString("Studioid"));
					v.add(rs.getString("StudioName"));
					v.add(rs.getString("Sadress"));
					v.add(rs.getString("PeopleNum"));
					v.add(rs.getString("Smassage"));
					massagetVector1.add(v);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			CustomJTable1.updateUI();
		}
		DbPro.disconnect();
	}

	/**
	 * Newcombo box.
	 * 字段搜索下拉框制作
	 */
	public void NewcomboBox() {
		jTFSNo = new JTextField();
		jTFSNo.setColumns(20);
		jTFSNo.setBounds(275, 5, 150, 23);
		// 顾客字段选择下拉栏
		JComboBox<String> comboBox_Cus = new JComboBox<String>();
		comboBox_Cus.addItem(" ");
		comboBox_Cus.addItem("顾客ID");
		comboBox_Cus.addItem("用户名");
		// 下拉框的制作
		comboBox_Cus.addItemListener(new ItemListener() {// 下拉框事件监听
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr2 = (String) event.getItem();// 选中的文字
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox_Cus.setSelectedIndex(0);
		comboBox_Cus.setBounds(190, 5, 80, 23);
		// 摄影师字段查询
		JComboBox<String> comboBox_Doctor = new JComboBox<String>();
		comboBox_Doctor.addItem(" ");
		comboBox_Doctor.addItem("摄影师ID");
		comboBox_Doctor.addItem("用户名");
		comboBox_Doctor.addItem("影楼ID");
		// 下拉框的制作
		comboBox_Doctor.addItemListener(new ItemListener() {// 下拉框事件监听
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr2 = (String) event.getItem();// 选中的文字
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox_Doctor.setSelectedIndex(0);
		comboBox_Doctor.setBounds(190, 5, 80, 23);
		// 影楼字段查询
		JComboBox<String> comboBox_Studio = new JComboBox<String>();
		comboBox_Studio.addItem(" ");
		comboBox_Studio.addItem("影楼ID");
		comboBox_Studio.addItem("影楼名");
		// 下拉框的制作
		comboBox_Studio.addItemListener(new ItemListener() {// 下拉框事件监听
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr2 = (String) event.getItem();// 选中的文字
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox_Studio.setSelectedIndex(0);
		comboBox_Studio.setBounds(190, 5, 80, 23);
		// 查找身份选择下拉框
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("摄影师");
		comboBox.addItem("顾客");
		comboBox.addItem("影楼");
		// 下拉框的制作
		comboBox.addItemListener(new ItemListener() {// 下拉框事件监听
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr = (String) event.getItem();// 选中的文字
					if (SelectQueryFieldStr.equals("摄影师")) {
						comboBox_Studio.setVisible(false);
						comboBox_Doctor.setVisible(true);
						comboBox_Cus.setVisible(false);
					}
					else if (SelectQueryFieldStr.equals("顾客")) {
						comboBox_Studio.setVisible(false);
						comboBox_Doctor.setVisible(false);
						comboBox_Cus.setVisible(true);
					}
					else if (SelectQueryFieldStr.equals("影楼")) {
						comboBox_Studio.setVisible(true);
						comboBox_Doctor.setVisible(false);
					comboBox_Cus.setVisible(false);
					}
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(120, 5, 70, 23);
		jP3.add(comboBox);
		jP3.add(comboBox_Cus);
		jP3.add(comboBox_Doctor);
		jP3.add(comboBox_Studio);
		jP3.add(jTFSNo);
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//	 将查询获得的记录数据，转换成适合生成JTable的数据形式
		// 对表格的更新（其实可以不要这一步）
		if (e.getActionCommand().equals("查找字段")) {
			find();
		} else if (e.getActionCommand().equals("删除账号") && (row >= 0)) {
			System.out.println("actionPerformed().删除账号");
			Delete();
			Renew();
		} else if (e.getActionCommand().equals("影楼添加")) {
			register_Studio register = new register_Studio();
			register.setVisible(true);
		} else if (e.getActionCommand().equals("刷新")) {
			Renew();
		}
	}

	/**
	 * Delete.
	 */
	public void Delete() {
		String sql;
		String sql2;
		String sql3;
		Dao Dao1 = new Dao();
		Dao1.connect();
		if (whereFlag == 0) {
			// 无
		} else if (whereFlag == 1) {
			// 顾客
			sql = "DELETE from Customer where Customid=" + CustomJTable2.getValueAt(row, 0).toString();
			sql2="DELETE from Evaluation where Customid=" + CustomJTable2.getValueAt(row, 0).toString();
			int n = JOptionPane.showConfirmDialog(null, "确定要删除顾客吗?", "删除顾客", JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				Dao1.executeUpdate(sql);
				Dao1.executeUpdate(sql2);
			}
		} else if (whereFlag == 2) {
			// 摄影师
			sql = "DELETE from DoctorInfo where Doctorid=" + table.getValueAt(row, 0).toString();
			sql2="DELETE from Evaluation where Doctorid=" + table.getValueAt(row, 0).toString();
			sql3="DELETE from Comment where Doctorid=" + table.getValueAt(row, 0).toString();
			int n = JOptionPane.showConfirmDialog(null, "确定要删除摄影师吗?", "删除摄影师", JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				Dao1.executeUpdate(sql);
				Dao1.executeUpdate(sql2);
				Dao1.executeUpdate(sql3);
			}
		} else if (whereFlag == 3) {
			// 影楼
			int n = JOptionPane.showConfirmDialog(null, "删除影楼会同时删除影楼记录的摄影师账号，确定要删除吗?", "删除影楼",
					JOptionPane.YES_NO_OPTION);
			sql = "DELETE from Studio where Studioid=" + CustomJTable1.getValueAt(row, 0).toString();
			sql2 = "DELETE from DoctorInfo where Studioid=" + CustomJTable1.getValueAt(row, 0).toString();
			if (n == 0) {
				Dao1.executeUpdate(sql);
				Dao1.executeUpdate(sql2);
			}
		}
		Dao1.disconnect();
	}
}