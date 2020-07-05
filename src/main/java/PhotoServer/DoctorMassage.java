package PhotoServer;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Dao.Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class DoctorMassage.
 *摄影师信息及评论页面（通用）
 * @author afsummer
 * @version  v1.0
 * @date 2020-7-4
 */
public class DoctorMassage extends JFrame implements ActionListener {
	
	/** The content pane. */
	public JPanel contentPane;
	/** The Studio 2. */
	private JLabel Studio2;
	/** The doctorid 2. */
	private JLabel doctorid2 = null;
	/** The Name 2. */
	private JLabel Name2 = null;
	
	/** The adress 2. */
	private JLabel adress2 = null;
	
	/** The time 2. */
	private JLabel time2 = null;
	
	/** The Doctor ID. */
	private String DoctorID = "";
	
	/** The Cus id. */
	private String CusId = "";
	
	/** The Customvectors. */
	private Vector Customvectors = null;
	
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
	 * Instantiates a new doctor massage.
	 *摄影师信息详情界面组件初始化
	 * @param CusId the cus id
	 * @param DoctorId the doctor id
	 */
	public DoctorMassage(String CusId, String DoctorId) {
		this.DoctorID = DoctorId;
		this.CusId = CusId;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 300, 560, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setTitle("摄影师详情");
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 600, 500);
		panel.setLayout(null);

		reNewDoc();

		JLabel ID = new JLabel("摄影师：");
		ID.setFont(new Font("楷体", Font.PLAIN, 14));
		ID.setBounds(30, 20, 87, 20);

		JLabel Name = new JLabel("影楼名称  ：");
		Name.setFont(new Font("楷体", Font.PLAIN, 14));
		Name.setBounds(30, 60, 87, 20);
		JLabel address = new JLabel("影楼地址：");
		address.setFont(new Font("楷体", Font.PLAIN, 14));
		address.setBounds(30, 100, 87, 20);
		JLabel peoplenum = new JLabel("注册时间：");
		peoplenum.setFont(new Font("楷体", Font.PLAIN, 14));
		peoplenum.setBounds(30, 140, 100, 20);
		JLabel Studiomassage = new JLabel("影楼ID：");
		Studiomassage.setFont(new Font("楷体", Font.PLAIN, 14));
		Studiomassage.setBounds(250, 20, 87, 20);

		doctorid2.setFont(new Font("楷体", Font.PLAIN, 14));
		doctorid2.setBounds(130, 20, 87, 20);

		Name2.setFont(new Font("楷体", Font.PLAIN, 14));
		Name2.setBounds(130, 60, 87, 20);
//
		adress2.setFont(new Font("楷体", Font.PLAIN, 14));
		adress2.setBounds(130, 100, 150, 20);
//
		time2.setFont(new Font("楷体", Font.PLAIN, 14));
		time2.setBounds(130, 140, 95, 20);

		Studio2.setFont(new Font("楷体", Font.PLAIN, 14));
		Studio2.setBounds(320, 20, 80, 20);
		// 输入栏设置

		// 摄影师表格
		titleVector3 = new Vector();
		studentVector21 = new Vector();// 数组

		titleVector3.add("评论ID");
		titleVector3.add("评论家ID");
		titleVector3.add("摄影师ID");
		titleVector3.add("评价时间");
		titleVector3.add("评价内容");

		table = new JTable(studentVector21, titleVector3) {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};// 表格的代码
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
					Customer_comment comments = new Customer_comment(table.getValueAt(row, 0).toString());
					comments.setVisible(true);
					// 获取指定行和列的值
				}
			}
		});

		panel.add(scrollPane);
		reNew();

		// 注册按钮
		JButton button_2 = new JButton("预约请求");
		button_2.setFont(new Font("楷体", Font.PLAIN, 15));
		button_2.setBounds(120, 400, 100, 23);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("预约请求");
				SetEvalMassage test = new SetEvalMassage(CusId, DoctorID);
				test.setVisible(true);
			}
		});
		JButton button_3 = new JButton("评论填写");
		button_3.setFont(new Font("楷体", Font.PLAIN, 15));
		button_3.setBounds(20, 400, 100, 23);
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Customer_setComment setComment = new Customer_setComment(CusId, DoctorID);
				setComment.setVisible(true);

			}
		});
		// 管理员删除按键
		JButton button_4 = new JButton("删除评论");
		button_4.setFont(new Font("楷体", Font.PLAIN, 15));
		button_4.setBounds(400, 430, 120, 23);
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				DeleteComment(0);
				reNew();
			}
		});
		JButton button_5 = new JButton("清空评论");
		button_5.setFont(new Font("楷体", Font.PLAIN, 15));
		button_5.setBounds(400, 20, 100, 23);
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				DeleteComment(1);
				reNew();
			}
		});
		// 退出按钮
		JButton button_1 = new JButton("返回");
		button_1.setFont(new Font("楷体", Font.PLAIN, 15));
		button_1.setBounds(400, 400, 120, 23);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});

		// 页面容器
		contentPane.add(panel);
		// 加入填空框名称
		panel.add(ID);
		panel.add(doctorid2);
		panel.add(Name2);
		panel.add(adress2);
		panel.add(time2);
		panel.add(Studiomassage);
		panel.add(peoplenum);
		panel.add(Name);
		panel.add(address);
		// 填空文本框
		panel.add(Studio2);

		// 按钮框
		panel.add(button_1);// 返回
		panel.add(button_2);
		panel.add(button_3);
		panel.add(button_4);
		panel.add(button_5);
		//判断查看者身份
		if (CusId.equals("0")) {
			button_2.setVisible(false);
			button_3.setVisible(false);
		} else if(CusId.equals("-1")){
			button_2.setVisible(false);
			button_3.setVisible(false);
			button_4.setVisible(false);
			button_5.setVisible(false);
		}else{
			button_4.setVisible(false);
			button_5.setVisible(false);
		}
	}

	/**
	 * Delete comment.
	 *删除评论（管理者使用）
	 * @param deleteall the deleteall
	 */
	public void DeleteComment(int deleteall) {
		String sql;
		Dao Dao1 = new Dao();
		Dao1.connect();
		if (deleteall == 1) {
			sql = "DELETE from Comment where Doctorid=" +DoctorID;
			int n = JOptionPane.showConfirmDialog(null, "确定要删除所有评论吗?", "删除评论", JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				Dao1.executeUpdate(sql);
			}
		} else {

			sql = "DELETE from Comment where comId=" + table.getValueAt(row, 0).toString();
			int n = JOptionPane.showConfirmDialog(null, "确定要删除评论吗?", "删除评论", JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				Dao1.executeUpdate(sql);
			}
		}
		Dao1.disconnect();
	}

	/**
	 * Re new.
	 * 评论表更新
	 */
	public void reNew() {
		try {
			// 建立查询条件
			String sql = "SELECT * from Comment where Doctorid=";
			sql = sql + DoctorID + ";";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro2 = new Dao();
			DbPro2.connect();
			ResultSet rs = DbPro2.executeQuery1(sql);
//	 将查询获得的记录数据，转换成适合生成JTable的数据形式
			// 对表格的更新（其实可以不要这一步）
			studentVector21.clear();
			while (rs.next()) {// 生存输出的列表的所需的信息
				Vector v2 = new Vector();
				v2.add(rs.getString("comId"));
				v2.add(rs.getString("Customid"));
				v2.add(rs.getString("Doctorid"));
				v2.add(rs.getString("Caddtime"));
				v2.add(rs.getString("Comment"));
				studentVector21.add(v2);
			}
			table.updateUI();
			DbPro2.disconnect();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Re new doc.
	 * 摄影师信息更新
	 */
	public void reNewDoc() {
		try {
			// 建立查询条件
			String sql = "SELECT * from DoctorInfo where Doctorid=";
			sql = sql + DoctorID + ";";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro = new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
			while (rs.next()) {// 生存输出的列表的所需的信息

				doctorid2 = new JLabel(rs.getString("Doctorid"));
				Name2 = new JLabel(rs.getString("Dname"));
				adress2 = new JLabel(rs.getString("Daddress"));
				time2 = new JLabel(rs.getString("Daddtime"));
				Studio2 = new JLabel(rs.getString("Studioid"));

			}
			DbPro.disconnect();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
		}
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
