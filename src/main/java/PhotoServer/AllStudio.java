package PhotoServer;

import java.awt.BorderLayout;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import Dao.Dao;


    // TODO: Auto-generated Javadoc
/**
     * The Class AllStudio.
     *�鿴����Ӱ¥����Ϣ���
     * @author afsummer
     * @date 2020��7��3��
     */
    
public class AllStudio extends JFrame implements ActionListener {
	
	/** The j L studio info table. */
	// ִ�г���ʱ��ֱ�ӳ�ʼ��
	JLabel jLStudioInfoTable = null;// ѧ����Ϣ��jlabel�ñ�ǩ������ʾ���ڴ�ֱ��ˮƽ���ж��롣
	
	/** The jp 2. */
	JPanel  jp1, jp2 = null;
	
	/** The j P bottom. */
	JPanel jPTop, jPBottom = null;

/** The studio J scroll pane 1. */
//	private JScrollPane studentJScrollPane_2;
	private JScrollPane studioJScrollPane_1;// ��������
	
	/** The studio J table 1. */
	JTable studioJTable1 = null;
	
	/** The student vector 1. */
	Vector studentVector1 = null;
	
	/** The title vector 1. */
	// vector��ʵ���˶�̬���飬����Ԫ�������仯�Ķ������顣������һ��
	Vector titleVector1 = null;

	/** The row. */
	int row = -1;
	
	/** The col. */
	int col = -1;
	
	/** The j B out. */
	private JButton jBOut;
	
	/** The button 1. */
	private JButton button_1;
	
	/** The Cus ID. */
	String CusID = "";

	/**
	 * Instantiates a new all studio.
	 *ҳ�������ʼ��
	 * @param ID the id
	 */
	// ���캯��
	public AllStudio(String ID) {
		// �������
		CusID = ID;
		jLStudioInfoTable = new JLabel("Ӱ¥��Ϣ��");
		jLStudioInfoTable.setFont(new Font("����", Font.PLAIN, 20));
		jLStudioInfoTable.setBounds(300,10,120,20);
		// �����ͷ

		studentVector1 = new Vector();// ��� ����
		titleVector1 = new Vector();
		// �����ͷ


		jp1 = new JPanel();
		jp1.setBounds(20, 40, 650, 510);
		jPTop = new JPanel();
		jPTop.setBounds(0, 0, 700, 500);
		jPBottom = new JPanel();
		jPBottom.setBounds(10, 500, 700, 150);


		
		jp1.setLayout(null);
		jPTop.setLayout(null);
		// ��һ�Ŷ����������Ϣ�������
		jPTop.add(jLStudioInfoTable);
		jPTop.add(jp1);
		

		titleVector1.add("Ӱ¥ID");
		titleVector1.add("Ӱ¥����");
		titleVector1.add("Ӱ¥��ַ");
		titleVector1.add("Ӱ¥����");
		titleVector1.add("Ӱ¥��Ϣ");

		studioJTable1 = new JTable(studentVector1, titleVector1){
			public boolean isCellEditable(int rowIndex,int ColIndex){
				return false;
			}
		};// ���Ĵ���
		studioJTable1.setRowHeight(50);//���ñ��߶�
//			studioJTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//��ֹ����Ӧ���ͻῪ��������
	studioJScrollPane_1 = new JScrollPane(studioJTable1);
		studioJScrollPane_1.setBounds(0, 0, 650, 450);
		jp1.add(studioJScrollPane_1);
		// �ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
		studioJScrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		studioJScrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jPBottom.setLayout(null);

		studioJTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
			     
         		 row=studioJTable1.rowAtPoint(event.getPoint());
				 col=studioJTable1.columnAtPoint(event.getPoint());
				System.out.println("��:"+row+"��"+col);//ע����ʼ����
				//��ȡָ���к��е�ֵ
				System.out.println("����:"+studioJTable1.getValueAt(row,col).toString());
				if(2==event.getClickCount()){//˫���¼�
					Studio studio=new Studio(studioJTable1.getValueAt(row,0).toString(),CusID);
					studio.setVisible(true);
				
					//��ȡָ���к��е�ֵ
				}				
			}
		});
		this.getContentPane().setLayout(null);
		// ��Ӷ��������͵ײ����������ݴ���
		this.getContentPane().add(jPTop);
		this.getContentPane().add(jPBottom);
		// �����Ű�ť������
		// �����Ű�ť���
		jp2 = new JPanel();
		jp2.setBounds(0, 0, 670, 80);
		jPBottom.add(jp2);
		jp2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jp2.setLayout(null);
		jp2.setPreferredSize(new Dimension(20, 20));

		button_1 = new JButton("Ӱ¥��ϸ��ѯ");
		button_1.setBounds(180, 10, 138, 30);
		button_1.addActionListener(this);

		jBOut = new JButton("����");
		jBOut.setBounds(350, 10, 138, 30);
		jBOut.addActionListener(this);
		jBOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				setVisible(false);
			}
		});
		
		// �����Ű�ť������
		jp2.add(button_1);
		jp2.add(jBOut);

		this.setTitle("Ӱ¥ҳ��");
		this.setSize(700, 650);
		this.setLocation(600, 150);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

		// ��¼�û���Ϣ����ڶ��ű����
		try {
			// ������ѯ����
			String sql = "SELECT * from Studio ";
			System.out.println("queryProcess(). sql = " + sql);
			Dao DbPro = new Dao();
			DbPro.connect();
			ResultSet rs = DbPro.executeQuery1(sql);
//		 ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			// �Ա��ĸ��£���ʵ���Բ�Ҫ��һ����
			studentVector1.clear();
			while (rs.next()) {// ����������б���������Ϣ
				Vector v = new Vector();
				v.add(rs.getString("Studioid"));
				v.add(rs.getString("StudioName"));
				v.add(rs.getString("Sadress"));
				v.add(rs.getString("PeopleNum"));
				v.add(rs.getString("Smassage"));
				studentVector1.add(v);
			}
			studioJTable1.updateUI();
			DbPro.disconnect();
		} catch (SQLException sqle) {
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null, "���ݲ�������", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	    /* (�� Javadoc)
	    * �԰�ť���¼�Ӧ��
	    * 
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Ӱ¥��ϸ��ѯ")) {
			System.out.println("actionPerformed(). Ӱ¥��ϸ��ѯ");
			if(row>=0) {
			Studio studio=new Studio(studioJTable1.getValueAt(row,0).toString(),CusID);
			studio.setVisible(true);
			
			}
//			Reward();

		}  

	}
}