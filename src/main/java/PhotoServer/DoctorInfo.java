package PhotoServer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import Dao.Dao;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorInfo.
 *摄影师主页面
 * @author afsummer
 * @version  v1.0
 * @date 2020-7-4
 */
public class DoctorInfo extends JFrame implements ActionListener {
	
	/** The j L custom info table. */
	// 执行程序时，直接初始化
	JLabel jLCustomInfoTable = null;// 学生信息表；jlabel该标签在其显示区内垂直和水平居中对齐。
	
/** The j B query all. */
JButton jBQueryAll = null;// 查询所有记录


	/** The j P 4. */
	JPanel jP1, jP2, jP3, jP4 = null;
	
	/** The j P bottom. */
	JPanel jPTop, jPBottom = null;

	/** The Custom J scroll pane 1. */
	private JScrollPane CustomJScrollPane_1;// 滚动窗口
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The Custom J table 1. */
	JTable CustomJTable1 = null;
	

	/** The massage vector 1. */
	Vector massageVector1 = null;
	
	/** The massage vector 2. */
	Vector massageVector2 = null;
	
	/** The title vector 1. */
	// vector它实现了动态数组，用于元素数量变化的对象数组。像数组一样
	Vector titleVector1 = null;
	
	/** The title vector 3. */
	Vector titleVector3 = null;

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
	
	/** The table. */
	private JTable table;// 表格
	
	/** The Amend. */
	private Doctor_amend Amend;
	
	/** The file excel. */
	private File fileExcel;
	
	/**
	 * Instantiates a new doctor info.
	 *主页面组件初始化
	 * @param ID the id
	 */
	// 构造函数
	public DoctorInfo (String ID) {
		// 创建组件
		fileExcel= new File("C:\\Users\\afsummer\\Desktop\\test.xls");//文件导出Excel目录
		DoctorID = ID;
		jLCustomInfoTable = new JLabel("个人信息表");




		// 定义表头

		massageVector1 = new Vector();// 表格 数组

		massageVector2 = new Vector();// 数组
		titleVector1 = new Vector();

		titleVector3 = new Vector();
				// 定义表头
		jP1 = new JPanel();
		jP1.setBounds(212, 5, 70, 25);
		jP2 = new JPanel();
		jP2.setBounds(20, 40, 462, 83);
		jPTop = new JPanel();
		jPTop.setBounds(0, 0, 494, 142);
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
//表头信息
		titleVector1.add("摄影师ID");
		titleVector1.add("用户名");
		titleVector1.add("电话号码");
		titleVector1.add("注册时间");
		titleVector1.add("QQ号码");
		titleVector1.add("地址");
		titleVector1.add("所属影楼ID");
		CustomJTable1 = new JTable(massageVector1, titleVector1){
			public boolean isCellEditable(int rowIndex,int ColIndex){
				return false;
			}
		};// 表格的代码
//			studentJTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//禁止自适应，就会开启滚动条
//		studentJTable1.setPreferredScrollableViewportSize(new Dimension(455, 150));
		CustomJScrollPane_1 = new JScrollPane(CustomJTable1);
		CustomJScrollPane_1.setBounds(0, 0, 455, 43);
		jP2.add(CustomJScrollPane_1);
		// 分别设置水平和垂直滚动条自动出现
		CustomJScrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		CustomJScrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jPBottom.setLayout(null);

		
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

		titleVector3.add("记录ID");
		titleVector3.add("预约顾客ID");
		titleVector3.add("预约摄影师ID");
		titleVector3.add("记录时间");
		titleVector3.add("预约情况");

		// studentVector2
//预约情况表 
		table = new JTable(massageVector2, titleVector3){
			public boolean isCellEditable(int rowIndex,int ColIndex){
				return false;
			}
		};// 表格的代码
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 禁止自适应，就会开启滚动条
		table.setPreferredScrollableViewportSize(new Dimension(455, 150));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 10, 455, 127);
		// studentJScrollPane.setBounds(0, 0, 462, 83);
		// 分别设置水平和垂直滚动条自动出现
//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//水平
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//垂直
		// 为表格添加监听器
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
			     
         		row=table.rowAtPoint(event.getPoint());
				col=table.columnAtPoint(event.getPoint());
				System.out.println("行:"+row+"列"+col);//注意起始索引
				//获取指定行和列的值
				System.out.println("内容:"+table.getValueAt(row,col).toString());
				if(2==event.getClickCount()){//双击事件
					EvalMassage_Doctor EvalMassages = new EvalMassage_Doctor(table.getValueAt(row, 0).toString());
					EvalMassages.setVisible(true);
					System.out.println("双击");//注意起始索引
					//获取指定行和列的值
				}				
			}
		});
		panel.add(scrollPane);

		// 第四排按钮组件
		jBQueryAll = new JButton("预约表打印");
		jBQueryAll.setBounds(330, 5, 120, 23);
		jBQueryAll.addActionListener(this);

		jP3 = new JPanel();
		jP3.setBounds(10, 152, 474, 32);
		jP3.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(jP3);
		jP3.setLayout(null);
		jP3.add(jBQueryAll);
		jP3.setPreferredSize(new Dimension(20, 20));

		
		JButton btnNewButton = new JButton("摄影师评价");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(30, 5, 105, 23);
		// 第三排按钮组件添加

		
		jP3.add(btnNewButton);
		// 第五排按钮组件
		jP4 = new JPanel();
		jP4.setBounds(0, 162, 474, 38);
		jPBottom.add(jP4);
		jP4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jP4.setLayout(null);
		jP4.setPreferredSize(new Dimension(20, 20));

		JButton button = new JButton("预约情况详情查询");
		button.addActionListener(this);
		button.setBounds(22, 10, 138, 23);

		button_1 = new JButton("个人信息修改");
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

		this.setTitle("摄影师页面");
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
		reNew();
		//第四排表格填入预约情况
		 
	}

/**
 * 个人信息跟新
 * ID new.
 */
public void IDNew() {
	try {
		// 建立查询条件
		String sql = "SELECT * from DoctorInfo where Doctorid=";
		sql = sql + DoctorID + ";";
		System.out.println("queryProcess(). sql = " + sql);
		Dao DbPro = new Dao();
		DbPro.connect();
		ResultSet rs = DbPro.executeQuery1(sql);
//	 将查询获得的记录数据，转换成适合生成JTable的数据形式
		// 对表格的更新（其实可以不要这一步）
		massageVector1.clear();
		while (rs.next()) {// 生存输出的列表的所需的信息
			Vector v = new Vector();
			v.add(rs.getString("Doctorid"));
			v.add(rs.getString("Dname"));
			v.add(rs.getString("DtelNumber"));
			v.add(rs.getString("Daddtime"));
			v.add(rs.getString("Dqqnumber"));
			v.add(rs.getString("Daddress"));
			v.add(rs.getString("Studioid"));
			massageVector1.add(v);
		}
		CustomJTable1.updateUI();
		//顾客
		
		
		DbPro.disconnect();
	} catch (SQLException sqle) {
		System.out.println("sqle = " + sqle);
		JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
	}
}

/**
 * Eva new.
 * 预约表更新
 */
public void EvaNew() {
	try {
		// 建立查询条件
		String sql = "SELECT * from Evaluation where Doctorid=";
		sql = sql + DoctorID + ";";
		System.out.println("queryProcess(). sql = " + sql);
		Dao DbPro2 = new Dao();
		DbPro2.connect();
		ResultSet rs = DbPro2.executeQuery1(sql);
//	 将查询获得的记录数据，转换成适合生成JTable的数据形式
		// 对表格的更新（其实可以不要这一步）
		massageVector2.clear();
		while (rs.next()) {// 生存输出的列表的所需的信息
			Vector v2 = new Vector();
			v2.add(rs.getString("EvaId"));
			v2.add(rs.getString("Customid"));
			v2.add(rs.getString("Doctorid"));
			v2.add(rs.getString("EaddTime"));
			if(rs.getString("EvaFlag").equals("-1"))
			v2.add("预约取消");
			else if(rs.getString("EvaFlag").equals("0"))
				v2.add("预约中");
			else if(rs.getString("EvaFlag").equals("1"))
				v2.add("预约成功");
			massageVector2.add(v2);
		}
		table.updateUI();
		DbPro2.disconnect();
	} catch (SQLException sqle) {
		JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
	}
}
	
	/**
	 * Re new.
	 * 所有表更新
	 */
	public void reNew() {
		IDNew();
		EvaNew();
	}
	
	/**
	 * Export excel.
	 * 预约表打印到相应Excel表格中
	 */
	public void exportExcel() {
		WritableWorkbook workBook = null;
		try {
			// 导出的文件fileExcel
	
			// 创建WritableWorkbook
			workBook = Workbook.createWorkbook(fileExcel);
			// 创建sheet
			WritableSheet sheet = workBook.createSheet("预约表信息", 0);// 创建一个学生信息的sheet页

			// 构造excel头信息
			/*
			 * 参数1：表示该Label在excel中x坐标 参数2：表示该Label在excel中y坐标 参数3：表示该label的内容
			 */
			for (int i = 0; i < titleVector3.size(); i++) {
				Label Labels = new Label(i, 0, titleVector3.get(i).toString());
				sheet.addCell(Labels);
			}

			try {
				// 建立查询条件
				String sql = "SELECT * from Evaluation where Doctorid=";
				sql = sql + DoctorID + ";";
				System.out.println("queryProcess(). sql = " + sql);
				Dao DbPro2 = new Dao();
				DbPro2.connect();
				ResultSet rs = DbPro2.executeQuery1(sql);
				int j = 1;
				while (rs.next()) {
					Label EvaLabels2 = new Label(0, j, rs.getString("EvaId"));
					Label CusLabels2 = new Label(1, j, rs.getString("Customid"));
					Label DocLabels2 = new Label(2, j, rs.getString("Doctorid"));
					Label EadLabels2 = new Label(3, j, rs.getString("EaddTime"));
					Label flagLabels2 = null;
					if (rs.getString("EvaFlag").equals("-1"))
						flagLabels2 = new Label(4, j, "预约取消");
					else if (rs.getString("EvaFlag").equals("0"))
						flagLabels2 = new Label(4, j, "预约中");
					else if (rs.getString("EvaFlag").equals("1"))
						flagLabels2 = new Label(4, j, "预约成功");
					sheet.addCell(EvaLabels2);
					sheet.addCell(CusLabels2);
					sheet.addCell(DocLabels2);
					sheet.addCell(EadLabels2);
					sheet.addCell(flagLabels2);
					j++;
				}
//		 将查询获得的记录数据，转换成适合生成JTable的数据形式
				// 对表格的更新（其实可以不要这一步）
				DbPro2.disconnect();
			} catch (SQLException sqle) {
				JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
			}

			// 查询数据库表数据

			// 写入数据
			workBook.write();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				workBook.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 按钮组件事件应答
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("预约情况详情查询") && (row >= 0)) {
			System.out.println("actionPerformed(). 预约情况详情查询");
			EvalMassage_Doctor EvalMassages = new EvalMassage_Doctor(table.getValueAt(row, 0).toString());
			EvalMassages.setVisible(true);

		} else if (e.getActionCommand().equals("摄影师评价")) {
			DoctorMassage Mymassage=new DoctorMassage("-1",DoctorID);
			Mymassage.setVisible(true);

		} else if (e.getActionCommand().equals("个人信息修改")) {
			System.out.println("actionPerformed(). 个人信息修改");
			Amend = new Doctor_amend(DoctorID);
			Amend.setVisible(true);

		} else if (e.getActionCommand().equals("刷新")) {
			reNew();
		}else if (e.getActionCommand().equals("预约表打印")) {
			exportExcel();
		}

	}



}
