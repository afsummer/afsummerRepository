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
     *�˿���Ϣ�޸�ҳ��
     * @author afsummer
     * @date 2020��7��3��
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
	 *ҳ�������ʼ��
	 * @param id the id
	 * ����idΪ�˿�id
	 */
	public Customer_amend(String id) {
		Cusid=id;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 300, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("������Ϣ�޸�");
		lblNewLabel.setBounds(180, 10, 200, 51);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);

		JPanel panel = new JPanel();
		panel.setBounds(5, 65, 450, 500);
		panel.setLayout(null);
		
		try{
			// ������ѯ����
			String sql="SELECT * from Customer where Customid=";
				sql = sql  + id+";";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro =new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
			while(rs.next()){//����������б���������Ϣ
			
				 ID2 = new JLabel(rs.getString("Customid"));
				name = new JTextField(rs.getString("Cname"));
				Password = new JTextField(rs.getString("CPassword"));
				telNumber = new JTextField(rs.getString("CtelNumber"));
				qqnumber = new JTextField(rs.getString("Cqqnumber"));

		}
			DbPro.disconnect();
		}catch(SQLException sqle){
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}

	
		JLabel ID = new JLabel("    ID");
		ID.setFont(new Font("����", Font.PLAIN, 14));
		ID.setBounds(68, 20, 87, 20);
		
		ID2.setFont(new Font("����", Font.PLAIN, 14));
		ID2.setBounds(165, 20, 87, 20);
		JLabel Name = new JLabel("    �û���");
		Name.setFont(new Font("����", Font.PLAIN, 14));
		Name.setBounds(68, 60, 87, 20);
		JLabel MI = new JLabel("    ����");
		MI.setFont(new Font("����", Font.PLAIN, 14));
		MI.setBounds(68, 100, 87, 20);
		JLabel ReMI = new JLabel("   ȷ������");
		ReMI.setFont(new Font("����", Font.PLAIN, 14));
		ReMI.setBounds(68, 140, 87, 20);
		JLabel Tel = new JLabel("   �绰����");
		Tel.setFont(new Font("����", Font.PLAIN, 14));
		Tel.setBounds(68, 180, 87, 20);
		JLabel QQ = new JLabel("   QQ����");
		QQ.setFont(new Font("����", Font.PLAIN, 14));
		QQ.setBounds(68, 220, 87, 20);

		
		// ����������
		
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
		// ������Ԫ��
		
		
		// ע�ᰴť
		JButton button = new JButton("ȷ���޸�");
		button.setFont(new Font("����", Font.PLAIN, 15));
		button.setBounds(324, 320, 120, 23);
		button.addActionListener(this);

		// �˳���ť
		JButton button_1 = new JButton("ȡ������");
		button_1.setFont(new Font("����", Font.PLAIN, 15));
		button_1.setBounds(324, 350, 120, 23);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// sleep();
				setVisible(false);

			}
		});

		// ҳ������
		contentPane.add(lblNewLabel);
		contentPane.add(panel);
        panel.add(ID);
        panel.add(ID2);
		panel.add(Name);// ������տ�����
		panel.add(MI);
		panel.add(ReMI);
		panel.add(Tel);
		panel.add(QQ);
		// ����ı���
		panel.add(name);
		panel.add(Password);
		panel.add(Password2);
		panel.add(telNumber);
		panel.add(qqnumber);

		
		// ��ť��
		panel.add(button);
		panel.add(button_1);

	}

	/**
	 * ��½�ֻ�����11λ����ƥ���ʽ��ǰ��λ�̶���ʽ+��8λ������ �˷�����ǰ��λ��ʽ�У� 13+������ 145,147,149
	 * 15+��4��������(��Ҫд^4�������Ļ���ĸҲ�ᱻ��Ϊ����ȷ��) 166 17+3,5,6,7,8 18+������ 198,199.
	 *
	 * @param str the str
	 * @return true, if is tel
	 * @throws PatternSyntaxException the pattern syntax exception
	 */
	public static boolean isTel(String str) throws PatternSyntaxException {
		// ^ ƥ�������ַ�����ʼ��λ��
		// \d ƥ��һ���������֣����� \ Ҫת�壬������ \\d
		// $ ƥ�������ַ�����β��λ��
		String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" + "|(18[0-9])|(19[8,9]))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * Checks if is qq.
	 *qq�������������
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

	
	    /* (�� Javadoc)
	    * 
	    * �Ը�����ť���¼�Ӧ��
	    * @param arg0
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("ȷ���޸�") && !name.getText().isEmpty() && !Password.getText().isEmpty()
				&& !Password2.getText().isEmpty() && !telNumber.getText().isEmpty()) {
			System.out.println("actionPerformed(). ȷ�Ͽ�ʼ�޸�");
			boolean flag = false;
			if (name.getText().trim().contains(" ")) {
				JOptionPane.showMessageDialog(null, "���ƺ��пո�", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (Password.getText().trim().contains(" ")) {
				JOptionPane.showMessageDialog(null, "���뺬�пո�", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (!Password.getText().equals(Password2.getText())) {
				JOptionPane.showMessageDialog(null, "���벻һ�£�", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (!isTel(telNumber.getText().trim())) {
				JOptionPane.showMessageDialog(null, "�绰�����ʽ����", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (!qqnumber.getText().trim().isEmpty()) {
				if (!isQQ(qqnumber.getText().trim())) {
					JOptionPane.showMessageDialog(null, "QQ��������", "�޸�ʧ��", JOptionPane.ERROR_MESSAGE);
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
					 JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
			
							// ������ѯ����
							String sql2="SELECT * from Customer where Customid=";
								sql2 = sql2  + Cusid+";";
							ResultSet rs2 = Dao2.executeQuery1(sql2);
						
							try {
								while(rs2.next()){//����������б���������Ϣ
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
	 *���������ݷ��أ����ã�
	 * @return the vector
	 */
	public  Vector returnNew() {
		return Customvectors;
	}
}
