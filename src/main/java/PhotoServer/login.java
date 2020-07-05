package PhotoServer;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import Dao.Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class login.
 *��¼ҳ��
 * @author afsummer
 * @version  v1.0
 * @date 2020-7-4
 */
public class login extends JFrame implements ActionListener {
	
	/** The content pane. */
	public JPanel contentPane;
	
	/** The Tno. */
	private static JTextField Tno;
	
	/** The Tmi. */
	private static JTextField Tmi;

	/** The Select query field str. */
	String SelectQueryFieldStr;
	
	/** The Select query field str 2. */
	String SelectQueryFieldStr2;

	
	    /* (�� Javadoc)
	    * 
	    * ������ť�¼�Ӧ��
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ȷ�ϵ�¼") && !Tno.getText().isEmpty() && !Tmi.getText().isEmpty()) {
			String sQueryField = Tno.getText().trim();
			String sQueryField1 = Tmi.getText().trim();

			// �жϵ�½�����
			if (SelectQueryFieldStr.equals("�˿�")) {
				String sql = "SELECT Customid,CPassword from Customer where Customid  = ";
				sql = sql + sQueryField + ";";
				Dao Dao = new Dao();
				Dao.connect();
				ResultSet rs = Dao.executeQuery1(sql);

				try {
					if (rs.isBeforeFirst()) {

					} else {
						JOptionPane.showMessageDialog(null, "�����¼��ݣ��˺��������Ƿ���ȷ��", "��¼ʧ��", JOptionPane.ERROR_MESSAGE);
					}
					while (rs.next()) {
						String temp1 = rs.getString("Customid");
						String temp2 = rs.getString("CPassword");

						try {
							if (sQueryField.equals(temp1.trim()) && sQueryField1.equals(temp2.trim())) {
								JOptionPane.showMessageDialog(null, "��ӭʹ�ô˹���ϵͳ��");
								// ���ع˿�ҳ��
								Customers customer = new Customers(temp1);
								customer.setVisible(true);
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(null, "�����¼��ݣ��˺��������Ƿ���ȷ��", "��¼ʧ��",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "���ݲ�������", "����", JOptionPane.ERROR_MESSAGE);
						}

					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			} else if (SelectQueryFieldStr.equals("��Ӱʦ")) {
				String sql = "SELECT Doctorid,DPassword from DoctorInfo where Doctorid = ";
				sql = sql + sQueryField + ";";
				System.out.println("queryProcess(). sql = " + sql);
				Dao Dao = new Dao();
				Dao.connect();
				ResultSet rs = Dao.executeQuery1(sql);
				// ��������˺���������ʱû����ʾ��Ϣ
				try {
					if (rs.isBeforeFirst()) {

					} else {
						JOptionPane.showMessageDialog(null, "�����¼��ݣ��˺��������Ƿ���ȷ��", "��¼ʧ��", JOptionPane.ERROR_MESSAGE);
					}
					while (rs.next()) {
						String temp1 = rs.getString("Doctorid");
						String temp2 = rs.getString("DPassword");
						try {
							if (sQueryField.equals(temp1.trim()) && sQueryField1.equals(temp2.trim())) {

								JOptionPane.showMessageDialog(null, "��ӭʹ��Ӱ¥�����ϵͳ��");
								DoctorInfo Doctor = new DoctorInfo(temp1);
								Doctor.setVisible(true);
								setVisible(false);

							} else {
								JOptionPane.showMessageDialog(null, "�����¼��ݣ��˺��������Ƿ���ȷ��", "��¼ʧ��",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "���ݲ�������", "����", JOptionPane.ERROR_MESSAGE);
						}

					}
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			} else if (SelectQueryFieldStr.equals("����Ա")) {
				String sql = "SELECT * from Admin where id = ";
				sql = sql + sQueryField + ";";
				Dao Dao = new Dao();
				Dao.connect();
				ResultSet rs = Dao.executeQuery1(sql);
				try {
					if (rs.isBeforeFirst()) {

					} else {
						JOptionPane.showMessageDialog(null, "�����¼��ݣ��˺��������Ƿ���ȷ��", "��¼ʧ��", JOptionPane.ERROR_MESSAGE);
					}
					while (rs.next()) {
						String temp1 = rs.getString("id");
						String temp2 = rs.getString("Password");

						try {
							if (sQueryField.equals(temp1.trim()) && sQueryField1.equals(temp2.trim())) {

								JOptionPane.showMessageDialog(null, "��ӭʹ��Ӱ¥�����ϵͳ��");
								Admin admin = new Admin();
								admin.setVisible(true);
								setVisible(false);

							} else {
								JOptionPane.showMessageDialog(null, "�����¼��ݣ��˺��������Ƿ���ȷ��", "��¼ʧ��",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "���ݲ�������", "����", JOptionPane.ERROR_MESSAGE);
						}

					}
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		}

	}

	/**
	 * Create the frame.
	 * ��¼���������ʼ��
	 */
	public login() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 270, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//�������������
		JLabel lblNewLabel = new JLabel("��ӭʹ�ñ���ӰԼ��ϵͳ");
		lblNewLabel.setBounds(146, 15, 251, 51);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lblNewLabel);
		JPanel panel = new JPanel();
		panel.setBounds(10, 75, 450, 98);
		contentPane.add(panel);
		panel.setLayout(null);

		// ������Ԫ��
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("��Ӱʦ");
		comboBox.addItem("�˿�");
		comboBox.addItem("����Ա");
		// �����������
		comboBox.addItemListener(new ItemListener() {// �������¼�����
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr = (String) event.getItem();// ѡ�е�����
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(313, 21, 91, 25);
		panel.add(comboBox);
		// ����������
		JLabel NO = new JLabel("    ID");
		NO.setFont(new Font("����", Font.PLAIN, 14));
		NO.setBounds(68, 23, 87, 20);
		panel.add(NO);

		JLabel MI = new JLabel("    ����");
		MI.setFont(new Font("����", Font.PLAIN, 14));
		MI.setBounds(68, 61, 87, 20);
		panel.add(MI);
		// ����������
		Tno = new JTextField();
		Tno.setBounds(165, 21, 128, 25);
		panel.add(Tno);
		Tno.setColumns(10);

		Tmi = new JTextField();
		Tmi.setBounds(165, 59, 128, 25);
		panel.add(Tmi);
		Tmi.setColumns(10);
		// ��¼��ť
		JButton button = new JButton("ȷ�ϵ�¼");
		button.setFont(new Font("����", Font.PLAIN, 15));
		button.setBounds(313, 60, 120, 23);
		panel.add(button);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		button.addActionListener(this);

//�˳���ť
		JButton button_1 = new JButton("��ȫ�˳�");
		button_1.setFont(new Font("����", Font.PLAIN, 15));
		button_1.setBounds(324, 208, 120, 23);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// sleep();
				dispose();
			}
		});
		contentPane.add(button_1);

		JButton button_2 = new JButton("ע���˺�");
		button_2.setFont(new Font("����", Font.PLAIN, 15));
		button_2.setBounds(324, 180, 120, 23);
		button_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {

				register test = new register();
				test.setVisible(true);

			}
		});
		contentPane.add(button_2);
	}


}
