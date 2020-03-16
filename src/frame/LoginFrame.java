package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pojo.User;

import com.mr.contact.dao.Dao;
import com.mr.contact.dao.DaoFactory;

/**
 * ��¼����
 *
 */
public class LoginFrame extends JFrame {
	/**
	 * ��¼��幹�췽��
	 */
	public LoginFrame() {
		setTitle("ͨѶ¼��¼");// ���ô������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ����رպ�ֹͣ����
		setSize(310, 210);// ������
		Toolkit tool = Toolkit.getDefaultToolkit();// ����ϵͳ��Ĭ��������߰�
		Dimension d = tool.getScreenSize();// ��ȡ��Ļ�ߴ磬����һ����ά�������
		// ������������Ļ�м���ʾ
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);

		JPanel contentPane = new JPanel();// �������������
		// ף�������ʹ�ÿ�Ⱥͼ�඼Ϊ5���صĿձ߿�
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);// ��������������뵽��������
		contentPane.setLayout(new BorderLayout());// ���������ʹ�ñ߽粼��
		// ����ʹ��2��1�����񲼾ֵ��в����
		JPanel centerPanel = new JPanel(new GridLayout(2, 1));
		// �в����ŵ��������������λ��
		contentPane.add(centerPanel, BorderLayout.CENTER);
		// ����������
		FlowLayout centerLayout = new FlowLayout();
		centerLayout.setHgap(10);// ������������10����
		// ������ŵ�һ���������壬��ʹ��������
		JPanel aFloorPanel = new JPanel(centerLayout);
		centerPanel.add(aFloorPanel);// ��һ���������в������
		JLabel usernameLabel = new JLabel("�˺ţ�");// ������ǩ
		// ��ǩ���䷽ʽΪ����
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aFloorPanel.add(usernameLabel);// ��һ�������ӱ�ǩ
		// �����û��������
		final JTextField usernameField = new JTextField();
		usernameField.setColumns(20);// �û�������򳤶�Ϊ20���ַ�
		aFloorPanel.add(usernameField);// ��һ���������û��������
		// ������ŵڶ����������壬��ʹ��������

		JPanel bFloorPanel = new JPanel(centerLayout);
		centerPanel.add(bFloorPanel);// �ڶ����������в������
		JLabel pwdLabel = new JLabel("���룺");// ������ǩ
		// ��ǩ���䷽ʽΪ����
		pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bFloorPanel.add(pwdLabel);// �ڶ��������ӱ�ǩ
		// �������������
		final JPasswordField passwordField = new JPasswordField();
		passwordField.setColumns(20);// ��������򳤶�Ϊ20���ַ�
		bFloorPanel.add(passwordField);// �ڶ������������������

		// �����ϲ����
		JPanel southPanel = new JPanel(centerLayout);
		// ���ϲ�����������������ϲ�
		contentPane.add(southPanel, BorderLayout.SOUTH);

		final JButton loginBtn = new JButton("��¼");// ������¼��ť
		southPanel.add(loginBtn);// ��������ӵ�¼��ť

		JButton closeBtn = new JButton("�ر�");// �����رհ�ť
		southPanel.add(closeBtn);// ��������ӹرհ�ť

		closeBtn.addActionListener(new ActionListener() {// �رհ�ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
				System.exit(0);// �������
			}// actionPerformed()����
		});// closeBtn.addActionListener()����
		loginBtn.addActionListener(new ActionListener() {// ��¼��ť��Ӷ�������
			public void actionPerformed(ActionEvent e) {// �����ʱ
				Dao dao = DaoFactory.getDao();// �������ݿ�ӿڶ���
				String account = usernameField.getText().trim();// ��ȡ�˺�������е����ݣ�ȥ�����߿ո�
				String password = new String(passwordField.getPassword());// ��ȡ������е�����
				User user = dao.selectUser(account, password);// ���˺����뽻�����ݿ�����ж�
				if (null == user) {// �����õ��û��ǿյ�
					// �����Ի�������û��˺����벻��
					JOptionPane.showMessageDialog(null, "��������˺����벻��ȷ��");
				} else {// ������ڴ��û�
					MainFrame.setUser(user);// �����û���Ϊ��ǰ�����û�
					MainFrame frame = new MainFrame();// �������������
					dispose();// ���ٱ�����
				}// else����
			}// actionPerformed()����
		});// loginBtn.addActionListener()����

		// ���������ӻس��¼�
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginBtn.doClick();// ������¼��ť����¼�
			}// actionPerformed()����
		});// addActionListener()����
	}// LoginFrame()����
}// LoginFrame����
