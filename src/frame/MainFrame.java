package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mr.contact.dao.Dao;
import com.mr.contact.dao.DaoFactory;
import com.mr.contact.swing.ContactFrame;
import com.mr.contact.swing.FixedTable;

import pojo.Customer;
import pojo.User;

/**
 * ������
 *
 */
public class MainFrame extends ContactFrame {

	static private User user;// ��ǰ��¼�û�
	private DefaultTableModel tableModel; // ������ģ�Ͷ���
	private FixedTable table;// ͨѶ¼���
	private Dao dao;// ���ݿ�ӿ�
	private JButton surch_btn;// ��ѯ��ť
	private JButton add_btn;// ��Ӱ�ť
	private JButton update_btn;// �޸İ�ť
	private JButton del_btn;// ɾ����ť
	private JMenuItem[] charItem;// ����ͼ�˵�����
	private JMenuItem accountMenu;// �û�����˵�
	private JMenuItem revisedPwdMenu;// �޸�����˵�
	private JComboBox<String> surchType_combo;// ģ���ؼ��ַ�Χ������
	private JTextField fuzzySurch_t;// ģ����ѯ�ؼ��������

	/**
	 * ���췽��
	 */
	public MainFrame() {
		setTitle("ͨѶ¼ϵͳ");// �������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �رմ���ͬʱֹͣ����
		Toolkit tool = Toolkit.getDefaultToolkit();// ����ϵͳ��Ĭ��������߰�
		Dimension d = tool.getScreenSize();// ��ȡ��Ļ�ߴ磬����һ����ά�������
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);// ������������Ļ�м���ʾ
		init();// �����ʼ��
		validate();// ���¼������
		addAction();// �����������
		setVisible(true);// ����ɼ�
		getCustomerBirth(); // ������������
	}// MainFrame()����

	/**
	 * �����ʼ��
	 */
	protected void init() {
		super.init();
		dao = DaoFactory.getDao();// ʵ�������ݿ�ӿ�

		charItem = getCharItem();
		revisedPwdMenu = getRevisedPwdMenu();// �޸�����˵���
		surchType_combo = getSurchType_combo();// ģ����ѯ�ؼ���������
		fuzzySurch_t = getFuzzySurch_t();// ģ����ѯ�����
		surch_btn = getSurch_btn();// ������ť
		table = getTable(); // ����ָ�����ģ�͵ı��
		table.setCellEditable(false);// �ñ�񲻿ɱ༭
		initTable();// ��ʼ���������

		add_btn = new JButton("���");// ��Ӱ�ť
		update_btn = new JButton("�޸�");// �޸İ�ť
		del_btn = new JButton("ɾ��");// ɾ����ť
		accountMenu = new JMenuItem("�û�����");// �˻��˵�
		if (user.getStatus().equals(User.ADMIN)) {// ����ǹ���Ա���
			JPanel bottomPanel = new JPanel();// �����ײ���ť���
			FlowLayout bottomLayout = new FlowLayout();// ����������
			bottomLayout.setHgap(20);// ����20����
			bottomLayout.setAlignment(FlowLayout.RIGHT);// �Ҷ���
			bottomPanel.setLayout(bottomLayout);// ����������
			bottomPanel.add(add_btn);// �ײ������Ӱ�ť
			bottomPanel.add(update_btn);// �ײ������Ӱ�ť
			bottomPanel.add(del_btn);// �ײ������Ӱ�ť
			getContentPane().add(bottomPanel, BorderLayout.SOUTH);// ��ӵײ����ŵ�����������λ��
			JMenu userMenu = getUserMenu();
			userMenu.add(accountMenu);// ϵͳ�˵�����û�����˵���
		}// if����
	}// init()����

	/**
	 * ����������
	 */
	private void addAction() {
		table.addMouseListener(new MouseAdapter() {// ����������¼�����
			public void mouseClicked(MouseEvent e) {// �������ʱ
				if (e.getClickCount() == 2) {// ��������˫���¼�
					// ���ѡ���еĵ�һ�����ݣ���ֵΪid
					String id = (String) table.getValueAt(
							table.getSelectedRow(), 0);
					Customer cust = dao.selectCustomer(Integer.parseInt(id));// ��ȡ��id�ĳ־û��ͻ���Ϣ����
					cust.setId(Integer.parseInt(id));// ��idֵת��intֵ�������ͻ�����
					ShowInfoFrame info = new ShowInfoFrame(cust, MainFrame.this);// ����ϸ��Ϣչʾ����
					info.setVisible(true);// ���ڿɼ�
				} // if����
			}// mouseClicked()����
		});// addMouseListener()����

		del_btn.addActionListener(new ActionListener() {// ɾ����ť����¼�����
			public void actionPerformed(ActionEvent e) {// �����ʱ
				int rowindex = table.getSelectedRow();// ��ȡ��ǰ���ѡ�е�������
				if (rowindex > -1) {// �����ѡ�е�ֵ
					// ������ʾ�Ի�����ʾ�ͻ����֣���ȡ�û��������صĽ��
					int i = JOptionPane.showConfirmDialog(MainFrame.this,
							"ȷ���Ƿ�ɾ��" + table.getValueAt(rowindex, 1) + "?",
							"ע�⣡", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {// ���ѡ��yes
						Customer del = new Customer();// �����ͻ�����
						String id = (String) table.getValueAt(rowindex, 0);// ��ȡ��ɾ���Ŀͻ�id
						del.setId(Integer.parseInt(id));// ���ÿͻ�id
						dao.deleteCustomer(del, user);// ���ͻ���Ϣɾ��
						tableModel.removeRow(table.getSelectedRow());// ���ɾ��ѡ���д���
					}// if����
				}// if����
			}// actionPerformed()����
		});// del_btn.addActionListener()����

		update_btn.addActionListener(new ActionListener() {// ���°�ť���ʱ�����
					public void actionPerformed(ActionEvent e) {// �����ʱ
						int rowindex = table.getSelectedRow();// ��ȡ��ǰ���ѡ�е�������
						if (rowindex > -1) {// �����ѡ�е�ֵ
							String id = (String) table.getValueAt(rowindex, 0);// ��ȡ����һ�е�ֵ��Ϊid
							// ��ȡ���ݿ������id��ͬ�Ŀͻ�����
							Customer update = dao.selectCustomer(Integer
									.parseInt(id));
							// �����ͻ��޸Ĵ���
							UpdateCustomerFrame show = new UpdateCustomerFrame(
									update, MainFrame.this);
							show.setVisible(true);// ��ͻ��޸Ĵ��ڿɼ�
						}// if����
					}// actionPerformed()����
				});// update_btn.addActionListener()����

		add_btn.addActionListener(new ActionListener() {// ��Ӱ�ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
				// ������ӿͻ�����
				AddCustomerFrame add = new AddCustomerFrame(MainFrame.this);
				add.setVisible(true);// ��ӿͻ����ڿɼ�
			}// actionPerformed()����
		});// add_btn.addActionListener()����

		surch_btn.addActionListener(new ActionListener() {// �������o��Ӽ���
					public void actionPerformed(ActionEvent e) {// �����ʱ
						int comboSelect = surchType_combo.getSelectedIndex();// ��ȡ��������ѡ�е�����
						String fuzzyName = fuzzySurch_t.getText().trim();// ��ȡ����Ĺؼ���
						if ("".equals(fuzzyName)) {// ����ؼ���Ϊ��
							tableModel = getUsableModleSoure();// �������пͻ���Ϣ
						}// if����
						if (comboSelect == 0) {// ���ѡ�е��ǵ�һ�������
							tableModel = getfuzzySurchModleSoure(fuzzyName);// ������������ģ����ѯ
						}// if����
						table.setModel(tableModel);// ���±������
					}// actionPerformed()����
				});// surch_btn.addActionListener()����

		charItem[0].addActionListener(new ActionListener() {// ��״ͼ��ť�¼�
					public void actionPerformed(ActionEvent e) {// �����ʱ
						// ����ͼ�괰�壬ѡ��չʾ��״ͼ
						ChartFrame pieFrame = new ChartFrame(MainFrame.this,
								ChartFrame.PIE_FRAME);
						pieFrame.setVisible(true);// ����ɼ�
					}// actionPerformed()����
				});// charItem[0].addActionListener()����

		charItem[1].addActionListener(new ActionListener() {// ��״ͼ��ť�¼�
					public void actionPerformed(ActionEvent e) {// �����ʱ
						// ����ͼ�괰�壬ѡ��չʾ��״ͼ
						ChartFrame barFrame = new ChartFrame(MainFrame.this,
								ChartFrame.BAR_FRAME);
						barFrame.setVisible(true);// ����ɼ�
					}// actionPerformed()����
				});// charItem[1].addActionListener()����

		accountMenu.addActionListener(new ActionListener() {// �û�����ť��Ӷ�������
					public void actionPerformed(ActionEvent e) {// �����ʱ
						// �����û�������
						AdminUserInterfaceFrame admin = new AdminUserInterfaceFrame(
								MainFrame.this);
						admin.setVisible(true);// �û������ڿɼ�
					}// actionPerformed()����
				});// accountMenu.addActionListener()����

		revisedPwdMenu.addActionListener(new ActionListener() {// �޸����밴ť��Ӷ�������
					public void actionPerformed(ActionEvent e) {// �����ʱ
						// �����޸����봰��
						RevisedPwdFrame revis = new RevisedPwdFrame(
								MainFrame.this);
						revis.setVisible(true);// �޸����봰�ڿɼ�
					}// actionPerformed()����
				});// revisedPwdMenu.addActionListener()����
	}// addAction()����

	/**
	 * ��ȡ���������ջ��Ѿ������յĿͻ�
	 */
	private void getCustomerBirth() {
		StringBuilder str = new StringBuilder();// ������ʾ��Ϣ�ַ���
		List<Customer> today = dao.selectAllCustomerBirthToday();// ��ȡ��������յĿͻ��б�
		if (today.size() > 0) {// ������˽��������
			str.append("�������˹�����Ŷ��");// ����ַ�����
			str.append("\n�������ǣ�");// ����ַ�����
			for (Customer c : today) {// ������������յĿͻ�
				str.append(c.getName() + " ");// ��ӿͻ�����
			}// for����
			str.append("\n\n");// �����������
		}// if����

		List<Customer> tomorrow = dao.selectAllCustomerBirthTomorrow();// ��ȡ��������տͻ��б�
		if (tomorrow.size() > 0) {// ����������������
			str.append("���к�������Ҫ��������Ŷ��");// ����ַ�����
			str.append("\n��������ǣ�");// ����ַ�����
			for (Customer c : tomorrow) {// ������������յĿͻ�
				str.append(c.getName() + " ");// ��ӿͻ�����
			}// for����
		}// if����
		if (str.length() > 0) {// �����ʾ��Ϣ���ǿյ�
			// �����Ի���չʾ��ʾ��Ϣ
			JOptionPane.showMessageDialog(MainFrame.this, str.toString());
		}// if����
	}// getCustomerBirth()����

	/**
	 * ��ѯ������Ч�ͻ���Ϣ
	 * 
	 * @return �������ģ��
	 */
	private DefaultTableModel getUsableModleSoure() {
		List<Customer> usableList = dao.selectUsableCustomer();// ��ȡ������Ч�ͻ�
		return assembledModleSoure(usableList);// ������������Ч�ͻ��������ģ��
	}// getUsableModleSoure()����

	/**
	 * ģ����ѯ
	 * 
	 * @param name
	 *            - �ؼ���
	 * @return �������ģ��
	 */
	private DefaultTableModel getfuzzySurchModleSoure(String name) {
		// ���������ؼ��ֻ�ȡ������Ч�ͻ�
		List<Customer> usableList = dao.selectCustomer(name);
		return assembledModleSoure(usableList);// ���ؿͻ��������ģ��
	}// getfuzzySurchModleSoure()����

	/**
	 * ���ݲ�ͬ�Ŀͻ����ϣ���ȡ��Ӧ�ı������ģ��
	 * 
	 * @param usableList
	 *            - �ͻ�����
	 * @return �������ģ��
	 */
	private DefaultTableModel assembledModleSoure(List<Customer> usableList) {
		int customerCount = usableList.size();// ��ȡ���ϵĿͻ�����
		String[] columnNames = { "���", "����", "�Ա�", "��������", "������λ", "ְλ",
				"�����ص�", "��ͥסַ" }; // ��������������
		String[][] tableValues = new String[customerCount][8];// ���������������
		for (int i = 0; i < customerCount; i++) {// �������������
			Customer cust = usableList.get(i);// ��ȡ���û�����
			tableValues[i][0] = "" + cust.getId();// ��һ��Ϊ���
			tableValues[i][1] = cust.getName();// �ڶ���Ϊ����
			tableValues[i][2] = cust.getSex();// ������Ϊ�Ա�
			tableValues[i][3] = cust.getBirth();// ������Ϊ��������
			tableValues[i][4] = cust.getWork_unit();// ������Ϊ��˾����
			tableValues[i][5] = cust.getRole();// ����λΪְλ
			tableValues[i][6] = cust.getWork_addr();// ������Ϊ��˾��ַ
			tableValues[i][7] = cust.getHome_addr();// �ڰ���Ϊ��ͥסַ
		} // for����
			// ��������������������鴴���������ģ��
		DefaultTableModel tmp = new DefaultTableModel(tableValues, columnNames);
		return tmp;
	}//

	/**
	 * ��ʼ���������
	 */
	public void initTable() {
		tableModel = getUsableModleSoure();// ��ȡ������Ч�ͻ���Ϣ
		table.setModel(tableModel);// �ͻ���Ϣ����������ģ��
	}

	/**
	 * ��ȡ��ǰ�����û�
	 */
	static public User getUser() {
		return user;
	}// getUser()����

	/**
	 * ���õ�ǰ�����û�
	 * 
	 * @param user
	 *            - ��ǰ�����û�
	 */
	static public void setUser(User user) {
		MainFrame.user = user;
	}// setUser()����
}// MainFrame�����
