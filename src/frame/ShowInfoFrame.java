package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pojo.Communication;
import pojo.Customer;
import com.mr.contact.dao.Dao;
import com.mr.contact.dao.DaoFactory;
import com.mr.contact.swing.CustomerFrame;
import com.mr.contact.swing.FixedTable;

/**
 * չʾ�ͻ���ϸ��Ϣ����
 *
 */
public class ShowInfoFrame extends CustomerFrame {
	private Dao dao;// ���ݿ�ӿ�
	private MainFrame frame;// ������
	private Customer cust;// Ҫչʾ��Ϣ�Ŀͻ�
	private FixedTable table;// ͨѶ��Ϣ���
	private JTextField nameText;// ���������
	private JTextField workUnitText;// ������λ�����
	private JTextField roleText;// ְλ�����
	private JTextField workAddressText;// �����ص������
	private JTextField homeText;// ��ͥסַ�����
	private JTextField birthText;// ���������
	private JTextField sexText;// �Ա������
	private DefaultTableModel tableModel; // ͨѶ��Ϣ�������ģ��

	/**
	 * ���췽��
	 * 
	 * @param cust
	 *            Ҫչʾ��Ϣ�Ŀͻ�
	 * @param frame
	 *            - ������
	 */
	public ShowInfoFrame(Customer cust, JFrame frame) {
		// ���ø��ഴ����չʾ�����ڵĹ��췽��
		super(frame, CustomerFrame.SHOW);
		this.cust = cust;
		this.frame = (MainFrame) frame;
		setTitle("��ϸ��Ϣ");
		dao = DaoFactory.getDao();// ʵ�������ݿ�ӿڶ���

		table = getTable();// ��ȡ������
		table.setCellEditable(false);// ��񲻿ɱ༭

		tableModel = getTableModel();// ��ȡ������ģ��
		initTbleModel();// ��ʼ������������

		nameText = getNameText();// ��ȡ���������
		nameText.setText(cust.getName());// ���������չʾ�ͻ���

		sexText = getSexText();// ��ȡ�Ա������
		sexText.setText(cust.getSex());// �Ա������չʾ�ͻ��Ա�

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

		FlowLayout btnPanelLayout = new FlowLayout(FlowLayout.RIGHT);// �Ҷ���������
		JPanel btnPanel = new JPanel(btnPanelLayout);// ���������ֵİ�ť���
		getContentPane().add(btnPanel, BorderLayout.SOUTH);// ��ť�����ӵ�

		JButton btnNewButton = new JButton("�ر�");// �����رհ�ť
		btnPanel.add(btnNewButton);// ��ť��ӵ���ť�����
		btnNewButton.addActionListener(new ActionListener() {// �رհ�ť��Ӽ���
					public void actionPerformed(ActionEvent e) {// ����ʱ
						dispose();// ���ٴ���
					}// actionPerformed()����
				});// addActionListener()����
	}// ShowInfoFrame()����

	/**
	 * ������ݳ�ʼ��
	 */
	private void initTbleModel() {
		if (tableModel.getRowCount() > 0) {// ����������������0
			tableModel.getDataVector().clear();// ��ձ�����
			tableModel.fireTableDataChanged();// ���»��Ʊ������
		}// if����
			// ��ȡ����Ҫչʾ�Ŀͻ������е�ͨѶѶϢ
		List<Communication> usableList = dao
				.selectCustmerCommunicationUsable(cust);
		// ��������ͨѶ��Ϣ���ַ������飬���ڸ��������ģ�͸�ֵ
		String[] tableValues = new String[5];
		for (Communication com : usableList) {// ����ͨѶ��Ϣ����
			if (com.getAvailable().endsWith("Y")) {// �������Ч����
				tableValues[0] = "" + com.getId();// ��¼ID���ַ���ֵ
				tableValues[1] = com.getOffice_phone();// ��¼�칫�绰
				tableValues[2] = com.getMobile_phone();// ��¼�ƶ��绰
				tableValues[3] = com.getEmail();// ��¼��������
				tableValues[4] = com.getQq();// ��¼QQ
				tableModel.addRow(tableValues);// �������ģ�����һ�м�¼
			}// if����
		}// for����
	}// initTbleModel()����
}// ShowInfoFrame����
