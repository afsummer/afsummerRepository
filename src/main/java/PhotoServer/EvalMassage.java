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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Dao.Dao;
// TODO: Auto-generated Javadoc

/**
 * The Class EvalMassage.
 *预约表查看（通用）
 * @author afsummer
 * @version  v1.0
 * @date 2020-7-4
 */
public class EvalMassage extends JFrame implements ActionListener {
	
	/** The content pane. */
	public JPanel contentPane;
	
	/** The Eval massage. */
	private static JTextArea EvalMassage;
	
	/** The Evaid 2. */
	private JLabel Evaid2=null;
	
	/** The Customid 2. */
	private JLabel Customid2=null;
	
	/** The Doctorid 2. */
	private JLabel Doctorid2=null;
	
	/** The Addtime 2. */
	private JLabel Addtime2=null;
	
	/** The Flag 2. */
	private JLabel Flag2=null;
	
	/** The Evaid. */
	private String Evaid=null;
	
	/** The Customvectors. */
	private  Vector Customvectors=null;
	
	/**
	 * Instantiates a new eval massage.
	 *预约表组件初始化
	 * @param Evaid the evaid
	 */
	public EvalMassage(String Evaid) {
		this.Evaid=Evaid;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 300, 560, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setTitle("预约单详情");
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 600, 500);
		panel.setLayout(null);

		
		try{
			// 建立查询条件
			String sql="SELECT * from Evaluation where EvaId=";
				sql = sql  + Evaid+";";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro =new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
			while(rs.next()){//生存输出的列表的所需的信息
			
				 Evaid2 = new JLabel(rs.getString("EvaId"));
				Customid2 = new JLabel(rs.getString("Customid"));
				Doctorid2 = new JLabel(rs.getString("Doctorid"));
				Addtime2 = new JLabel(rs.getString("EaddTime"));
				EvalMassage=new JTextArea(rs.getString("Eval"));
				if(rs.getString("EvaFlag").equals("0")) {
					Flag2=new JLabel("预约中");
				}else if(rs.getString("EvaFlag").equals("-1")) {
					Flag2=new JLabel("预约取消");
					Flag2.setForeground(Color.RED);
				}else if(rs.getString("EvaFlag").equals("1")) {
					Flag2=new JLabel("预约成功");
					Flag2.setForeground(Color.GREEN);
				}

		}
			DbPro.disconnect();
		}catch(SQLException sqle){
			JOptionPane.showMessageDialog(null,
				"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
		}

	
		JLabel ID = new JLabel("预约单ID：");
		ID.setFont(new Font("楷体", Font.PLAIN, 14));
		ID.setBounds(30, 20, 87, 20);
		
		
		JLabel Name = new JLabel("顾客ID  ：");
		Name.setFont(new Font("楷体", Font.PLAIN, 14));
		Name.setBounds(30, 60, 87, 20);
		JLabel MI = new JLabel("摄影师ID：");
		MI.setFont(new Font("楷体", Font.PLAIN, 14));
		MI.setBounds(30, 100, 87, 20);
		JLabel time = new JLabel("预约填写时间：");
		time.setFont(new Font("楷体", Font.PLAIN, 14));
		time.setBounds(30, 140, 100, 20);
		JLabel Flag = new JLabel("预约情况：");
		Flag.setFont(new Font("楷体", Font.PLAIN, 14));
		Flag.setBounds(300, 20, 87, 20);

		Evaid2.setFont(new Font("楷体", Font.PLAIN, 14));
		Evaid2.setBounds(130, 20, 87, 20);

		Customid2.setFont(new Font("楷体", Font.PLAIN, 14));
		Customid2.setBounds(130, 60, 87, 20);
//
		Doctorid2.setFont(new Font("楷体", Font.PLAIN, 14));
		Doctorid2.setBounds(130, 100, 87, 20);
//
		Addtime2.setFont(new Font("楷体", Font.PLAIN, 14));
		Addtime2.setBounds(130, 140, 95, 20);
		

		Flag2.setFont(new Font("楷体", Font.PLAIN, 25));
		Flag2.setBounds(390, 20, 120, 25);
		// 输入栏设置
		
		EvalMassage.setBounds(30,180,350,200);
		EvalMassage.setLineWrap(true);//如果内容过长，自动换行，在文本域加上滚动条，水平和垂直滚动条始终出现。
		EvalMassage.setEditable(false);//文本框不可调试
		// 下拉框元素
		
		
		// 注册按钮

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
        panel.add(Evaid2);
        panel.add(Customid2);
        panel.add(Doctorid2);
        panel.add(Addtime2);
        panel.add(Flag2);
        panel.add(Flag);
        panel.add(time);
		panel.add(Name);
		panel.add(MI);
		// 填空文本框
	panel.add(EvalMassage);

		 
		// 按钮框
		panel.add(button_1);

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
