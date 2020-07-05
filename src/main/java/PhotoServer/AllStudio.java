package PhotoServer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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


    // TODO: Auto-generated Javadoc
/**
     * The Class AllStudio.
     *查看所有影楼的信息表格
     * @author afsummer
     * @date 2020年7月3日
     */
    
public class AllStudio extends JFrame implements ActionListener {
	
	/** The j L studio info table. */
	// 执行程序时，直接初始化
	JLabel jLStudioInfoTable = null;// 学生信息表；jlabel该标签在其显示区内垂直和水平居中对齐。
	
	/** The jp 2. */
	JPanel  jp1, jp2 = null;
	
	/** The j P bottom. */
	JPanel jPTop, jPBottom = null;

/** The studio J scroll pane 1. */
//	private JScrollPane studentJScrollPane_2;
	private JScrollPane studioJScrollPane_1;// 滚动窗口
	
	/** The studio J table 1. */
	JTable studioJTable1 = null;
	
	/** The student vector 1. */
	Vector studentVector1 = null;
	
	/** The title vector 1. */
	// vector它实现了动态数组，用于元素数量变化的对象数组。像数组一样
	Vector titleVector1 = null;

	/** The row. */
	int row = -1;
	
	/** The col. */
	int col = -1;
	
	/** The j B out. */
	private JButton jBOut;
	
	/** The button 1. */
	private JButton button_1;
	
	/** The Cus ID. */
	String CusID = "";

	/**
	 * Instantiates a new all studio.
	 *页面组件初始化
	 * @param ID the id
	 */
	// 构造函数
	public AllStudio(String ID) {
		// 创建组件
		CusID = ID;
		jLStudioInfoTable = new JLabel("影楼信息表");
		jLStudioInfoTable.setFont(new Font("楷体", Font.PLAIN, 20));
		jLStudioInfoTable.setBounds(300,10,120,20);
		// 定义表头

		studentVector1 = new Vector();// 表格 数组
		titleVector1 = new Vector();
		// 定义表头


		jp1 = new JPanel();
		jp1.setBounds(20, 40, 650, 510);
		jPTop = new JPanel();
		jPTop.setBounds(0, 0, 700, 500);
		jPBottom = new JPanel();
		jPBottom.setBounds(10, 500, 700, 150);


		
		jp1.setLayout(null);
		jPTop.setLayout(null);
		// 第一排顶部表格与信息容器添加
		jPTop.add(jLStudioInfoTable);
		jPTop.add(jp1);
		

		titleVector1.add("影楼ID");
		titleVector1.add("影楼名称");
		titleVector1.add("影楼地址");
		titleVector1.add("影楼人数");
		titleVector1.add("影楼信息");

		studioJTable1 = new JTable(studentVector1, titleVector1){
			public boolean isCellEditable(int rowIndex,int ColIndex){
				return false;
			}
		};// 表格的代码
		studioJTable1.setRowHeight(50);//设置表格高度
//			studioJTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//禁止自适应，就会开启滚动条
	studioJScrollPane_1 = new JScrollPane(studioJTable1);
		studioJScrollPane_1.setBounds(0, 0, 650, 450);
		jp1.add(studioJScrollPane_1);
		// 分别设置水平和垂直滚动条自动出现
		studioJScrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		studioJScrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jPBottom.setLayout(null);

		studioJTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
			     
         		 row=studioJTable1.rowAtPoint(event.getPoint());
				 col=studioJTable1.columnAtPoint(event.getPoint());
				System.out.println("行:"+row+"列"+col);//注意起始索引
				//获取指定行和列的值
				System.out.println("内容:"+studioJTable1.getValueAt(row,col).toString());
				if(2==event.getClickCount()){//双击事件
					Studio studio=new Studio(studioJTable1.getValueAt(row,0).toString(),CusID);
					studio.setVisible(true);
				
					//获取指定行和列的值
				}				
			}
		});
		this.getContentPane().setLayout(null);
		// 添加顶部容器和底部容器在内容窗格
		this.getContentPane().add(jPTop);
		this.getContentPane().add(jPBottom);
		// 第三排按钮组件添加
		// 第五排按钮组件
		jp2 = new JPanel();
		jp2.setBounds(0, 0, 670, 80);
		jPBottom.add(jp2);
		jp2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jp2.setLayout(null);
		jp2.setPreferredSize(new Dimension(20, 20));

		button_1 = new JButton("影楼详细查询");
		button_1.setBounds(180, 10, 138, 30);
		button_1.addActionListener(this);

		jBOut = new JButton("返回");
		jBOut.setBounds(350, 10, 138, 30);
		jBOut.addActionListener(this);
		jBOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				setVisible(false);
			}
		});
		
		// 第五排按钮组件添加
		jp2.add(button_1);
		jp2.add(jBOut);

		this.setTitle("影楼页面");
		this.setSize(700, 650);
		this.setLocation(600, 150);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		// 登录用户信息填入第二排表格中
		try {
			// 建立查询条件
			String sql = "SELECT * from Studio ";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro = new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
//		 将查询获得的记录数据，转换成适合生成JTable的数据形式
			// 对表格的更新（其实可以不要这一步）
			studentVector1.clear();
			while (rs.next()) {// 生存输出的列表的所需的信息
				Vector v = new Vector();
				v.add(rs.getString("Studioid"));
				v.add(rs.getString("StudioName"));
				v.add(rs.getString("Sadress"));
				v.add(rs.getString("PeopleNum"));
				v.add(rs.getString("Smassage"));
				studentVector1.add(v);
			}
			studioJTable1.updateUI();
			DbPro.disconnect();
		} catch (SQLException sqle) {
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	    /* (非 Javadoc)
	    * 对按钮的事件应答
	    * 
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("影楼详细查询")) {
			System.out.println("actionPerformed(). 影楼详细查询");
			if(row>=0) {
			Studio studio=new Studio(studioJTable1.getValueAt(row,0).toString(),CusID);
			studio.setVisible(true);
			
			}
//			Reward();

		}  

	}
}