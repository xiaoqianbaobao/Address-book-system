package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.mr.contact.dao.Dao;
import com.mr.contact.dao.DaoFactory;
import pojo.User;

/**
 * ����û�����
 *
 */
public class AddUserFrame extends JDialog {
	private JFrame frame;// ������
	private JTextField accountField;// �û��˺������
	private JPasswordField passwordField;// ����������
	private JPasswordField passwordField_1;// �ٴ�ȷ�����������
	private JButton save_btn;// ���水ť
	private JButton reset_btn;// ���ð�ť
	private JButton cencle_btn;// �رհ�ť
	private Dao dao;// ���ݿ�ӿ�
	private JComboBox comboBox;// Ȩ��������

	/**
	 * ���췽��
	 * 
	 * @param frame
	 *            - ������
	 */
	public AddUserFrame(JFrame frame) {
		super(frame, true);// ���ø��๹�췽��������������
		this.frame = frame;
		init();// �����ʼ��
		addActoin();// ��Ӽ����¼�
	}

	/**
	 * �����ʼ��
	 */
	private void init() {
		dao = DaoFactory.getDao();

		setTitle("�½��û�");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(frame.getX() + 10, frame.getY() + 10, 288, 250);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("�û�����");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);

		accountField = new JTextField();
		panel_1.add(accountField);
		accountField.setColumns(10);

		JLabel label = new JLabel("���룺");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);

		passwordField = new JPasswordField();
		panel_1.add(passwordField);

		JLabel label_1 = new JLabel("ȷ�����룺");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1);

		passwordField_1 = new JPasswordField();
		panel_1.add(passwordField_1);

		JLabel lblNewLabel_1 = new JLabel("Ȩ�ޣ�");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "��ͨ�û�", "����Ա" }));
		panel_1.add(comboBox);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(15);
		panel.add(panel_2, BorderLayout.SOUTH);

		save_btn = new JButton("����");
		panel_2.add(save_btn);

		reset_btn = new JButton("����");
		panel_2.add(reset_btn);

		cencle_btn = new JButton("ȡ��");
		panel_2.add(cencle_btn);
	}

	/**
	 * ��Ӽ����¼�
	 */
	private void addActoin() {
		cencle_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();// �رմ���
			}
		});

		reset_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountField.setText("");
				passwordField.setText("");
				passwordField_1.setText("");
			}
		});

		save_btn.addActionListener(new ActionListener() {// ���水ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
				String account = accountField.getText().trim();// ��ȡ�û��˺�
				String password = new String(passwordField.getPassword());// ����
				String password2 = new String(passwordField_1.getPassword());// ȷ�ϵ�����
				int comboBoxSelected = comboBox.getSelectedIndex();// ��ȡ������ѡ��״̬
				boolean flag = true;// ע���Ƿ�ɹ�
				StringBuilder message = new StringBuilder();// �Ի�����ʾ��Ϣ
				message.append("���ʧ�ܣ�");
				if ("".equals(account)) {
					flag = false;
					message.append("\n�û�������Ϊ��");
				}
				if ("".equals(password)) {
					flag = false;
					message.append("\n���벻��Ϊ��");
				}
				if (!password.equals(password2)) {
					flag = false;
					message.append("\n������������벻һ�£�");
				}
				if (dao.checkUser(account)) {// ��̨����Ƿ�����Ч�û�����
					flag = false;
					message.append("\n�û��Ѵ��ڣ�");
				}
				if (flag) {
					String status = User.GUEST;
					if (comboBoxSelected == 1) {// ���ѡ�е��ǹ���Աѡ��
						status = User.ADMIN;
					}
					User user = new User(account, password, status);
					user.setAvailable("Y");// ��Ϊ��Ч�û�
					dao.addUser(user, MainFrame.getUser());// �û��־û�
					JOptionPane.showMessageDialog(AddUserFrame.this, "��ӳɹ���");
					quit();// �رմ���
				} else {
					JOptionPane.showMessageDialog(AddUserFrame.this,
							message.toString());
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {// ���ڹر�ʱ
				quit();// �رմ���
			}
		});
	}

	/**
	 * �رմ���
	 */
	private void quit() {
		dispose();// ���ٴ���
		AdminUserInterfaceFrame admin = new AdminUserInterfaceFrame(frame);// ���û�������
		admin.setVisible(true);// ����ɼ�
	}
}
