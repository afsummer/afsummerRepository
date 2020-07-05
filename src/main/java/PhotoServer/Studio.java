package PhotoServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import Dao.Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Studio.
 *影楼详情界面
 * @author afsummer
 * @version  v1.0
 * @date 2020-7-4
 */
public class Studio extends JFrame implements ActionListener {
	
	/** The content pane. */
	public JPanel contentPane;
	
	/** The Studio massage. */
	private static JTextArea StudioMassage;
	
	/** The Stuid 2. */
	private JLabel Stuid2 = null;
	
	/** The Name 2. */
	private JLabel Name2 = null;
	
	/** The adress 2. */
	private JLabel adress2 = null;
	
	/** The People num 2. */
	private JLabel PeopleNum2 = null;
	
	/** The Studio id. */
	private String StudioId = "";
	
	/** The Cus id. */
	private String CusId = "";
	
	/** The table. */
	private JTable table;// 表格
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The student vector 21. */
	private Vector studentVector21 = null;
	
	/** The title vector 3. */
	private Vector titleVector3 = null;
	
	/** The row. */
	private int row = -1;
	
	/** The col. */
	private int col = -1;

	/**
	 * Instantiates a new studio.
	 *
	 * @param StudioId the studio id
	 * @param CusId the cus id
	 */
	public Studio(String StudioId, String CusId) {
		this.StudioId = StudioId;
		this.CusId = CusId;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 300, 560, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setTitle("影楼详情");
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 600, 500);
		panel.setLayout(null);

		try {
			// 建立查询条件
			String sql = "SELECT * from Studio where Studioid=";
			sql = sql + StudioId + ";";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro = new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
			while (rs.next()) {// 生存输出的列表的所需的信息

				Stuid2 = new JLabel(rs.getString("Studioid"));
				Name2 = new JLabel(rs.getString("StudioName"));
				adress2 = new JLabel(rs.getString("Sadress"));
				PeopleNum2 = new JLabel(rs.getString("PeopleNum"));
				StudioMassage = new JTextArea(rs.getString("Smassage"));

			}
			DbPro.disconnect();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
		}

		JLabel ID = new JLabel("影楼ID：");
		ID.setFont(new Font("楷体", Font.PLAIN, 14));
		ID.setBounds(30, 20, 87, 20);

		JLabel Name = new JLabel("影楼名称  ：");
		Name.setFont(new Font("楷体", Font.PLAIN, 14));
		Name.setBounds(30, 60, 87, 20);
		JLabel address = new JLabel("影楼地址：");
		address.setFont(new Font("楷体", Font.PLAIN, 14));
		address.setBounds(30, 100, 87, 20);
		JLabel peoplenum = new JLabel("影楼人数：");
		peoplenum.setFont(new Font("楷体", Font.PLAIN, 14));
		peoplenum.setBounds(30, 140, 100, 20);
		JLabel Studiomassage = new JLabel("影楼信息：");
		Studiomassage.setFont(new Font("楷体", Font.PLAIN, 14));
		Studiomassage.setBounds(250, 20, 87, 20);

		Stuid2.setFont(new Font("楷体", Font.PLAIN, 14));
		Stuid2.setBounds(130, 20, 87, 20);

		Name2.setFont(new Font("楷体", Font.PLAIN, 14));
		Name2.setBounds(130, 60, 87, 20);
		//
		adress2.setFont(new Font("楷体", Font.PLAIN, 14));
		adress2.setBounds(130, 100, 100, 20);
		//
		PeopleNum2.setFont(new Font("楷体", Font.PLAIN, 14));
		PeopleNum2.setBounds(130, 140, 95, 20);

		// 输入栏设置

		StudioMassage.setBounds(320, 20, 200, 180);
		StudioMassage.setLineWrap(true);// 如果内容过长，自动换行，在文本域加上滚动条，水平和垂直滚动条始终出现。
		StudioMassage.setEditable(false);// 文本框不可调试
		// 摄影师表格
		titleVector3 = new Vector();
		studentVector21 = new Vector();// 数组

		titleVector3.add("摄影师ID");
		titleVector3.add("摄影师名称");
		titleVector3.add("电话号码");
		titleVector3.add("注册时间");
		titleVector3.add("地址");
		titleVector3.add("影楼ID");
		titleVector3.add("QQ号码");
		table = new JTable(studentVector21, titleVector3) {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};// 表格的代码
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 禁止自适应，就会开启滚动条
		table.setPreferredScrollableViewportSize(new Dimension(455, 150));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 250, 455, 127);
		// 分别设置水平和垂直滚动条自动出现
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// 水平
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);// 垂直
		// 为表格添加监听器
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {

				row = table.rowAtPoint(event.getPoint());
				col = table.columnAtPoint(event.getPoint());
				System.out.println("行:" + row + "列" + col);// 注意起始索引
				// 获取指定行和列的值
				System.out.println("内容:" + table.getValueAt(row, col).toString());
				if (2 == event.getClickCount()) {// 双击事件
					DoctorMassage doctor = new DoctorMassage(CusId,table.getValueAt(row, 0).toString());
					doctor.setVisible(true);
					System.out.println("双击");// 注意起始索引
					// 获取指定行和列的值
				}
			}
		});
		panel.add(scrollPane);
		try {
			// 建立查询条件
			String sql = "SELECT * from DoctorInfo where StudioId=";
			sql = sql + StudioId + ";";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro2 = new Dao();
			DbPro2.connect();
			ResultSet rs = DbPro2.executeQuery1(sql);
//			 将查询获得的记录数据，转换成适合生成JTable的数据形式
			// 对表格的更新（其实可以不要这一步）
			studentVector21.clear();
			while (rs.next()) {// 生存输出的列表的所需的信息
				Vector v2 = new Vector();
				v2.add(rs.getString("Doctorid"));
				v2.add(rs.getString("Dname"));
				v2.add(rs.getString("DtelNumber"));
				v2.add(rs.getString("Daddtime"));
				v2.add(rs.getString("Daddress"));
				v2.add(rs.getString("Studioid"));
				v2.add(rs.getString("Dqqnumber"));
				studentVector21.add(v2);
			}
			table.updateUI();
			DbPro2.disconnect();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
		}

		// 注册按钮

		// 退出按钮
		JButton button_1 = new JButton("返回");
		button_1.setFont(new Font("楷体", Font.PLAIN, 15));
		button_1.setBounds(400, 430, 120, 23);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		JButton button_2 = new JButton("摄影师详情");
		button_2.setFont(new Font("楷体", Font.PLAIN, 15));
		button_2.setBounds(400, 400, 120, 23);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (row >= 0) {
					DoctorMassage doctor = new DoctorMassage(CusId,table.getValueAt(row, 0).toString());
					doctor.setVisible(true);
				}
			}
		});

		// 页面容器
		contentPane.add(panel);
		// 加入填空框名称
		panel.add(ID);
		panel.add(Stuid2);
		panel.add(Name2);
		panel.add(adress2);
		panel.add(PeopleNum2);
		panel.add(Studiomassage);
		panel.add(peoplenum);
		panel.add(Name);
		panel.add(address);
		// 填空文本框
		panel.add(StudioMassage);

		// 按钮框
		panel.add(button_1);
		panel.add(button_2);
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param arg0
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
//			
//				
	}


}
