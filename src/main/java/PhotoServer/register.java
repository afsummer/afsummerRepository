package PhotoServer;

import java.sql.Date;
import java.text.SimpleDateFormat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * The Class register.
 *ע�����
 * @author afsummer
 * @version  v1.0
 * @date 2020-7-4
 */
public class register extends JFrame implements ActionListener {

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
	
	/** The Select query field str. */
	String SelectQueryFieldStr;
	
	/** The Select query field str 2. */
	String SelectQueryFieldStr2;

	/**
	 * Instantiates a new register.
	 */
	public register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 300, 500, 500);
		contentPane = new JPanel();
	
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ע���˺�");
		lblNewLabel.setBounds(200, 10, 200, 51);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);

		JPanel panel = new JPanel();
		panel.setBounds(5, 65, 450, 500);

		panel.setLayout(null);

		JLabel Name = new JLabel("    �û���");
		Name.setFont(new Font("����", Font.PLAIN, 14));
		Name.setBounds(68, 20, 87, 20);

		JLabel MI = new JLabel("    ����");
		MI.setFont(new Font("����", Font.PLAIN, 14));
		MI.setBounds(68, 60, 87, 20);
		JLabel ReMI = new JLabel("   ȷ������");
		ReMI.setFont(new Font("����", Font.PLAIN, 14));
		ReMI.setBounds(68, 100, 87, 20);

		JLabel Address = new JLabel("   ��ַ");
		Address.setFont(new Font("����", Font.PLAIN, 14));
		Address.setBounds(68, 140, 87, 20);

		JLabel Tel = new JLabel("   �绰����");
		Tel.setFont(new Font("����", Font.PLAIN, 14));
		Tel.setBounds(68, 180, 87, 20);
		JLabel QQ = new JLabel("   QQ����");
		QQ.setFont(new Font("����", Font.PLAIN, 14));
		QQ.setBounds(68, 220, 87, 20);
		JLabel Studio = new JLabel("   ��Ӱ¥");
		Studio.setFont(new Font("����", Font.PLAIN, 14));
		Studio.setBounds(68, 260, 87, 20);

		// ����������
		name = new JTextField();
		name.setBounds(165, 20, 128, 25);
		name.setColumns(20);

		Password = new JTextField();
		Password.setBounds(165, 60, 128, 25);
		Password.setColumns(20);

		Password2 = new JTextField();
		Password2.setBounds(165, 100, 128, 25);
		Password2.setColumns(20);

		address = new JTextField();
		address.setBounds(165, 140, 128, 25);
		address.setColumns(20);

		telNumber = new JTextField();
		telNumber.setBounds(165, 180, 128, 25);
		telNumber.setColumns(20);

		qqnumber = new JTextField();
		qqnumber.setBounds(165, 220, 128, 25);
		qqnumber.setColumns(20);
		// ������Ԫ��
		String sql1 = "SELECT Studioid from Studio";

		System.out.println("queryProcess(). sql = " + sql1);
		Dao Dao = new Dao();
		Dao.connect();

		JComboBox<String> comboBox = new JComboBox<String>();
		JComboBox<String> comboBox2 = new JComboBox<String>();
		ResultSet rs1 = Dao.executeQuery1(sql1);
		comboBox2.addItem(" ");
		try {
			while (rs1.next()) {
				comboBox2.addItem(rs1.getString("Studioid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comboBox2.setSelectedIndex(0);
		comboBox2.setBounds(165, 260, 91, 25);
		Dao.disconnect();
		comboBox2.addItemListener(new ItemListener() {// �������¼�����
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr2 = (String) event.getItem();
					System.out.println(SelectQueryFieldStr2);
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});

		comboBox.addItem("��Ӱʦ");
		comboBox.addItem("�˿�");
		comboBox.addItemListener(new ItemListener() {// �������¼�����
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr = (String) event.getItem();// ѡ�е�����
					if (SelectQueryFieldStr.equals("�˿�")) {
						address.setVisible(false);
						comboBox2.setVisible(false);
					} else if (SelectQueryFieldStr.contentEquals("��Ӱʦ")) {
						address.setVisible(true);
						comboBox2.setVisible(true);
					}
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(313, 21, 91, 25);

		// ע�ᰴť
		JButton button = new JButton("ȷ��ע��");
		button.setFont(new Font("����", Font.PLAIN, 15));
		button.setBounds(324, 320, 120, 23);
		button.addActionListener(this);


		// �˳���ť
		JButton button_1 = new JButton("����");
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

		panel.add(Name);// ������տ�����
		panel.add(MI);
		panel.add(ReMI);
		panel.add(Address);
		panel.add(Tel);
		panel.add(QQ);
		panel.add(Studio);
		// ����ı���
		panel.add(name);
		panel.add(Password);
		panel.add(Password2);
		panel.add(address);
		panel.add(telNumber);
		panel.add(qqnumber);

		panel.add(comboBox);// ������
		panel.add(comboBox2);
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
	 *
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
	    * 
	    * @param arg0
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("ȷ��ע��") && !name.getText().isEmpty() && !Password.getText().isEmpty()
				&& !Password2.getText().isEmpty() && !telNumber.getText().isEmpty()) {
			System.out.println("actionPerformed(). ȷ�Ͽ�ʼע��");
			boolean flag = false;
			if (name.getText().trim().contains(" ")) {
				JOptionPane.showMessageDialog(null, "���ƺ��пո�", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (Password.getText().trim().contains(" ")) {
				JOptionPane.showMessageDialog(null, "���뺬�пո�", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (!Password.getText().equals(Password2.getText())) {
				JOptionPane.showMessageDialog(null, "���벻һ�£�", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (!isTel(telNumber.getText().trim())) {
				JOptionPane.showMessageDialog(null, "�绰�����ʽ����", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (!qqnumber.getText().trim().isEmpty()) {
				if (!isQQ(qqnumber.getText().trim())) {
					JOptionPane.showMessageDialog(null, "QQ��������", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
					flag = true;
				}
			}
			if (SelectQueryFieldStr.equals("��Ӱʦ") && !flag) {
				if (address.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "��Ӱʦ��ַ����Ϊ�գ�", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
				else {
					String tempfield1 = Password.getText().trim();
					String tempfield2 = name.getText().trim();
					String tempfield3 = telNumber.getText().trim();
					String tempfield4 = "0";
					if (!qqnumber.getText().isEmpty()) {
						tempfield4 = qqnumber.getText().trim();
					}
					SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
					String tempfield5 = tempDate.format(new java.util.Date());
					String tempfield6 = address.getText().trim();

					String sql = "INSERT into DoctorInfo(DPassword,Dname,DtelNumber,Daddtime,Dqqnumber,Daddress,Studioid) values('"
							+ tempfield1 + "','" + tempfield2 + "'," + tempfield3 + ",'" + tempfield5 + "',"
							+ tempfield4 + ",'" + tempfield6 + "'," + SelectQueryFieldStr2 + ")";
					System.out.println("queryProcess(). sql = " + sql);
					Dao Dao1 = new Dao();
					Dao1.connect();
					int sure = Dao1.insert(sql);
					if (sure > 0) {
						String sql2 = "SELECT TOP 1 Doctorid FROM DoctorInfo order by Doctorid desc";
						ResultSet rs = Dao1.executeQuery1(sql2);
						try {
							while(rs.next())
							JOptionPane.showMessageDialog(null, "ע��ɹ���\n���ID��"+rs.getString("Doctorid"));
						} catch (HeadlessException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dispose();
					}

				}
			}

			else if (SelectQueryFieldStr.equals("�˿�") && !flag) {
				String tempfield1 = Password.getText().trim();
				String tempfield2 = name.getText().trim();
				String tempfield3 = telNumber.getText().trim();
				String tempfield4 = "0";
				if (!qqnumber.getText().isEmpty()) {
					tempfield4 = qqnumber.getText().trim();
				}

				SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
				String tempfield5 = tempDate.format(new java.util.Date());
				String sql = "INSERT into Customer(CPassword,Cname,CtelNumber,Caddtime,Cqqnumber) values('" + tempfield1
						+ "','" + tempfield2 + "'," + tempfield3 + ",'" + tempfield5 + "','" + tempfield4 + "')";

				System.out.println("queryProcess(). sql = " + sql);
				Dao Dao2 = new Dao();

				int sure = Dao2.insert(sql);
				if (sure > 0) {
					String sql2 = "SELECT TOP 1 Customid FROM Customer order by Customid desc";
					ResultSet rs = Dao2.executeQuery1(sql2);
					try {
						while(rs.next())
						JOptionPane.showMessageDialog(null, "ע��ɹ���\n���ID��"+rs.getString("Customid"));
					} catch (HeadlessException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dispose();
				}
				Dao2.connect();
			}
		}
	}


}
