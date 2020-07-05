package PhotoServer;

import java.awt.Color;
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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Dao.Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Customer_setComment.
 *顾客评价窗口
 * @author afsummer
 * @version  v1.0
 * @date 2020-7-4
 */
//评论详情表
public class Customer_setComment extends JFrame implements ActionListener {
	
	/** The content pane. */
	public JPanel contentPane;
	
	/** The Eval massage. */
	private static JTextArea EvalMassage;

/** The Customid 2. */
//	private JLabel Evaid2=null;
	private JLabel Customid2 = null;
	
	/** The Doctorid 2. */
	private JLabel Doctorid2 = null;
	
	/** The Doctorid. */
	private String Doctorid = "";
	
	/** The Cusid. */
	private String Cusid = "";
	
	/** The Customvectors. */
	private Vector Customvectors = null;

	/**
	 * Instantiates a new customer set comment.
	 *页面组件初始化
	 * @param Cusid the cusid
	 * @param Doctorid the doctorid
	 * 参数1顾客id 参数2此摄影师id
	 */
	public Customer_setComment(String Cusid, String Doctorid) {
		this.Doctorid = Doctorid;
		this.Cusid = Cusid;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 300, 560, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setTitle("评论");
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 600, 500);
		panel.setLayout(null);

		Customid2 = new JLabel(Cusid);
		Doctorid2 = new JLabel(Doctorid);
		EvalMassage = new JTextArea();

		JLabel Name = new JLabel("顾客ID  ：");
		Name.setFont(new Font("楷体", Font.PLAIN, 14));
		Name.setBounds(30, 60, 87, 20);
		JLabel MI = new JLabel("摄影师ID：");
		MI.setFont(new Font("楷体", Font.PLAIN, 14));
		MI.setBounds(30, 100, 87, 20);

		Customid2.setFont(new Font("楷体", Font.PLAIN, 14));
		Customid2.setBounds(130, 60, 87, 20);
//
		Doctorid2.setFont(new Font("楷体", Font.PLAIN, 14));
		Doctorid2.setBounds(130, 100, 87, 20);

		EvalMassage.setBounds(30, 180, 350, 200);
		EvalMassage.setLineWrap(true);// 如果内容过长，自动换行，在文本域加上滚动条，水平和垂直滚动条始终出现。
		EvalMassage.setEditable(true);// 文本框可调试
		// 下拉框元素

		// 注册按钮
		// 退出按钮
		JButton button_1 = new JButton("返回");
		button_1.setFont(new Font("楷体", Font.PLAIN, 15));
		button_1.setBounds(400, 400, 120, 23);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// sleep();
				setVisible(false);

			}
		});
		JButton button_2 = new JButton("确认");
		button_2.setFont(new Font("楷体", Font.PLAIN, 15));
		button_2.setBounds(400, 430, 120, 23);
		button_2.addActionListener(this);
		// 页面容器
		contentPane.add(panel);
		// 加入填空框名称
//        panel.add(ID);
		panel.add(Customid2);
		panel.add(Doctorid2);
//        panel.add(Addtime2);
//        panel.add(Flag2);
//        panel.add(Flag);
//        panel.add(time);
		panel.add(Name);
		panel.add(MI);
		// 填空文本框
		panel.add(EvalMassage);

		// 按钮框
		panel.add(button_2);
		panel.add(button_1);

	}

	/**
	 * Strchange.
	 *将评论多行文本框得到的字符串中
	 *回车字符更换成sql server可识别的字符
	 * @param str the str
	 * @return the string
	 * 返回修改后的字符串
	 */
	// 字符串中回车改为数据库可读取的样式
	public String strchange(String str) {
		StringBuilder sb = new StringBuilder();
		String[] sourceStrArray = str.split("\n");// 分割出来的字符数组
		for (int i = 0; i < sourceStrArray.length; i++) {
			sb.append(sourceStrArray[i]);
			if (i + 1 >= sourceStrArray.length)
				sb.append("'+char(10)");
			else if (i + 1 < sourceStrArray.length)
				sb.append("'+char(10)+'");
		}
		str = sb.toString();

		return str;
	}

	
	    /* (非 Javadoc)
	    * 
	    * 按钮的事件应答
	    * @param arg0
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("确认")) {

			String str = EvalMassage.getText();

			if (!str.equals(""))
				str = strchange(str);
			System.out.println(str);
			if (!str.equals("")) {
				String sql = "Insert  INTO Comment(Customid,Doctorid,Comment )" + " values(" + Cusid + ","
						+ Doctorid + ",'" + str +" )";
				
				Dao Dao2 = new Dao();
				Dao2.connect();
				int sure = Dao2.executeUpdate(sql);
				if(sure>0) {
					JOptionPane.showMessageDialog(null, "评论已发送。");
					this.setVisible(false);
				}
			}
		}
				
	}
}