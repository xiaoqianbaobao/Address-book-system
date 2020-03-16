package frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import com.mr.contact.dao.Dao;
import com.mr.contact.dao.DaoFactory;
import pojo.User;

import java.awt.FlowLayout;

/**
 * �޸��û���Ϣ����
 *
 */
public class UpdateUserframe extends JDialog {
	private JFrame frame;// ������
	private JButton saveBtn;// ���水ť
	private JButton cencelBtn;// �رհ�ť
	private User user;// ���޸ĵ��û�
	private Dao dao;// ���ݿ�ӿ�
	private JComboBox<String> comboBox;// Ȩ��������
	private JCheckBox resetPwdCheck;// ���������ѡ��

	/**
	 * ���췽��
	 * 
	 * @param user
	 *            - ���޸ĵ��û�
	 * @param frame
	 *            - ������
	 */
	public UpdateUserframe(User user, JFrame frame) {
		super(frame, true);// ���ø��๹�췽��������������
		this.frame = frame;
		this.user = user;
		dao = DaoFactory.getDao();
		setTitle("�޸��û�");
		setBounds(frame.getX() + 5, frame.getY() + 5, 286, 250);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 5, 5));

		JLabel usernameLabel = new JLabel("�û�����");
		panel.add(usernameLabel);

		JLabel userAccountLabel = new JLabel();
		userAccountLabel.setText(user.getAccount());
		panel.add(userAccountLabel);

		JLabel userStatusLabel = new JLabel("�û�Ȩ�ޣ�");
		panel.add(userStatusLabel);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "��ͨ�û�", "����Ա" }));
		if (User.ADMIN.equals(user.getStatus())) {// ����û���Ȩ���ǹ���Ա
			comboBox.setSelectedIndex(1);// Ĭ��ѡ������Ϊ1��ѡ��
		}
		panel.add(comboBox);

		JLabel resetPwdLabel = new JLabel("�Ƿ��������룺");
		panel.add(resetPwdLabel);

		resetPwdCheck = new JCheckBox("��������");
		panel.add(resetPwdCheck);

		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setHgap(15);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		saveBtn = new JButton("����");
		buttonPanel.add(saveBtn);

		cencelBtn = new JButton("�ر�");
		buttonPanel.add(cencelBtn);
		addAction();// ��Ӷ�������
	}

	/**
	 * ��Ӷ�������
	 */
	private void addAction() {
		cencelBtn.addActionListener(new ActionListener() {// �رհ�ť
					public void actionPerformed(ActionEvent e) {
						quit();// �رմ���
					}
				});

		saveBtn.addActionListener(new ActionListener() {// ���水ť
			public void actionPerformed(ActionEvent e) {
				int select = comboBox.getSelectedIndex();// ��ȡ������ѡ�е�����
				String status = User.GUEST;// ��ʼ���û�Ȩ�ޱ���
				User tmp = dao.selectUser(user.getId());// �����û�id���û�������г־û�
				if (select == 1) {// ���������ѡ�е�������1��Ҳ���ǹ���Աѡ��
					status = User.ADMIN;// �û�Ȩ�޸�Ϊ����Ա
				}
				tmp.setStatus(status);// �޸��û�ѡȨ��
				if (resetPwdCheck.isSelected()) {// ���ѡ�С��������롰
					tmp.setPassword("123456");// �����Ϊ123456
				}
				dao.updateUser(tmp, MainFrame.getUser());// ���û���Ϣ�־û�
				JOptionPane.showMessageDialog(UpdateUserframe.this, "�޸ĳɹ���");
				quit();// �رմ���
			}
		});

		addWindowListener(new WindowAdapter() {// ���ڹر�ʱ
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
	}

	/**
	 * �رմ���
	 */
	private void quit() {
		dispose();// ���ٴ���
		AdminUserInterfaceFrame admin = new AdminUserInterfaceFrame(frame);// �û��������
		admin.setVisible(true);// ����ɼ�
	}
}
