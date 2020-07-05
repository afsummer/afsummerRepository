package PhotoServer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import Dao.Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class Admin.
 *
 * @author nieming
 * @version  v1.0
 * @date 2020-7-4
 */
public class Admin extends JFrame implements ActionListener {
	
	/** The j L custom info table. */
	// ִ�г���ʱ��ֱ�ӳ�ʼ��
	JLabel jLCustomInfoTable = null;// ѧ����Ϣ��jlabel�ñ�ǩ������ʾ���ڴ�ֱ��ˮƽ���ж��롣


	/** The Select query field str. */
	private String SelectQueryFieldStr;// ��ѯ�ֶ�//JTextField�ı���JTextField �����༭���е��ı�
	
	/** The Select query field str 2. */
	private String SelectQueryFieldStr2;
	
	/** The j TFS no. �����ֶε������ı���*/
	JTextField jTFSNo = null;
	
	/** The j P 4. ����������õĵڶ�������*/
	JPanel jP1, jP2, jP3, jP4 = null;
	
	/** The j P bottom.��һ�η�Ϊ����2������ */
	JPanel jPTop, jPBottom = null;

	/** The Custom J scroll pane 1. */
	private JScrollPane CustomJScrollPane_1;// ��������
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The Custom J table 1. */
	JTable CustomJTable1 = null;
	
	/** The Custom J table 2. */
	JTable CustomJTable2 = null;
	
	/** The CJ scroll pane 2. */
	private JScrollPane CJScrollPane_2;

	/** The Vector. */
	Vector Vector = null;// vector��ʵ���˶�̬���飬����Ԫ�������仯�Ķ������顣������һ��
	//title�����ͷ������
	/** The title vector. */
	Vector titleVector = null;
	
	/** The title vector 1. */
	Vector titleVector1 = null;
	
	/** The title vector 2. */
	Vector titleVector2 = null;
	
	/** The title vector 3. */
	Vector titleVector3 = null;
	//massage������������Ϣ
	/** The massaget vector 1. */
	Vector massagetVector1 = null;
	
	/** The massaget vector 2. */
	Vector massagetVector2 = null;
	
	/** The massaget vector 3. */
	Vector massagetVector3 = null;
	
	/** The row. */
	int row = -1;
	
	/** The col. */
	int col = -1;
	
	/** The j B out. */
	private JButton jBOut;

	/** The button 1. */
	private JButton button_1;
	
	/** The button new. */
	private JButton buttonNew;
	
	/** The Doctor ID. */
	private String DoctorID = "";
	
	/** The where flag. */
	private static int whereFlag = -1;// ������ı������
	
	/** The table. */
	private JTable table;// ���


	/**
	 * Instantiates a new admin.
	 * ������ҳ���������ĳ�ʼ��
	 */
	// ���캯��
	public Admin() {
		// �������

		jLCustomInfoTable = new JLabel("Ӱ¥�����");

		Vector = new Vector();// λ������
		titleVector = new Vector();

		// �����ͷ

		massagetVector1 = new Vector();// ��� ����
		massagetVector2 = new Vector();// ����
		massagetVector3 = new Vector();// ����
		titleVector1 = new Vector();
		titleVector2 = new Vector();
		titleVector3 = new Vector();
		
		// �˿ͱ�
		titleVector2.add("�˿�ID");
		titleVector2.add("�û���");
		titleVector2.add("�绰����");
		titleVector2.add("ע��ʱ��");
		titleVector2.add("QQ����");

//			studentJTable= new DefaultTableModel(tableTitle, 15);
		CustomJTable2 = new JTable(massagetVector2, titleVector2) {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};// ���Ĵ���
		CustomJTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ��ֹ����Ӧ���ͻῪ��������
//		studentJTable2.setPreferredScrollableViewportSize(new Dimension(455, 150));

		CJScrollPane_2 = new JScrollPane(CustomJTable2);
		CJScrollPane_2.setBounds(10, 10, 216, 127);
		// studentJScrollPane.setBounds(0, 0, 462, 83);
		// �ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
		CJScrollPane_2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		CJScrollPane_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// Ϊ�����Ӽ�����
		CustomJTable2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				whereFlag = 1;
				row = CustomJTable2.rowAtPoint(event.getPoint());
				col = CustomJTable2.columnAtPoint(event.getPoint());
				// ��ȡָ���к��е�ֵ
			}
		});
		// ***************************

		jP1 = new JPanel();
		jP1.setBounds(212, 5, 70, 25);
		jP2 = new JPanel();
		jP2.setBounds(20, 40, 462, 120);
		jPTop = new JPanel();
		jPTop.setBounds(0, 0, 494, 150);
		jPBottom = new JPanel();
		jPBottom.setBounds(10, 188, 474, 224);

		buttonNew = new JButton("ˢ��");
		buttonNew.setBounds(300, 5, 70, 25);
		buttonNew.addActionListener(this);

		jP1.add(jLCustomInfoTable, BorderLayout.SOUTH);
		jP2.setLayout(null);
		jPTop.setLayout(null);
		// ��һ�Ŷ����������Ϣ�������
		jPTop.add(jP1);
		jPTop.add(jP2);
		jPTop.add(buttonNew);
//��ͷӰ¥��Ϣ
		titleVector1.add("Ӱ¥ID");
		titleVector1.add("Ӱ¥��");
		titleVector1.add("Ӱ¥��ַ");
		titleVector1.add("Ӱ¥����");
		titleVector1.add("Ӱ¥��Ϣ");

		CustomJTable1 = new JTable(massagetVector1, titleVector1) {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};// ���Ĵ���
//			studentJTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//��ֹ����Ӧ���ͻῪ��������
//		studentJTable1.setPreferredScrollableViewportSize(new Dimension(455, 150));
		CustomJTable1.setRowHeight(20);
		CustomJScrollPane_1 = new JScrollPane(CustomJTable1);
		CustomJScrollPane_1.setBounds(0, 0, 455, 100);
		jP2.add(CustomJScrollPane_1);
		// �ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
		CustomJScrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		CustomJScrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jPBottom.setLayout(null);

		CustomJTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				whereFlag = 3;
				row = CustomJTable1.rowAtPoint(event.getPoint());
				col = CustomJTable1.columnAtPoint(event.getPoint());
				System.out.println("��:" + row + "��" + col);// ע����ʼ����
				// ��ȡָ���к��е�ֵ
				System.out.println("����:" + CustomJTable1.getValueAt(row, col).toString());
				if (2 == event.getClickCount()) {// ˫���¼�
					
					Studio stu = new Studio(CustomJTable1.getValueAt(row, 0).toString(), "0");
					stu.setVisible(true);
				}
			}
		});
		getContentPane().setLayout(null);
		// ��Ӷ��������͵ײ����������ݴ���
		getContentPane().add(jPTop);
		getContentPane().add(jPBottom);

		// ����������
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 454, 137);
		panel.setLayout(null);
//			panel.add(studentJScrollPane_2);

		// �������϶�ʽ��񴰿�
		jPBottom.add(panel);

		titleVector3.add("��ӰʦID");
		titleVector3.add("�û���");
		titleVector3.add("�绰����");
		titleVector3.add("ע��ʱ��");
		titleVector3.add("QQ����");
		titleVector3.add("��ַ");
		titleVector3.add("����Ӱ¥ID");

		// studentVector2
//��Ӱʦ�� 
		table = new JTable(massagetVector3, titleVector3) {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};// ���Ĵ���
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ��ֹ����Ӧ���ͻῪ��������
//		table.setPreferredScrollableViewportSize(new Dimension(455, 150));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(228, 10, 216, 127);
		// studentJScrollPane.setBounds(0, 0, 462, 83);
		// �ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// ˮƽ
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);// ��ֱ
		// Ϊ�����Ӽ�����
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				whereFlag = 2;
				row = table.rowAtPoint(event.getPoint());
				col = table.columnAtPoint(event.getPoint());
				System.out.println("��:" + row + "��" + col);// ע����ʼ����
				// ��ȡָ���к��е�ֵ
				System.out.println("����:" + table.getValueAt(row, col).toString());
				if (2 == event.getClickCount()) {// ˫���¼�
					DoctorMassage DoctorMassage = new DoctorMassage("0", table.getValueAt(row, 0).toString());
					DoctorMassage.setVisible(true);
					// ��ȡָ���к��е�ֵ
				}
			}
		});
		panel.add(scrollPane);
		panel.add(CJScrollPane_2);
		// �����Ű�ť���
//		jBQueryAll = new JButton("ԤԼ���ӡ");
//		jBQueryAll.setBounds(330, 5, 120, 23);
//		jBQueryAll.addActionListener(this);

		jP3 = new JPanel();
		jP3.setBounds(10, 152, 474, 32);
		jP3.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(jP3);
		jP3.setLayout(null);
//		jP3.add(jBQueryAll);
		jP3.setPreferredSize(new Dimension(20, 20));
		
		JButton btnNewButton = new JButton("�����ֶ�");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(30, 5, 86, 23);
		// �����Ű�ť������

		NewcomboBox();
		jP3.add(btnNewButton);
		// �����Ű�ť���
		jP4 = new JPanel();
		jP4.setBounds(0, 162, 474, 38);
		jPBottom.add(jP4);
		jP4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jP4.setLayout(null);
		jP4.setPreferredSize(new Dimension(20, 20));

		JButton button = new JButton("ɾ���˺�");
		button.addActionListener(this);
		button.setBounds(22, 10, 138, 23);

		button_1 = new JButton("Ӱ¥���");
		button_1.setBounds(312, 10, 138, 23);
		button_1.addActionListener(this);

		jBOut = new JButton("���沢�˳�");
		jBOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				login frame1 = new login();
				frame1.setVisible(true);
				setVisible(false);
			}
		});
		jBOut.setBounds(182, 10, 102, 23);
		jBOut.addActionListener(this);
		// �����Ű�ť������
		jP4.add(button);
		jP4.add(button_1);
		jP4.add(jBOut);

		this.setTitle("����Աҳ��");
		this.setSize(500, 451);
		this.setLocation(150, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		titleVector1.add("��ӰʦID");
		titleVector1.add("�û���");
		titleVector1.add("�绰����");
		titleVector1.add("ע��ʱ��");
		titleVector1.add("QQ����");
		titleVector1.add("��ַ");
		titleVector1.add("����Ӱ¥ID");
		// ��¼�û���Ϣ����ڶ��ű����

		// �����ű������ԤԼ���

		Renew();
	}

	/**
	 * Custom new.
	 * �˿���Ϣ���������
	 */
	public void CustomNew() {
		try {
			// ������ѯ����
			String sql = "SELECT * from Customer ";

			Dao DbPro = new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
//		 ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			// �Ա��ĸ��£���ʵ���Բ�Ҫ��һ����
			massagetVector2.clear();

			while (rs.next()) {// ����������б���������Ϣ
				Vector v = new Vector();
				v.add(rs.getString("Customid"));
				v.add(rs.getString("Cname"));
				v.add(rs.getString("CtelNumber"));
				v.add(rs.getString("CaddTime"));
				v.add(rs.getString("Cqqnumber"));
				massagetVector2.add(v);
			}

			CustomJTable2.updateUI();
			DbPro.disconnect();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "���ݲ�������", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Studio new.
	 * Ӱ¥���������
	 */
	public void StudioNew() {
		try {
			// ������ѯ����
			String sql = "SELECT * from Studio ";
			Dao DbPro = new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
//	 ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			// �Ա��ĸ��£���ʵ���Բ�Ҫ��һ����
			massagetVector1.clear();

			while (rs.next()) {// ����������б���������Ϣ
				Vector v = new Vector();
				v.add(rs.getString("Studioid"));
				v.add(rs.getString("StudioName"));
				v.add(rs.getString("Sadress"));
				v.add(rs.getString("PeopleNum"));
				v.add(rs.getString("Smassage"));
				massagetVector1.add(v);
			}

			CustomJTable1.updateUI();
			DbPro.disconnect();
		} catch (SQLException sqle) {
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null, "���ݲ�������", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Doctor new.
	 * ��Ӱʦ���������
	 */
	public void DoctorNew() {
		try {
			// ������ѯ����
			String sql = "SELECT * from DoctorInfo";

			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro2 = new Dao();
			DbPro2.connect();
			ResultSet rs = DbPro2.executeQuery1(sql);
//	 ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			// �Ա��ĸ��£���ʵ���Բ�Ҫ��һ����
			massagetVector3.clear();
			while (rs.next()) {// ����������б���������Ϣ

				Vector v = new Vector();
				v.add(rs.getString("Doctorid"));
				v.add(rs.getString("Dname"));
				v.add(rs.getString("DtelNumber"));
				v.add(rs.getString("Daddtime"));
				v.add(rs.getString("Dqqnumber"));
				v.add(rs.getString("Daddress"));
				v.add(rs.getString("Studioid"));

				massagetVector3.add(v);
			}
			table.updateUI();
			DbPro2.disconnect();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "���ݲ�������", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Renew.
	 * 3�������ݸ���
	 */
	public void Renew() {
		DoctorNew();
		CustomNew();
		StudioNew();
	}

	/**
	 * Find.
	 * ��������
	 */
	public void find() {
		String str = new String();// ����ֶ�
		String str2 = new String();// ��������
		int flag = 0;
		if (SelectQueryFieldStr.equals("��Ӱʦ")) {
			str = "DoctorInfo";
			flag = 1;
			if (SelectQueryFieldStr2.equals("�û���"))
				str2 = "Dname";
			else if (SelectQueryFieldStr2.equals("��ӰʦID"))
				str2 = "Doctorid";
			else if (SelectQueryFieldStr2.equals("Ӱ¥ID"))
				str2 = "Studioid";
		}

		else if (SelectQueryFieldStr.equals("�˿�")) {
			str = "Customer";
			flag = 2;
			if (SelectQueryFieldStr2.equals("�û���"))
				str2 = "Cname";
			else if (SelectQueryFieldStr2.equals("�˿�ID"))
				str2 = "Customid";

		}

		else if (SelectQueryFieldStr.equals("Ӱ¥")) {
			str = "Studio";
			flag = 3;
			if (SelectQueryFieldStr2.equals("Ӱ¥��"))
				str2 = "StudioName";
			else if (SelectQueryFieldStr2.equals("Ӱ¥ID"))
				str2 = "Studioid";
		}
		String sql = "SELECT * from " + str + " where " + str2 + " LIKE '%";
		sql = sql + jTFSNo.getText() + "%';";
		System.out.println("queryProcess(). sql = " + sql);
		Dao DbPro = new Dao();
		DbPro.connect();
		ResultSet rs = DbPro.executeQuery1(sql);
		// ��Ӱʦ
		if (flag == 1) {
			massagetVector3.clear();
			try {
				while (rs.next()) {// ����������б���������Ϣ
					Vector v = new Vector();
					v.add(rs.getString("Doctorid"));
					v.add(rs.getString("Dname"));
					v.add(rs.getString("DtelNumber"));
					v.add(rs.getString("Daddtime"));
					v.add(rs.getString("Dqqnumber"));
					v.add(rs.getString("Daddress"));
					v.add(rs.getString("Studioid"));
					massagetVector3.add(v);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			table.updateUI();
		}
		// �˿�
		if (flag == 2) {
			massagetVector2.clear();
			try {
				while (rs.next()) {// ����������б���������Ϣ
					Vector v = new Vector();
					v.add(rs.getString("Customid"));
					v.add(rs.getString("Cname"));
					v.add(rs.getString("CtelNumber"));
					v.add(rs.getString("CaddTime"));
					v.add(rs.getString("Cqqnumber"));
					massagetVector2.add(v);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			CustomJTable2.updateUI();
		}
		// Ӱ¥
		if (flag == 3) {
			massagetVector1.clear();
			try {
				while (rs.next()) {// ����������б���������Ϣ
					Vector v = new Vector();
					v.add(rs.getString("Studioid"));
					v.add(rs.getString("StudioName"));
					v.add(rs.getString("Sadress"));
					v.add(rs.getString("PeopleNum"));
					v.add(rs.getString("Smassage"));
					massagetVector1.add(v);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			CustomJTable1.updateUI();
		}
		DbPro.disconnect();
	}

	/**
	 * Newcombo box.
	 * �ֶ���������������
	 */
	public void NewcomboBox() {
		jTFSNo = new JTextField();
		jTFSNo.setColumns(20);
		jTFSNo.setBounds(275, 5, 150, 23);
		// �˿��ֶ�ѡ��������
		JComboBox<String> comboBox_Cus = new JComboBox<String>();
		comboBox_Cus.addItem(" ");
		comboBox_Cus.addItem("�˿�ID");
		comboBox_Cus.addItem("�û���");
		// �����������
		comboBox_Cus.addItemListener(new ItemListener() {// �������¼�����
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr2 = (String) event.getItem();// ѡ�е�����
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox_Cus.setSelectedIndex(0);
		comboBox_Cus.setBounds(190, 5, 80, 23);
		// ��Ӱʦ�ֶβ�ѯ
		JComboBox<String> comboBox_Doctor = new JComboBox<String>();
		comboBox_Doctor.addItem(" ");
		comboBox_Doctor.addItem("��ӰʦID");
		comboBox_Doctor.addItem("�û���");
		comboBox_Doctor.addItem("Ӱ¥ID");
		// �����������
		comboBox_Doctor.addItemListener(new ItemListener() {// �������¼�����
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr2 = (String) event.getItem();// ѡ�е�����
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox_Doctor.setSelectedIndex(0);
		comboBox_Doctor.setBounds(190, 5, 80, 23);
		// Ӱ¥�ֶβ�ѯ
		JComboBox<String> comboBox_Studio = new JComboBox<String>();
		comboBox_Studio.addItem(" ");
		comboBox_Studio.addItem("Ӱ¥ID");
		comboBox_Studio.addItem("Ӱ¥��");
		// �����������
		comboBox_Studio.addItemListener(new ItemListener() {// �������¼�����
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr2 = (String) event.getItem();// ѡ�е�����
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox_Studio.setSelectedIndex(0);
		comboBox_Studio.setBounds(190, 5, 80, 23);
		// �������ѡ��������
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("��Ӱʦ");
		comboBox.addItem("�˿�");
		comboBox.addItem("Ӱ¥");
		// �����������
		comboBox.addItemListener(new ItemListener() {// �������¼�����
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED:
					SelectQueryFieldStr = (String) event.getItem();// ѡ�е�����
					if (SelectQueryFieldStr.equals("��Ӱʦ")) {
						comboBox_Studio.setVisible(false);
						comboBox_Doctor.setVisible(true);
						comboBox_Cus.setVisible(false);
					}
					else if (SelectQueryFieldStr.equals("�˿�")) {
						comboBox_Studio.setVisible(false);
						comboBox_Doctor.setVisible(false);
						comboBox_Cus.setVisible(true);
					}
					else if (SelectQueryFieldStr.equals("Ӱ¥")) {
						comboBox_Studio.setVisible(true);
						comboBox_Doctor.setVisible(false);
					comboBox_Cus.setVisible(false);
					}
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(120, 5, 70, 23);
		jP3.add(comboBox);
		jP3.add(comboBox_Cus);
		jP3.add(comboBox_Doctor);
		jP3.add(comboBox_Studio);
		jP3.add(jTFSNo);
	}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//	 ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
		// �Ա��ĸ��£���ʵ���Բ�Ҫ��һ����
		if (e.getActionCommand().equals("�����ֶ�")) {
			find();
		} else if (e.getActionCommand().equals("ɾ���˺�") && (row >= 0)) {
			System.out.println("actionPerformed().ɾ���˺�");
			Delete();
			Renew();
		} else if (e.getActionCommand().equals("Ӱ¥���")) {
			register_Studio register = new register_Studio();
			register.setVisible(true);
		} else if (e.getActionCommand().equals("ˢ��")) {
			Renew();
		}
	}

	/**
	 * Delete.
	 */
	public void Delete() {
		String sql;
		String sql2;
		String sql3;
		Dao Dao1 = new Dao();
		Dao1.connect();
		if (whereFlag == 0) {
			// ��
		} else if (whereFlag == 1) {
			// �˿�
			sql = "DELETE from Customer where Customid=" + CustomJTable2.getValueAt(row, 0).toString();
			sql2="DELETE from Evaluation where Customid=" + CustomJTable2.getValueAt(row, 0).toString();
			int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ���˿���?", "ɾ���˿�", JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				Dao1.executeUpdate(sql);
				Dao1.executeUpdate(sql2);
			}
		} else if (whereFlag == 2) {
			// ��Ӱʦ
			sql = "DELETE from DoctorInfo where Doctorid=" + table.getValueAt(row, 0).toString();
			sql2="DELETE from Evaluation where Doctorid=" + table.getValueAt(row, 0).toString();
			sql3="DELETE from Comment where Doctorid=" + table.getValueAt(row, 0).toString();
			int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ����Ӱʦ��?", "ɾ����Ӱʦ", JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				Dao1.executeUpdate(sql);
				Dao1.executeUpdate(sql2);
				Dao1.executeUpdate(sql3);
			}
		} else if (whereFlag == 3) {
			// Ӱ¥
			int n = JOptionPane.showConfirmDialog(null, "ɾ��Ӱ¥��ͬʱɾ��Ӱ¥��¼����Ӱʦ�˺ţ�ȷ��Ҫɾ����?", "ɾ��Ӱ¥",
					JOptionPane.YES_NO_OPTION);
			sql = "DELETE from Studio where Studioid=" + CustomJTable1.getValueAt(row, 0).toString();
			sql2 = "DELETE from DoctorInfo where Studioid=" + CustomJTable1.getValueAt(row, 0).toString();
			if (n == 0) {
				Dao1.executeUpdate(sql);
				Dao1.executeUpdate(sql2);
			}
		}
		Dao1.disconnect();
	}
}