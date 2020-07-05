package PhotoServer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Dao.Dao;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class Customers.
 *�˿���ҳ��
 * @author afsummer
 * @version  v1.0
 * @date 2020-7-4
 */
public class Customers extends JFrame implements ActionListener {
	
	/** The j L custom info table. */
	// ִ�г���ʱ��ֱ�ӳ�ʼ��
	JLabel jLCustomInfoTable = null;// ѧ����Ϣ��jlabel�ñ�ǩ������ʾ���ڴ�ֱ��ˮƽ���ж��롣
	
	/** The eva excel. */
	File evaExcel;

	/** The j bprint all. */
	JButton jBprintAll = null;// ��ѯ���м�¼

	/** The j P 4. */
	JPanel jP1, jP2, jP3, jP4 = null;
	
	/** The j P bottom. */
	JPanel jPTop, jPBottom = null;

	/** The Custom J scroll pane 1. */
	private JScrollPane CustomJScrollPane_1;// ��������
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The Custom J table 1. */
	JTable CustomJTable1 = null;
	
	/** The title vector. */
	Vector titleVector = null;
	
	/** The title vector 1. */
	Vector titleVector1 = null;
	
	/** The title vector 3. */
	Vector titleVector3 = null;
	
	/** The Vector. */
	Vector Vector = null;// vector��ʵ���˶�̬���飬����Ԫ�������仯�Ķ������顣������һ��
	
	/** The massage vector 1. */
	Vector massageVector1 = null;
	
	/** The massage vector 2. */
	// vector��ʵ���˶�̬���飬����Ԫ�������仯�Ķ������顣������һ��
	Vector massageVector2 = null;

	/** The row. */
	int row = -1;
	
	/** The col. */
	int col = -1;

	/** The j B out. */
	private JButton jBOut;
	
	/** The button 1. */
	private JButton button_1;
	
	/** The button 2. */
	private JButton button_2;
	
	/** The button new. */
	private JButton buttonNew;
	
	/** The Cus ID. */
	private String CusID = "1801";
	
	/** The table. */
	private JTable table;// ���
	
	/** The Amend. */
	private Customer_amend Amend;
	
	/** The file excel. */
	private File fileExcel;

	/**
	 * Instantiates a new customers.
	 *ҳ���ʼ��
	 * @param ID the id
	 */
	// ���캯��
	public Customers(String ID) {
		// �������
		CusID = ID;
		fileExcel= new File("C:\\Users\\afsummer\\Desktop\\test.xls");
		jLCustomInfoTable = new JLabel("������Ϣ��");

		// �����ͷ
		massageVector1 = new Vector();// ��� ����
		massageVector2 = new Vector();// ����
		titleVector1 = new Vector();
		titleVector3 = new Vector();
		// �����ͷ

		jP1 = new JPanel();
		jP1.setBounds(212, 5, 70, 25);
		jP2 = new JPanel();
		jP2.setBounds(20, 40, 462, 83);
		jPTop = new JPanel();
		jPTop.setBounds(0, 0, 494, 142);
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

		titleVector1.add("�˿�ID");
		titleVector1.add("�û���");
		titleVector1.add("�绰����");
		titleVector1.add("ע��ʱ��");
		titleVector1.add("QQ����");

		CustomJTable1 = new JTable(massageVector1, titleVector1) {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};// ���Ĵ���
//			studentJTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//��ֹ����Ӧ���ͻῪ��������
//		studentJTable1.setPreferredScrollableViewportSize(new Dimension(455, 150));
		CustomJScrollPane_1 = new JScrollPane(CustomJTable1);
		CustomJScrollPane_1.setBounds(0, 0, 455, 43);
		jP2.add(CustomJScrollPane_1);
		// �ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
		CustomJScrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		CustomJScrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jPBottom.setLayout(null);

		CustomJTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				int row = CustomJTable1.rowAtPoint(event.getPoint());
				int col = CustomJTable1.columnAtPoint(event.getPoint());
				if (2 == event.getClickCount()) {// ˫���¼�
					CustomJTable1.getValueAt(row, col).toString();
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

		titleVector3.add("��¼ID");
		titleVector3.add("ԤԼ�˿�ID");
		titleVector3.add("ԤԼ��ӰʦID");
		titleVector3.add("��¼ʱ��");
		titleVector3.add("ԤԼ���");

		// studentVector2
//ԤԼ����� 
		table = new JTable(massageVector2, titleVector3) {
			public boolean isCellEditable(int rowIndex, int ColIndex) {
				return false;
			}
		};// ���Ĵ���
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ��ֹ����Ӧ���ͻῪ��������
		table.setPreferredScrollableViewportSize(new Dimension(455, 150));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 10, 455, 127);
		// studentJScrollPane.setBounds(0, 0, 462, 83);
		// �ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//ˮƽ
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);// ��ֱ
		// Ϊ�����Ӽ�����
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				row = table.rowAtPoint(event.getPoint());
				col = table.columnAtPoint(event.getPoint());
				// ��ȡָ���к��е�ֵ
				if (2 == event.getClickCount()) {// ˫���¼�
					EvalMassage EvalMassages = new EvalMassage(table.getValueAt(row, 0).toString());
					EvalMassages.setVisible(true);
				}
			}
		});
		panel.add(scrollPane);

		// �����Ű�ť������
		jBprintAll = new JButton("ԤԼ���ӡ");
		jBprintAll.setBounds(330, 5, 120, 23);
		jBprintAll.addActionListener(this);

		button_2 = new JButton("������Ϣ�޸�");
		button_2.setBounds(30, 5, 116, 23);
		button_2.addActionListener(this);
		jP3 = new JPanel();
		jP3.setBounds(10, 152, 474, 32);
		jP3.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(jP3);
		jP3.setLayout(null);
		jP3.add(jBprintAll);
		jP3.setPreferredSize(new Dimension(20, 20));
		jP3.add(button_2);

		// �����Ű�ť���
		jP4 = new JPanel();
		jP4.setBounds(0, 162, 474, 38);
		jPBottom.add(jP4);
		jP4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jP4.setLayout(null);
		jP4.setPreferredSize(new Dimension(20, 20));

		JButton button = new JButton("ԤԼ��������ѯ");
		button.addActionListener(this);
		button.setBounds(22, 10, 138, 23);

		button_1 = new JButton("Ӱ¥��ѯ");
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

		this.setTitle("�˿�ҳ��");
		this.setSize(500, 451);
		this.setLocation(150, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		// ��¼�û���Ϣ����ڶ��ű����
		IDNew();
		// �����ű������ԤԼ���
		EvaNew();
		reNew();
	}

	/**
	 * Export table.
	 *��ԤԼ�������ݵ�����ָ��Excel�ĵ�
	 * @param table the table
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void exportTable(JTable table, File file) throws IOException {
		TableModel model = table.getModel();
		BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < model.getColumnCount(); i++) {
			bWriter.write(model.getColumnName(i));
			bWriter.write("\t");
		}
		bWriter.newLine();
		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 0; j < model.getColumnCount(); j++) {
				bWriter.write(model.getValueAt(i, j).toString());
				bWriter.write("\t");
			}
			bWriter.newLine();
		}
		bWriter.close();
	}

	/**
	 * ID new.
	 * ������Ϣ�����
	 */
	public void IDNew() {
		try {
			// ������ѯ����
			String sql = "SELECT * from Customer where Customid=";
			sql = sql + CusID + ";";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro = new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
//		 ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			// �Ա��ĸ��£���ʵ���Բ�Ҫ��һ����
			massageVector1.clear();
			while (rs.next()) {// ����������б���������Ϣ
				Vector v = new Vector();
				v.add(rs.getString("Customid"));
				v.add(rs.getString("Cname"));
				v.add(rs.getString("CtelNumber"));
				v.add(rs.getString("CaddTime"));
				v.add(rs.getString("Cqqnumber"));
				massageVector1.add(v);
			}
			CustomJTable1.updateUI();
			DbPro.disconnect();
		} catch (SQLException sqle) {
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null, "���ݲ�������", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Eva new.
	 * ԤԼ����Ϣ����
	 */
	public void EvaNew() {
		try {
			// ������ѯ����
			String sql = "SELECT * from Evaluation where Customid=";
			sql = sql + CusID + ";";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro2 = new Dao();
			DbPro2.connect();
			ResultSet rs = DbPro2.executeQuery1(sql);
//		 ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			// �Ա��ĸ��£���ʵ���Բ�Ҫ��һ����
			massageVector2.clear();
			while (rs.next()) {// ����������б���������Ϣ
				Vector v2 = new Vector();
				v2.add(rs.getString("EvaId"));
				v2.add(rs.getString("Customid"));
				v2.add(rs.getString("Doctorid"));
				v2.add(rs.getString("EaddTime"));
				if (rs.getString("EvaFlag").equals("-1"))
					v2.add("ԤԼȡ��");
				else if (rs.getString("EvaFlag").equals("0"))
					v2.add("ԤԼ��");
				else if (rs.getString("EvaFlag").equals("1"))
					v2.add("ԤԼ�ɹ�");
				massageVector2.add(v2);
			}
			table.updateUI();
			DbPro2.disconnect();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "���ݲ�������", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Re new.
	 * ���б����
	 */
	public void reNew() {
		IDNew();
		EvaNew();
	}

	
	    /* (�� Javadoc)
	    * 
	    * ���а�ť�¼���Ӧ��
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Ӱ¥��ѯ")) {

			AllStudio newStudio = new AllStudio(CusID);
			newStudio.setVisible(true);

		} else if (e.getActionCommand().equals("ԤԼ��������ѯ") && (row >= 0)) {
			System.out.println("actionPerformed(). ԤԼ��������ѯ");
			EvalMassage EvalMassages = new EvalMassage(table.getValueAt(row, 0).toString());
			EvalMassages.setVisible(true);

		} else if (e.getActionCommand().equals("ԤԼ���ӡ")) {
			exportExcel();
		} else if (e.getActionCommand().equals("������Ϣ�޸�")) {
			System.out.println("actionPerformed(). ������Ϣ�޸�");
			Amend = new Customer_amend(CusID);
			Amend.setVisible(true);

		} else if (e.getActionCommand().equals("ˢ��")) {
			reNew();
		}
	}

	/**
	 * Export excel.
	 * ԤԼ������ݵ��뵽ָ��Excel���
	 * 
	 */
	public void exportExcel() {
		WritableWorkbook workBook = null;
		try {
			// �������ļ�fileExcel
	
			// ����WritableWorkbook
			workBook = Workbook.createWorkbook(fileExcel);
			// ����sheet
			WritableSheet sheet = workBook.createSheet("ԤԼ����Ϣ", 0);// ����һ��ѧ����Ϣ��sheetҳ

			// ����excelͷ��Ϣ
			/*
			 * ����1����ʾ��Label��excel��x���� ����2����ʾ��Label��excel��y���� ����3����ʾ��label������
			 */
			for (int i = 0; i < titleVector3.size(); i++) {
				Label Labels = new Label(i, 0, titleVector3.get(i).toString());
				sheet.addCell(Labels);
			}

			try {
				// ������ѯ����
				String sql = "SELECT * from Evaluation where Customid=";
				sql = sql + CusID + ";";
				System.out.println("queryProcess(). sql = " + sql);
				Dao DbPro2 = new Dao();
				DbPro2.connect();
				ResultSet rs = DbPro2.executeQuery1(sql);
				int j = 1;
				while (rs.next()) {
					Label EvaLabels2 = new Label(0, j, rs.getString("EvaId"));
					Label CusLabels2 = new Label(1, j, rs.getString("Customid"));
					Label DocLabels2 = new Label(2, j, rs.getString("Doctorid"));
					Label EadLabels2 = new Label(3, j, rs.getString("EaddTime"));
					Label flagLabels2 = null;
					if (rs.getString("EvaFlag").equals("-1"))
						flagLabels2 = new Label(4, j, "ԤԼȡ��");
					else if (rs.getString("EvaFlag").equals("0"))
						flagLabels2 = new Label(4, j, "ԤԼ��");
					else if (rs.getString("EvaFlag").equals("1"))
						flagLabels2 = new Label(4, j, "ԤԼ�ɹ�");
					sheet.addCell(EvaLabels2);
					sheet.addCell(CusLabels2);
					sheet.addCell(DocLabels2);
					sheet.addCell(EadLabels2);
					sheet.addCell(flagLabels2);
					j++;
				}
//		 ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
				// �Ա��ĸ��£���ʵ���Բ�Ҫ��һ����
				DbPro2.disconnect();
			} catch (SQLException sqle) {
				JOptionPane.showMessageDialog(null, "���ݲ�������", "����", JOptionPane.ERROR_MESSAGE);
			}

			// ��ѯ���ݿ������

			// д������
			workBook.write();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				workBook.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
