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
 * 修改密码
 *
 */
public class RevisedPwdFrame extends JDialog {

	private JPasswordField oldpwd_t;// 原密码
	private JPasswordField newpwd1_t;// 新密码
	private JPasswordField newpwd2_t;// 确认新密码
	private User user;// 被修改的用户
	private Dao dao;// 数据库接口
	private JButton update_btn;// 修改按钮
	private JButton cencel_btn;// 关闭取消按钮

	/**
	 * 构造方法
	 */
	public RevisedPwdFrame(JFrame frame) {
		super(frame, true);// 调用父类构造方法，阻塞父窗体
		this.user = MainFrame.getUser();
		dao = DaoFactory.getDao();

		setTitle("修改密码");
		setBounds(frame.getX() + 40, frame.getY() + 30, 346, 233);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 使用空边框
		contentPane.setLayout(new BorderLayout(0, 0));// 使用边界布局
		setContentPane(contentPane);// 指定窗体主容器面板

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 5));

		JLabel lblNewLabel = new JLabel("用户名：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lblNewLabel);

		JLabel userAccount_l = new JLabel();
		userAccount_l.setText(user.getAccount());
		panel.add(userAccount_l);

		JLabel lblNewLabel_2 = new JLabel("旧密码：");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);

		oldpwd_t = new JPasswordField();
		panel.add(oldpwd_t);

		JLabel label = new JLabel("新密码：");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);

		newpwd1_t = new JPasswordField();
		panel.add(newpwd1_t);

		JLabel label_1 = new JLabel("确认新密码：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);

		newpwd2_t = new JPasswordField();
		panel.add(newpwd2_t);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		update_btn = new JButton("修改");
		panel_1.add(update_btn);

		cencel_btn = new JButton("取消");
		panel_1.add(cencel_btn);

		addAction();// 添加动作监听
	}

	/**
	 * 添加动作监听
	 */
	private void addAction() {
		cencel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// 销毁窗体
			}
		});

		update_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldpwd = new String(oldpwd_t.getPassword());// 旧密码框内容
				String newpwd1 = new String(newpwd1_t.getPassword());// 新密码框内容
				String newpwd2 = new String(newpwd2_t.getPassword());// 确认新密码框内容
				StringBuilder mesagge = new StringBuilder();// 提示框日志
				boolean flag = true;// 修改密码过程中是否出现问题

				if (!user.getPassword().equals(oldpwd)) {// 输入的密码与原密码不匹配
					flag = false;
					mesagge.append("原密码不正确！\n");
				}
				if (!newpwd1.equals(newpwd2)) {// 两次输入的新密码不匹配
					flag = false;
					mesagge.append("两次输入的密码不一致！");
				}
				if (flag) {
					User update = dao.selectUser(user.getId());// 获取持久化的用户对象
					update.setPassword(newpwd1);// 更新用户密码
					dao.updateUser(update, MainFrame.getUser());// 修改数据库中数据
					JOptionPane
							.showMessageDialog(RevisedPwdFrame.this, "修改成功！");// 弹出对话框
					dispose();// 销毁窗体
				} else {
					JOptionPane.showMessageDialog(RevisedPwdFrame.this,
							mesagge.toString(), "注意", JOptionPane.ERROR_MESSAGE);// 弹出对话框
				}
			}
		});
	}
}
