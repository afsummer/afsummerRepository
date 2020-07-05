package PhotoServer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Dao.Dao;

// TODO: Auto-generated Javadoc
/**
 * The Class register_Studio.
 *Ӱ¥ע�����
 * @author afsummer
 * @version  v1.0
 * @date 2020-7-4
 */
public class register_Studio extends JFrame implements ActionListener {
	
	/** The content pane. */
	public JPanel contentPane;
	
	/** The Studio massage. */
	private static JTextArea StudioMassage;
	
	/** The Stuid 2. */
	private JTextField Stuid2 = null;
	
	/** The Name 2. */
	private JTextField Name2 = null;
	
	/** The adress 2. */
	private JTextField adress2 = null;
	
	/** The People num 2. */
	private JTextField PeopleNum2 = null;
	
	/** The row. */
	private int row = -1;
	
	/** The col. */
	private int col = -1;

	/**
	 * Instantiates a new register studio.
	 */
	public register_Studio() {
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 300, 560, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setTitle("Ӱ¥ע��");
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 600, 500);
		panel.setLayout(null);

		

//				Stuid2 = new JTextField();
				Name2 = new JTextField();
				adress2 = new JTextField();
//				PeopleNum2 = new JTextField();
				StudioMassage = new JTextArea();

//		JLabel ID = new JLabel("Ӱ¥ID��");
//		ID.setFont(new Font("����", Font.PLAIN, 14));
//		ID.setBounds(30, 20, 87, 20);

		JLabel Name = new JLabel("Ӱ¥����  ��");
		Name.setFont(new Font("����", Font.PLAIN, 14));
		Name.setBounds(30, 60, 87, 20);
		JLabel address = new JLabel("Ӱ¥��ַ��");
		address.setFont(new Font("����", Font.PLAIN, 14));
		address.setBounds(30, 100, 87, 20);
//		JLabel peoplenum = new JLabel("Ӱ¥������");
//		peoplenum.setFont(new Font("����", Font.PLAIN, 14));
//		peoplenum.setBounds(30, 140, 100, 20);
		JLabel Studiomassage = new JLabel("Ӱ¥��Ϣ��");
		Studiomassage.setFont(new Font("����", Font.PLAIN, 14));
		Studiomassage.setBounds(30, 140, 87, 20);

//		Stuid2.setFont(new Font("����", Font.PLAIN, 14));
//		Stuid2.setBounds(130, 20, 87, 20);

		Name2.setFont(new Font("����", Font.PLAIN, 14));
		Name2.setBounds(130, 60, 120, 20);
		//
		adress2.setFont(new Font("����", Font.PLAIN, 14));
		adress2.setBounds(130, 100, 120, 20);
		//
//		PeopleNum2.setFont(new Font("����", Font.PLAIN, 14));
//		PeopleNum2.setBounds(130, 140, 95, 20);

		// ����������

		StudioMassage.setBounds(130,150, 300, 180);
		StudioMassage.setLineWrap(true);// ������ݹ������Զ����У����ı�����Ϲ�������ˮƽ�ʹ�ֱ������ʼ�ճ��֡�
		StudioMassage.setEditable(true);//
		// ��Ӱʦ���
		

		// ע�ᰴť

		// �˳���ť
		JButton button_1 = new JButton("����");
		button_1.setFont(new Font("����", Font.PLAIN, 15));
		button_1.setBounds(400, 430, 120, 23);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		JButton button_2 = new JButton("ȷ��ע��");
		button_2.setFont(new Font("����", Font.PLAIN, 15));
		button_2.setBounds(400, 400, 120, 23);
		button_2.addActionListener(this);

		// ҳ������
		contentPane.add(panel);
		// ������տ�����
//		panel.add(Stuid2);
		panel.add(Name2);
		panel.add(adress2);
//		panel.add(PeopleNum2);
		panel.add(Studiomassage);
		panel.add(Name);
		panel.add(address);
		// ����ı���
		panel.add(StudioMassage);

		// ��ť��
		panel.add(button_1);
		panel.add(button_2);
	}
	
	/**
	 * Strchange.
	 *�����ı���Ļس���ת��ת�������ݿ��ʶ�����ʽ��
	 * @param str the str
	 * @return the string
	 */
	public String strchange(String str) {
		StringBuilder sb = new StringBuilder();
		String[] sourceStrArray = str.split("\n");// �ָ�������ַ�����
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
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param arg0
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("ȷ��ע��")&& !Name2.getText().isEmpty() && !adress2.getText().isEmpty()) {
			boolean flag = false;
			if (Name2.getText().trim().contains(" ")) {
				JOptionPane.showMessageDialog(null, "���ƺ��пո�", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
				flag = true;
			} else if (adress2.getText().trim().contains(" ")) {
				JOptionPane.showMessageDialog(null, "��ַ���пո�", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
				flag = true;
			}
			if ( !flag) {
				
					String tempfield1 = Name2.getText().trim();
					String tempfield2 = adress2.getText().trim();
					String tempfield3 = "NULL";
					if (!StudioMassage.getText().isEmpty()) {
						
						tempfield3 = StudioMassage.getText().trim();
						if (!tempfield3.equals(""))
							tempfield3 = strchange(tempfield3);
					}

					String sql = "INSERT into Studio(StudioName,Sadress,Smassage) values('"
							+ tempfield1 + "','" +tempfield2+"','"+ tempfield3 +")";

					Dao Dao1 = new Dao();
					Dao1.connect();
					int sure = Dao1.insert(sql);
					if (sure > 0) {
						String sql2 = "SELECT TOP 1 Studioid FROM Studio order by Studioid desc";
						ResultSet rs = Dao1.executeQuery1(sql2);
						try {
							while(rs.next())
							JOptionPane.showMessageDialog(null, "ע��ɹ���\n���ID��"+rs.getString("Studioid"));
						} catch (HeadlessException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dispose();
					}

				}
			}
		}



}
