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
 *ԤԼ��鿴��ͨ�ã�
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
	 *ԤԼ�������ʼ��
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

		this.setTitle("ԤԼ������");
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 600, 500);
		panel.setLayout(null);

		
		try{
			// ������ѯ����
			String sql="SELECT * from Evaluation where EvaId=";
				sql = sql  + Evaid+";";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro =new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
			while(rs.next()){//����������б���������Ϣ
			
				 Evaid2 = new JLabel(rs.getString("EvaId"));
				Customid2 = new JLabel(rs.getString("Customid"));
				Doctorid2 = new JLabel(rs.getString("Doctorid"));
				Addtime2 = new JLabel(rs.getString("EaddTime"));
				EvalMassage=new JTextArea(rs.getString("Eval"));
				if(rs.getString("EvaFlag").equals("0")) {
					Flag2=new JLabel("ԤԼ��");
				}else if(rs.getString("EvaFlag").equals("-1")) {
					Flag2=new JLabel("ԤԼȡ��");
					Flag2.setForeground(Color.RED);
				}else if(rs.getString("EvaFlag").equals("1")) {
					Flag2=new JLabel("ԤԼ�ɹ�");
					Flag2.setForeground(Color.GREEN);
				}

		}
			DbPro.disconnect();
		}catch(SQLException sqle){
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}

	
		JLabel ID = new JLabel("ԤԼ��ID��");
		ID.setFont(new Font("����", Font.PLAIN, 14));
		ID.setBounds(30, 20, 87, 20);
		
		
		JLabel Name = new JLabel("�˿�ID  ��");
		Name.setFont(new Font("����", Font.PLAIN, 14));
		Name.setBounds(30, 60, 87, 20);
		JLabel MI = new JLabel("��ӰʦID��");
		MI.setFont(new Font("����", Font.PLAIN, 14));
		MI.setBounds(30, 100, 87, 20);
		JLabel time = new JLabel("ԤԼ��дʱ�䣺");
		time.setFont(new Font("����", Font.PLAIN, 14));
		time.setBounds(30, 140, 100, 20);
		JLabel Flag = new JLabel("ԤԼ�����");
		Flag.setFont(new Font("����", Font.PLAIN, 14));
		Flag.setBounds(300, 20, 87, 20);

		Evaid2.setFont(new Font("����", Font.PLAIN, 14));
		Evaid2.setBounds(130, 20, 87, 20);

		Customid2.setFont(new Font("����", Font.PLAIN, 14));
		Customid2.setBounds(130, 60, 87, 20);
//
		Doctorid2.setFont(new Font("����", Font.PLAIN, 14));
		Doctorid2.setBounds(130, 100, 87, 20);
//
		Addtime2.setFont(new Font("����", Font.PLAIN, 14));
		Addtime2.setBounds(130, 140, 95, 20);
		

		Flag2.setFont(new Font("����", Font.PLAIN, 25));
		Flag2.setBounds(390, 20, 120, 25);
		// ����������
		
		EvalMassage.setBounds(30,180,350,200);
		EvalMassage.setLineWrap(true);//������ݹ������Զ����У����ı�����Ϲ�������ˮƽ�ʹ�ֱ������ʼ�ճ��֡�
		EvalMassage.setEditable(false);//�ı��򲻿ɵ���
		// ������Ԫ��
		
		
		// ע�ᰴť

		// �˳���ť
		JButton button_1 = new JButton("����");
		button_1.setFont(new Font("����", Font.PLAIN, 15));
		button_1.setBounds(400, 400, 120, 23);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});

		// ҳ������
		contentPane.add(panel);
		// ������տ�����
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
		// ����ı���
	panel.add(EvalMassage);

		 
		// ��ť��
		panel.add(button_1);

	}



	
	    /* (�� Javadoc)
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
