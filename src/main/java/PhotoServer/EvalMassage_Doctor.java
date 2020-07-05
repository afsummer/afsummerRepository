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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Dao.Dao;
// TODO: Auto-generated Javadoc

/**
 * The Class EvalMassage_Doctor.
 *ԤԼ�鿴����Ӱʦ��
 * @author nieming
 * @version  v1.0
 * @date 2020-7-4
 */
public class EvalMassage_Doctor extends JFrame implements ActionListener {
	
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
	
	/** The Eval ID. */
	private String EvalID="";
	
	/** The Cusid. */
	private String Cusid="";
	
	/** The Select query field str. */
	private String SelectQueryFieldStr;
	
	/** The Customvectors. */
	private  Vector Customvectors=null;
	
	/** The flag. */
	private int flag=1;
	
	/**
	 * Instantiates a new eval massage doctor.
	 *ԤԼ����Ϣҳ�����
	 * @param Evalid the evalid
	 * ����1ԤԼ��id
	 */
	public EvalMassage_Doctor(String Evalid) {
		this.EvalID=Evalid;
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
				sql = sql  + EvalID;
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
		JComboBox<String> comboBox = new JComboBox<String>();

		comboBox.addItem("����");
		comboBox.addItem("�ܾ�");
		comboBox.addItemListener(new ItemListener() {// �������¼�����
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr = (String) event.getItem();// ѡ�е�����
					if (SelectQueryFieldStr.equals("����")) {
						flag=1;
					} else if (SelectQueryFieldStr.contentEquals("�ܾ�")) {
						flag=-1;
					}
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(300, 60, 91, 25);
		// ע�ᰴť
		// �˳���ť
		JButton button_1 = new JButton("����");
		button_1.setFont(new Font("����", Font.PLAIN, 15));
		button_1.setBounds(400, 400, 120, 23);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// sleep();
				setVisible(false);

			}
		});
		JButton button_2 = new JButton("ȷ��ԤԼ");
		button_2.setFont(new Font("����", Font.PLAIN, 15));
		button_2.setBounds(400, 430, 120, 23);
        button_2.addActionListener(this);
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
//������
	panel.add(comboBox);
		
		// ��ť��
		panel.add(button_2);
		panel.add(button_1);

	}



	
	    /* (�� Javadoc)
	    * 
	    * ��ť�¼�Ӧ��
	    * @param arg0
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent arg0) {
if(arg0.getActionCommand().equals("ȷ��ԤԼ")) {
	String sql = "UPDATE Evaluation SET EvaFlag=" +  flag+ " where EvaId=" + this.EvalID;

	Dao Dao2 = new Dao();
	Dao2.connect();
	int sure = Dao2.executeUpdate(sql);
	if (sure > 0) {
		JOptionPane.showMessageDialog(null, "ԤԼ״̬��ȷ�ϡ�");
		dispose();
	}
}
	}


}

