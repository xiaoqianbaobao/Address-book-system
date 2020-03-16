package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.mr.contact.dao.Dao;
import com.mr.contact.dao.DaoFactory;
import com.mr.contact.swing.CustomerFrame;
import com.mr.contact.swing.FixedTable;

import pojo.Communication;
import pojo.Customer;

/**
 * �޸Ŀͻ���Ϣ����
 *
 */
public class UpdateCustomerFrame extends CustomerFrame {
	private final byte ALLSOUR = 0x5a;// ��ȡȫ������
	private final byte USABLESOUR = 0x1a;// ��ȡ��Ч����
	private Dao dao;// ���ݿ�ӿ�
	private FixedTable table;// ͨѶ��Ϣ���
	private DefaultTableModel tableModel; // ͨѶ��Ϣ����ģ��
	private JTextField nameText;// ���������
	private JTextField workUnitText;// ������λ�����
	private JTextField roleText;// ְλ�����
	private JTextField workAddressText;// ������ַ�����
	private JTextField homeText;// ��ͥסַ�����
	private JTextField birthText;// �������������
	private Customer cust;// ���޸ĵĿͻ�
	private JButton cancelBtn;// �رհ�ť
	private JButton saveBtn;// ���水ť
	private JComboBox<String> sexComboBox;// �Ա�������
	private JButton delRowBtn;// ɾ���а�ť
	private JButton addRowBtn;// ����а�ť
	private JCheckBox chckbxNewCheckBox;// ����ʧЧ��Ϣ��ѡ��
	private MainFrame frame;// ������

	/**
	 * ���췽��
	 * 
	 * @param cust
	 *            - ���޸ĵĿͻ�
	 * @param frame
	 *            - ������
	 */
	public UpdateCustomerFrame(Customer cust, JFrame frame) {
		// ���ø��ഴ�����޸ġ����ڵĹ��췽��
		super(frame, CustomerFrame.UPDATE);
		this.cust = cust;// ��¼���޸ĵĿͻ�
		this.frame = (MainFrame) frame;// ��¼������
		setTitle("�޸Ŀͻ���Ϣ"); // ���ñ���
		dao = DaoFactory.getDao();// ʵ�������ݿ�ӿڶ���
		init();// �����ʼ��
		addAction();// ��Ӽ���
	}// UpdateCustomerFrame()����

	/**
	 * �����ʼ��
	 */
	private void init() {
		cust = dao.selectCustomer(cust.getId());// ��cust�־û�
		setTableModelSource(USABLESOUR);// ��ȡ�������
		table = getTable();// ����ָ�����ģ�͵ı��
		table.setModel(tableModel);// ���ر������
		table.setCellEditable(true);// �����Ա༭
		table.hiddenFirstColumn();// ���ص�һ��

		nameText = getNameText();// ��ȡ���������
		nameText.setText(cust.getName());// ���������չʾ�ͻ���

		sexComboBox = getSexCombo();// ��ȡ�Ա�������
		if (null == cust.getSex()) {// ����Ա�Ϊnull
			sexComboBox.setSelectedIndex(0);// ѡ���һ��
		} else if (cust.getSex().equals("Ů")) {
			sexComboBox.setSelectedIndex(2);// ѡ�������
		} else {
			sexComboBox.setSelectedIndex(1);// ѡ��ڶ���
		}// else����

		birthText = getBirthText();// ��ȡ���������
		if (null != cust.getBirth()) {// ������ղ�Ϊ��
			birthText.setText(cust.getBirth());// ���������չʾ�ͻ�����
		}// if����

		workUnitText = getWorkUnitText();// ��ȡ������λ�����
		if (null != cust.getWork_unit()) {// ���������λ��Ϊ��
			workUnitText.setText(cust.getWork_unit());// ������λ�����չʾ�ͻ�������λ
		}// if����

		roleText = getRoleText();// ��ȡְλ�����
		if (null != cust.getRole()) {// ���ְλ��Ϊ��
			roleText.setText(cust.getRole());// ְλ�����չʾ�ͻ�ְλ
		}// if����

		workAddressText = getWorkAddressText();// ��ȡ�����ص������
		if (null != cust.getWork_addr()) {// ��������ص㲻Ϊ��
			workAddressText.setText(cust.getWork_addr());// �����ص������չʾ�ͻ������ص�
		}// if����

		homeText = getHomeText();// ��ȡ��ͥסַ�����
		if (null != cust.getHome_addr()) {// �����ͥסַ��Ϊ��
			homeText.setText(cust.getHome_addr());// ��ͥסַ�����չʾ�ͻ���ͥסַ
		}// if����

		chckbxNewCheckBox = getChckbxNewCheckBox();// ��ȡ����

		JPanel southPanel = new JPanel();// �����ϲ����
		southPanel.setLayout(new GridLayout(1, 2));// ���ʹ��1��2�е����񲼾�
		getContentPane().add(southPanel, BorderLayout.SOUTH);// �������ŵ����������ϲ�

		FlowLayout p4layout = new FlowLayout();// ����������
		p4layout.setHgap(20);// ˮƽ���20����
		
		JPanel leftButtonPanel = new JPanel();// ������ఴť���
		leftButtonPanel.setLayout(p4layout);// ��ఴť���ʹ�ô�����������
		southPanel.add(leftButtonPanel);// �ϲ���������ఴť���

		delRowBtn = new JButton("ɾ����");// ʵ����ɾ���а�ť
		leftButtonPanel.add(delRowBtn);// ��ఴť������ɾ���а�ť

		addRowBtn = new JButton("�����");// ʵ��������а�ť
		leftButtonPanel.add(addRowBtn);// �������������а�ť

	
		JPanel rightButtonPanel = new JPanel();// �����Ҳఴť���
		rightButtonPanel.setLayout(p4layout);// �Ҳఴť���ʹ�ô�����������
		southPanel.add(rightButtonPanel);// �ϲ��������Ҳఴť���

		saveBtn = new JButton("����");// ʵ�������水ť
		rightButtonPanel.add(saveBtn);// �Ҳఴť�����ӱ��水ť

		cancelBtn = new JButton("ȡ��");// ʵ����ȡ����ť
		rightButtonPanel.add(cancelBtn);// �Ҳఴť������ȡ����ť
	}// init()����

	/**
	 * ��Ӷ�������
	 */
	private void addAction() {
		cancelBtn.addActionListener(new ActionListener() {// ȡ����ť
					public void actionPerformed(ActionEvent e) {
						dispose();// ���ٴ���
					}// actionPerformed()����
				});// cancelBtn.addActionListener()����

		addRowBtn.addActionListener(new ActionListener() {// ����а�ť
					public void actionPerformed(ActionEvent e) {// ���ʱ
						Communication addCom = new Communication();// ������ͨѶ��Ϣ
						addCom.setCust(cust);// ��Ϣ�����ͻ�Ϊcust
						addCom.setAvailable("Y");// Ĭ�Ͽ���
						dao.addCommunication(addCom, MainFrame.getUser());// ���ݿ����һ���ռ�¼
						String id = "" + addCom.getId();// ��ȡ��¼ID
						String[] add = { id, "", "", "", "", "Y" };// ����������
						tableModel.addRow(add);// �������ģ�����һ��
					}// actionPerformed()����
				});// addRowBtn.addActionListener()����

		delRowBtn.addActionListener(new ActionListener() {// ɾ���а�ť
					public void actionPerformed(ActionEvent e) {// ���ʱ
						int selectedRow = table.getSelectedRow();// ��ñ�ѡ���е�����
						if (selectedRow != -1) { // �ж��Ƿ���ڱ�ѡ����
							// ��ȡ���ѡ���еĵ�һ������
							String id = (String) table.getValueAt(selectedRow,
									0);
							Communication del = new Communication();// ������ͨѶ��Ϣ
							del.setId(Integer.parseInt(id));// ��Ϣ�ı��Ϊid
							// �������ݿ�������������ϢʧЧ
							dao.deleteCommunication(del, MainFrame.getUser());
							tableModel.removeRow(selectedRow);// �������ģ��ɾ��ѡ����
						}// if����
					}// actionPerformed()����
				});// delRowBtn.addActionListener()����

		saveBtn.addActionListener(new ActionListener() {// ���水ť
			public void actionPerformed(ActionEvent e) {
				if (table.isEditing()) {// �ñ��ֹͣ�༭״̬
					table.getCellEditor().stopCellEditing();
				}// if����
				if (checkInfo()) {// /���������ϢУ��ϸ�
					// ����������е���Ϣ����׼�����µĿͻ�����
					Customer updateCust = new Customer(nameText.getText()
							.trim(), // ����
							(String) sexComboBox.getSelectedItem(),// �Ա�
							birthText.getText().trim(),// ��������
							workUnitText.getText().trim(),// ������λ
							workAddressText.getText().trim(),// �����ص�
							homeText.getText().trim(),// ��ͥסַ
							roleText.getText().trim());// ְλ
					updateCust.setId(cust.getId());// ����ID
					updateCust.setAvailable("Y");// ���ÿ���
					dao.updateCustomer(updateCust, MainFrame.getUser());// ���¿ͻ���Ϣ

					int rowcount = table.getRowCount();// ��ȡ�������
					for (int i = 0; i < rowcount; i++) {// ����������
						String id = (String) table.getValueAt(i, 0);// ͨѶ��ϢID
						String officNum = (String) table.getValueAt(i, 1);// �칫�绰
						String mobileNum = (String) table.getValueAt(i, 2);// �ƶ��绰
						String emailStr = (String) table.getValueAt(i, 3);// ��������
						String qqNum = (String) table.getValueAt(i, 4);// QQ��
						String available = (String) table.getValueAt(i, 5);// �Ƿ���Ч
						// ���ݱ�������ݣ�����ͨѶ��Ϣ����
						Communication updateCom = new Communication(cust,
								officNum, mobileNum, emailStr, qqNum);
						updateCom.setId(Integer.parseInt(id));// ����ͨѶ��Ϣ���
						updateCom.setAvailable(available);// ����ͨѶ��Ϣ�Ƿ���Ч
						dao.updateCommunication(updateCom, MainFrame.getUser());// ����ͨѶ��Ϣ
					}// for����
						// �����Ի���
					JOptionPane.showMessageDialog(UpdateCustomerFrame.this,
							"����ɹ���");
					dispose();// ���ٴ���
				}// if����
			}// actionPerformed()����
		});// saveBtn.addActionListener()����

		chckbxNewCheckBox.addChangeListener(new ChangeListener() {// �Ƿ�����ʧЧ��Ϣ��ѡ��
					public void stateChanged(ChangeEvent e) {// ��ѡ��ʱ
						if (chckbxNewCheckBox.isSelected()) {// �����ѡ��ѡ��
							setTableModelSource(USABLESOUR);// ��ȡ��Ч����
						} else {
							setTableModelSource(ALLSOUR);// ��ȡ��������
						}// else����
						table.setModel(tableModel);// �����������ģ��
					}// stateChanged()����
				});// chckbxNewCheckBox.addChangeListener()����
	}// addAction()����

	/**
	 * ��ȡ�û�ͨѶ��Ϣ
	 * 
	 * @param type
	 *            - ��ȡ��������
	 * @return �û�ͨѶ��Ϣ
	 */
	private void setTableModelSource(byte type) {
		if (tableModel == null) {// ����������ģ�Ͳ��ǿյ�
			// ��������������
			String[] columnNames = { "���", "�칫�绰", "�ƶ��绰", "��������", "QQ", "�Ƿ���Ч" };
			tableModel = new DefaultTableModel();// ʵ�����������ģ��
			tableModel.setColumnIdentifiers(columnNames);// ����������
		}// if����
		if (tableModel.getRowCount() > 0) {// ����������������������0
			tableModel.getDataVector().clear();// ��ձ�����
			tableModel.fireTableDataChanged();// ���»��Ʊ������
		}// if����

		List<Communication> usableList = null;// ��������չʾ��ͨѶ��Ϣ����
		if (type == ALLSOUR) {// ���������������������
			// ��ȡ�˿ͻ�������������Ϣ
			usableList = dao.selectCustmerCommunicationAll(cust);
		} else if (type == USABLESOUR) {// ���������������Ч����
			// ��ȡ�˿ͻ�����Ч������Ϣ
			usableList = dao.selectCustmerCommunicationUsable(cust);
		} else {// �����������������
			return;// ��������
		}// else����
		int comCount = usableList.size();// ��ȡͨѶ��Ϣ���ϳ���
		String[] tableValues = new String[6];// ��������һ��ͨѶ��Ϣ���ݵ�����
		for (int i = 0; i < comCount; i++) {// ����ͨѶ��Ϣ����
			Communication com = usableList.get(i);// ��ȡ�����е�ͨѶ��Ϣ����
			tableValues[0] = "" + com.getId();// ��¼ID���ַ���ֵ
			tableValues[1] = com.getOffice_phone();// ��¼�칫�绰
			tableValues[2] = com.getMobile_phone();// ��¼�ƶ��绰
			tableValues[3] = com.getEmail();// ��¼��������
			tableValues[4] = com.getQq();// ��¼QQ
			tableValues[5] = com.getAvailable();// ��¼�Ƿ���Ч
			tableModel.addRow(tableValues);// �������ģ�����һ������
		}// for����
	}// setTableModelSource()����

	/**
	 * ����û���Ϣ�Ƿ���ϸ�ʽ
	 * 
	 * @return �Ƿ�ϸ�
	 */
	private boolean checkInfo() {
		boolean result = true;// ����������������Ĭ��ͨ��
		StringBuilder sb = new StringBuilder();// ������־�ַ���
		String name = nameText.getText();// ��ȡ����
		if ("".equals(name) || null == name) {// ��������ǿյ�
			result = false;// У����Ϊ��ͨ��
			sb.append("��������Ϊ�գ�\n");// ��¼������־
		}// if����

		// ��ȡ�û��Ա�
		String sex = (String) sexComboBox.getSelectedItem();
		if ("".equals(sex) || null == sex) {// ����Ա��ǿյ�
			result = false;// У����Ϊ��ͨ��
			sb.append("�Ա���Ϊ�գ�\n");// ��¼������־
		}// if����
		String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";// �����������ڸ�ʽ������ʽ
		String birth = birthText.getText();// ��ȡ��������
		// ����������ڲ�Ϊ�գ��Ҳ������ڸ�ʽ
		if (!(birth.matches(regex) || birth.equals(""))) {
			result = false;// У����Ϊ��ͨ��
			sb.append("���ڸ�ʽ����\n");// ��¼������־
		}// if����

		int rowcount = tableModel.getRowCount();// ��ȡ���������
		for (int i = 0; i < rowcount; i++) {// ���������������
			// ��ȡ�ڶ��еİ칫�绰
			String offic_num = (String) tableModel.getValueAt(i, 1);
			// ��ȡ�����е��ƶ��绰
			String mobile_num = (String) tableModel.getValueAt(i, 2);
			// ��ȡ�����еĵ����ʼ�
			String email_str = (String) tableModel.getValueAt(i, 3);
			// ��ȡ�����е�QQ����
			String qq_num = (String) tableModel.getValueAt(i, 4);
			// ��ȡ�����е���Ч��־
			String available = (String) tableModel.getValueAt(i, 5);
			// ����칫�绰��Ϊ�գ��Ҳ�����Ч����
			if (!(offic_num.matches("[0-9]+") || offic_num.equals(""))) {
				result = false;// У����Ϊ��ͨ��
				sb.append("�칫�绰���ڴ������ݣ�\n");// ��¼������־
			}// if����
				// ����ƶ��绰��Ϊ�գ��Ҳ�����Ч����
			if (!(mobile_num.matches("[0-9]+") || mobile_num.equals(""))) {
				result = false;// У����Ϊ��ͨ��
				sb.append("�ƶ��绰���ڴ������ݣ�\n");// ��¼������־
			}// if����
				// ������������ַ��Ϊ�գ��Ҳ�����Ч�����ʽ
			if (!(email_str.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}") || email_str
					.equals(""))) {
				result = false;// У����Ϊ��ͨ��
				sb.append("���������ʽ����\n");// ��¼������־
			}// if����
				// ���QQ���벻Ϊ�գ��Ҳ�����ЧQQ����
			if (!(qq_num.matches("[0-9]{5,10}") || qq_num.equals(""))) {
				result = false;// У����Ϊ��ͨ��
				sb.append("QQ������ڴ������ݣ�\n");// ��¼������־
			}// if����
				// �����Ч��־����YҲ����N
			if (!("Y".equals(available) || "N".equals(available))) {
				result = false;// У����Ϊ��ͨ��
				sb.append("��Ϣ��Ч��ֵֻ��ѡ��Y��N��\n");// ��¼������־
			}// if����
		}// for����
		if (!result) {// У����Ϊ��ͨ��
			// �����Ի���չʾ��־��Ϣ
			JOptionPane.showMessageDialog(null, sb.toString());
		}// if����
		return result;
	}// checkInfo()����

	/**
	 * ��������
	 */
	public void dispose() {
		super.dispose();// ���ø��ര�����ٷ���
		frame.initTable();// ��������±������
	}// dispose()����
}// UpdateCustomerFrame����
