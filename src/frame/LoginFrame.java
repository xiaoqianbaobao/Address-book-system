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
 * 登录窗体
 *
 */
public class LoginFrame extends JFrame {
	/**
	 * 登录面板构造方法
	 */
	public LoginFrame() {
		setTitle("通讯录登录");// 设置窗体标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗体关闭后停止程序
		setSize(310, 210);// 窗体宽高
		Toolkit tool = Toolkit.getDefaultToolkit();// 创建系统该默认组件工具包
		Dimension d = tool.getScreenSize();// 获取屏幕尺寸，赋给一个二维坐标对象
		// 让主窗体在屏幕中间显示
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);

		JPanel contentPane = new JPanel();// 创建主容器面板
		// 祝容器面板使用宽度和间距都为5像素的空边框
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);// 将主容器面板载入到主容器中
		contentPane.setLayout(new BorderLayout());// 主容器面板使用边界布局
		// 创建使用2行1列网格布局的中部面板
		JPanel centerPanel = new JPanel(new GridLayout(2, 1));
		// 中部面板放到主容器面板中央位置
		contentPane.add(centerPanel, BorderLayout.CENTER);
		// 创建流布局
		FlowLayout centerLayout = new FlowLayout();
		centerLayout.setHgap(10);// 布局中组件间隔10像素
		// 创建存放第一行组件的面板，并使用流布局
		JPanel aFloorPanel = new JPanel(centerLayout);
		centerPanel.add(aFloorPanel);// 第一行面板放入中部面板中
		JLabel usernameLabel = new JLabel("账号：");// 创建标签
		// 标签对其方式为居中
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aFloorPanel.add(usernameLabel);// 第一行面板添加标签
		// 创建用户名输入框
		final JTextField usernameField = new JTextField();
		usernameField.setColumns(20);// 用户名输入框长度为20个字符
		aFloorPanel.add(usernameField);// 第一行面板添加用户名输入框
		// 创建存放第二行组件的面板，并使用流布局

		JPanel bFloorPanel = new JPanel(centerLayout);
		centerPanel.add(bFloorPanel);// 第二行面板放入中部面板中
		JLabel pwdLabel = new JLabel("密码：");// 创建标签
		// 标签对其方式为居中
		pwdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bFloorPanel.add(pwdLabel);// 第二行面板添加标签
		// 创建密码输入框
		final JPasswordField passwordField = new JPasswordField();
		passwordField.setColumns(20);// 密码输入框长度为20个字符
		bFloorPanel.add(passwordField);// 第二行面板添加密码输入框

		// 创建南部面板
		JPanel southPanel = new JPanel(centerLayout);
		// 将南部面板放入主容器面板南部
		contentPane.add(southPanel, BorderLayout.SOUTH);

		final JButton loginBtn = new JButton("登录");// 创建登录按钮
		southPanel.add(loginBtn);// 主容器添加登录按钮

		JButton closeBtn = new JButton("关闭");// 创建关闭按钮
		southPanel.add(closeBtn);// 主容器添加关闭按钮

		closeBtn.addActionListener(new ActionListener() {// 关闭按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				System.exit(0);// 程序结束
			}// actionPerformed()结束
		});// closeBtn.addActionListener()结束
		loginBtn.addActionListener(new ActionListener() {// 登录按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				Dao dao = DaoFactory.getDao();// 创建数据库接口对象
				String account = usernameField.getText().trim();// 获取账号输入框中的内容，去掉两边空格
				String password = new String(passwordField.getPassword());// 获取密码框中的内容
				User user = dao.selectUser(account, password);// 将账号密码交给数据库进行判断
				if (null == user) {// 如果获得的用户是空的
					// 弹出对话框告诉用户账号密码不对
					JOptionPane.showMessageDialog(null, "您输入的账号密码不正确！");
				} else {// 如果存在此用户
					MainFrame.setUser(user);// 将此用户设为当前操作用户
					MainFrame frame = new MainFrame();// 创建主窗体对象
					dispose();// 销毁本窗体
				}// else结束
			}// actionPerformed()结束
		});// loginBtn.addActionListener()结束

		// 密码面板添加回车事件
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginBtn.doClick();// 触发登录按钮点击事件
			}// actionPerformed()结束
		});// addActionListener()结束
	}// LoginFrame()结束
}// LoginFrame结束
