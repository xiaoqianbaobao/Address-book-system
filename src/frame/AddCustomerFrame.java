package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pojo.Communication;
import pojo.Customer;

import com.mr.contact.dao.Dao;
import com.mr.contact.dao.DaoFactory;
import com.mr.contact.swing.CustomerFrame;

/**
 * ��ӿͻ�ͨѶ��Ϣ
 * 
 */
public class AddCustomerFrame extends CustomerFrame {
	private MainFrame frame;// ������
	private DefaultTableModel tableModel; // ͨѶ��Ϣ�������ģ��
	private JTable table;// ͨѶ��Ϣ���
	private Dao dao;// ���ݿ�ӿ�
	private JTextField nameText;// ���������
	private JTextField workUnitText;// ������λ�����
	private JTextField roleText;// ְλ�����
	private JTextField workAddressText;// �����ص������
	private JTextField homeText;// ��ͥסַ�����
	private JTextField birthText;// ���������
	private JButton cancelBtn;// �رհ�ť
	private JButton saveBtn;// ���水ť
	private JComboBox<String> sexCombo;// �Ա�������
	private JButton addRowBtn;// ����а�ť
	private JButton delRowBtn;// ɾ���а�ť

	/**
	 * ���췽��
	 * 
	 * @param frame
	 *            - ������
	 */
	public AddCustomerFrame(JFrame frame) {
		// ���ø��ഴ������ӡ����ڵĹ��췽��
		super(frame, CustomerFrame.ADD);
		this.frame = (MainFrame) frame;// ��¼������
		setTitle("��ӿͻ�����");// ���ô������
		dao = DaoFactory.getDao();// ��ʼ�����ݿ�ӿ�

		tableModel = getTableModel();// ��ȡ�������ģ��
		table = getTable();// ��ȡ���
		nameText = getNameText();// ��ȡ���������
		sexCombo = getSexCombo();// ��ȡ�Ա������б�
		birthText = getBirthText();// ��ȡ�������������
		workUnitText = getWorkUnitText();// ��ȡ������λ
		workAddressText = getWorkAddressText();// ��ȡ�����ص�
		homeText = getHomeText();// ��ȡ��ͥסַ
		roleText = getRoleText();// ��ȡְλ
		JPanel btnPanel = new JPanel();// ������ť���
		// ��ť���ʹ��1��2�е����񲼾�
		btnPanel.setLayout(new GridLayout(1, 2));
		// ����ť���ŵ����������ϲ�
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		FlowLayout p4layout = new FlowLayout();// ����������
		p4layout.setHgap(20);// ˮƽ���20����
		
		JPanel leftButtonPanel = new JPanel();// ������ఴť���
		leftButtonPanel.setLayout(p4layout);// ��ఴť���ʹ�ô�����������
		btnPanel.add(leftButtonPanel);// �ϲ���������ఴť���

		delRowBtn = new JButton("ɾ����");// ʵ����ɾ���а�ť
		leftButtonPanel.add(delRowBtn);// ��ఴť������ɾ���а�ť

		addRowBtn = new JButton("�����");// ʵ��������а�ť
		leftButtonPanel.add(addRowBtn);// �������������а�ť

	
		JPanel rightButtonPanel = new JPanel();// �����Ҳఴť���
		rightButtonPanel.setLayout(p4layout);// �Ҳఴť���ʹ�ô�����������
		btnPanel.add(rightButtonPanel);// �ϲ��������Ҳఴť���

		saveBtn = new JButton("����");// ʵ�������水ť
		rightButtonPanel.add(saveBtn);// �Ҳఴť�����ӱ��水ť

		cancelBtn = new JButton("�ر�");// ʵ�����رհ�ť
		rightButtonPanel.add(cancelBtn);// �Ҳఴť�����ӹرհ�ť

		addAction();// ����������
		
	}// AddCustomerFrame()����

	/**
	 * ����������
	 */
	private void addAction() {
		cancelBtn.addActionListener(new ActionListener() {// ȡ����ť��Ӷ�������
					public void actionPerformed(ActionEvent e) {// �����ʱ
						dispose();// ���ٴ���
					}// actionPerformed()����
				});// cancelBtn.addActionListener()����

		delRowBtn.addActionListener(new ActionListener() {// ɾ���а�ť��Ӷ�������
					public void actionPerformed(ActionEvent e) {// �����ʱ
						int selectedRow = table.getSelectedRow();// ��ñ�ѡ���е�����
						if (selectedRow != -1) // �ж��Ƿ���ڱ�ѡ����
							// �ӱ��ģ�͵���ɾ��ָ����
							tableModel.removeRow(selectedRow);
					}// actionPerformed()����
				});// delRowBtn.addActionListener()����

		addRowBtn.addActionListener(new ActionListener() {// ����а�ť��Ӷ�������
					public void actionPerformed(ActionEvent e) {// �����ʱ
						String[] add = { "", "", "", "" };// ����������
						tableModel.addRow(add);// ���ģ�����һ��
					}// actionPerformed()����
				});// addRowBtn.addActionListener()����

		saveBtn.addActionListener(new ActionListener() {// ���水ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
				if (table.isEditing()) {// ���������ڱ༭
					table.getCellEditor().stopCellEditing();// �ñ��ֹͣ�༭״̬
				}// if����
				if (checkInfo()) {// ���������Ϣͨ��У��
					// �����ͻ�����
					Customer updateCust = new Customer(nameText.getText()
							.trim(),// ����
							(String) sexCombo.getSelectedItem(),// �Ա�
							birthText.getText().trim(),// ��������
							workUnitText.getText().trim(),// ������λ
							workAddressText.getText().trim(),// �����ص�
							homeText.getText().trim(),// ��ͥסַ
							roleText.getText().trim());// ְλ
					// �����ݿ���Ӵ��û�����
					dao.addCustomer(updateCust, MainFrame.getUser());

					int rowcount = table.getRowCount();// ��ȡ��������
					for (int i = 0; i < rowcount; i++) {// ����������
						// ��0�б�ű����أ��˴���������
						String offic_num = (String) table.getValueAt(i, 1);// ��ȡ��һ�еİ칫�绰
						String mobile_num = (String) table.getValueAt(i, 2);// ��ȡ�ڶ��е��ƶ��绰
						String email_str = (String) table.getValueAt(i, 3);// ��ȡ�����е�������
						String qq_num = (String) table.getValueAt(i, 4);// ��ȡ������QQ��
						// ����ͨѶ��Ϣ����
						Communication updateCom = new Communication(updateCust,
								offic_num, mobile_num, email_str, qq_num);
						// �����ݿ���Ӵ�ͨѶ��Ϣ����
						dao.addCommunication(updateCom, MainFrame.getUser());
					}// for����
						// �����Ի���
					JOptionPane.showMessageDialog(AddCustomerFrame.this,
							"��ӳɹ���");
					dispose();// ���ٴ���
				}// if����
			}// actionPerformed()����
		});// saveBtn.addActionListener()����
	}// addAction()����

	/**
	 * У����Ϣ
	 * 
	 * @return У���Ƿ�ɹ�
	 */
	private boolean checkInfo() {
		boolean result = true;// У����������Ĭ��У��ɹ�
		StringBuilder sb = new StringBuilder();// ��ʾ��Ϣ�ַ���
		String name = nameText.getText();// ��ȡ��������������
		if ("".equals(name) || null == name) {// ��������ǿյ�
			result = false;// У����Ϊ��ͨ��
			sb.append("��������Ϊ�գ�\n");// ��¼������־
		}// if����

		String sex = (String) sexCombo.getSelectedItem();// ��ȡ�Ա��������е��ı�
		if ("".equals(sex) || null == sex) {// ����Ա��ǿյ�
			result = false;// У����Ϊ��ͨ��
			sb.append("�Ա���Ϊ�գ�\n");// ��¼������־
		}// if����

		String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";// ��������������ʽ
		String birth = birthText.getText();// ��ȡ�������������
		if (!birth.matches(regex)) {// ������ڲ�����������ʽ
			result = false;// У����Ϊ��ͨ��
			sb.append("���ڸ�ʽ����\n");// ��¼������־
		}// if����

		int rowcount = table.getRowCount();// ��ȡ����е�����
		for (int i = 0; i < rowcount; i++) {// �������������
			// ��ȡ��һ�еİ칫�绰
			String offic_num = (String) table.getValueAt(i, 1);
			// ��ȡ�ڶ��е��ƶ��绰
			String mobile_num = (String) table.getValueAt(i, 2);
			// ��ȡ�����е�������
			String email_str = (String) table.getValueAt(i, 3);
			// ��ȡ������QQ��
			String qq_num = (String) table.getValueAt(i, 4);
			// ����칫�绰�������ֲ��Ҳ�Ϊ��
			if (!offic_num.matches("[0-9]+") && !offic_num.equals("")) {
				result = false;// У����Ϊ��ͨ��
				sb.append("�칫�绰���붼�����֣�\n");// ��¼������־
			}// if����
				// ����ƶ��绰�������ֲ��Ҳ�Ϊ��
			if (!mobile_num.matches("[0-9]+") && !mobile_num.equals("")) {
				result = false;// У����Ϊ��ͨ��
				sb.append("�ƶ��绰���붼�����֣�\n");// ��¼������־
			}// if����
				// ����������䲻���������ʽ���Ҳ�Ϊ��
			if (!email_str.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}")
					&& !email_str.equals("")) {
				result = false;// У����Ϊ��ͨ��
				sb.append(email_str+"���������ʽ����\n");// ��¼������־
			}// if����
				// ���QQ�Ų���5-11Ϊ�����鲢�Ҳ�Ϊ��
			if (!qq_num.matches("[0-9]{5,11}") && !qq_num.equals("")) {
				result = false;// У����Ϊ��ͨ��
				sb.append(qq_num+"QQ������ڴ������ݣ�\n");// ��¼������־
			}// if����
		}// for����

		if (!result) {// ���У��ʧ��
			// �����Ի���չʾ��־��Ϣ
			JOptionPane.showMessageDialog(this, sb.toString());
		}// if����
		return result;
	}// checkInfo()����

	/**
	 * ���ٴ���
	 */
	public void dispose() {
		super.dispose();// ���ø��ര�����ٷ���
		frame.initTable();// ��������±������
	}// dispose()����
}
