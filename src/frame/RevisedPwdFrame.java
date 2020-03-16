package frame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import com.mr.contact.dao.Dao;
import com.mr.contact.dao.DaoFactory;
import pojo.User;

/**
 * �޸�����
 *
 */
public class RevisedPwdFrame extends JDialog {

	private JPasswordField oldpwd_t;// ԭ����
	private JPasswordField newpwd1_t;// ������
	private JPasswordField newpwd2_t;// ȷ��������
	private User user;// ���޸ĵ��û�
	private Dao dao;// ���ݿ�ӿ�
	private JButton update_btn;// �޸İ�ť
	private JButton cencel_btn;// �ر�ȡ����ť

	/**
	 * ���췽��
	 */
	public RevisedPwdFrame(JFrame frame) {
		super(frame, true);// ���ø��๹�췽��������������
		this.user = MainFrame.getUser();
		dao = DaoFactory.getDao();

		setTitle("�޸�����");
		setBounds(frame.getX() + 40, frame.getY() + 30, 346, 233);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// ʹ�ÿձ߿�
		contentPane.setLayout(new BorderLayout(0, 0));// ʹ�ñ߽粼��
		setContentPane(contentPane);// ָ���������������

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 5));

		JLabel lblNewLabel = new JLabel("�û�����");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lblNewLabel);

		JLabel userAccount_l = new JLabel();
		userAccount_l.setText(user.getAccount());
		panel.add(userAccount_l);

		JLabel lblNewLabel_2 = new JLabel("�����룺");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);

		oldpwd_t = new JPasswordField();
		panel.add(oldpwd_t);

		JLabel label = new JLabel("�����룺");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);

		newpwd1_t = new JPasswordField();
		panel.add(newpwd1_t);

		JLabel label_1 = new JLabel("ȷ�������룺");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);

		newpwd2_t = new JPasswordField();
		panel.add(newpwd2_t);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		update_btn = new JButton("�޸�");
		panel_1.add(update_btn);

		cencel_btn = new JButton("ȡ��");
		panel_1.add(cencel_btn);

		addAction();// ��Ӷ�������
	}

	/**
	 * ��Ӷ�������
	 */
	private void addAction() {
		cencel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// ���ٴ���
			}
		});

		update_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldpwd = new String(oldpwd_t.getPassword());// �����������
				String newpwd1 = new String(newpwd1_t.getPassword());// �����������
				String newpwd2 = new String(newpwd2_t.getPassword());// ȷ�������������
				StringBuilder mesagge = new StringBuilder();// ��ʾ����־
				boolean flag = true;// �޸�����������Ƿ��������

				if (!user.getPassword().equals(oldpwd)) {// �����������ԭ���벻ƥ��
					flag = false;
					mesagge.append("ԭ���벻��ȷ��\n");
				}
				if (!newpwd1.equals(newpwd2)) {// ��������������벻ƥ��
					flag = false;
					mesagge.append("������������벻һ�£�");
				}
				if (flag) {
					User update = dao.selectUser(user.getId());// ��ȡ�־û����û�����
					update.setPassword(newpwd1);// �����û�����
					dao.updateUser(update, MainFrame.getUser());// �޸����ݿ�������
					JOptionPane
							.showMessageDialog(RevisedPwdFrame.this, "�޸ĳɹ���");// �����Ի���
					dispose();// ���ٴ���
				} else {
					JOptionPane.showMessageDialog(RevisedPwdFrame.this,
							mesagge.toString(), "ע��", JOptionPane.ERROR_MESSAGE);// �����Ի���
				}
			}
		});
	}
}
