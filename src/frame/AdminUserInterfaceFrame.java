package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import com.mr.contact.dao.Dao;
import com.mr.contact.dao.DaoFactory;

import pojo.User;

import com.mr.contact.swing.FixedTable;

/**
 * 用户管理界面
 *
 */
public class AdminUserInterfaceFrame extends JDialog {
	private JFrame frame;// 父窗体
	private FixedTable table;// 用户列表表格
	private DefaultTableModel tableModel;// 表格数据模型
	private Dao dao;// 数据库接口
	private JButton addUser_btn;// 添加用户按钮
	private JButton updateUser_btn;// 修改用户按钮
	private JButton delUser_btn;// 删除用户按钮
	private JButton back_btn;// 关闭按钮

	/**
	 * 构造方法
	 * 
	 * @param frame
	 *            - 父窗体
	 */
	public AdminUserInterfaceFrame(JFrame frame) {
		super(frame, true);// 调用父类构造方法，阻塞父窗体
		this.frame = frame;
		dao = DaoFactory.getDao();

		setTitle("用户管理	");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(frame.getX() + 5, frame.getY() + 5, 520, 291);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel("用户列表");
		panel.add(label, BorderLayout.NORTH);

		List<User> list = dao.selectUsableUser();// 获取所有有效用户信息
		int userCount = list.size();
		String[] columnNames = { "id", "用户名", "权限" }; // 定义表格列名数组
		String[][] tableValues = new String[userCount][columnNames.length];
		for (int i = 0; i < userCount; i++) {
			User tmp = list.get(i);
			tableValues[i][0] = "" + tmp.getId();
			tableValues[i][1] = tmp.getAccount();
			tableValues[i][2] = tmp.getStatus();
		}
		tableModel = new DefaultTableModel(tableValues, columnNames);
		table = new FixedTable(tableModel);
		table.setCellEditable(false);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		FlowLayout southLayout = new FlowLayout(FlowLayout.CENTER, 15, 0);
		JPanel southPanel = new JPanel(southLayout);
		contentPane.add(southPanel, BorderLayout.SOUTH);

		addUser_btn = new JButton("添加新用户");
		southPanel.add(addUser_btn);

		updateUser_btn = new JButton("修改用户信息");
		southPanel.add(updateUser_btn);

		delUser_btn = new JButton("删除用户");
		southPanel.add(delUser_btn);

		back_btn = new JButton("关闭");
		southPanel.add(back_btn);

		addAction();
	}

	/**
	 * 添加监听
	 */
	private void addAction() {
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// 销毁窗体
			}
		});

		updateUser_btn.addActionListener(new ActionListener() {// 修改用户信息按钮添加事件监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				int rowindex = table.getSelectedRow();// 获取选中行的索引
				if (rowindex != -1) {// 如果有选中的行
					String id = (String) table.getValueAt(rowindex, 0);// 获取第一列用户id
					User update = dao.selectUser(Integer.parseInt(id));// 根据id获得持久化用户对象
					// 创建修改用户界面
					UpdateUserframe updateFrame = new UpdateUserframe(update, frame);
					updateFrame.setVisible(true);// 修改用户界面可见
					dispose();// 本窗体销毁
				}
			}
		});

		delUser_btn.addActionListener(new ActionListener() {// 删除用户按钮添加事件监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				int rowindex = table.getSelectedRow();// 获取选中行的索引
				if (rowindex != -1) {// 如果有选中的行
					// 弹出提示
					int i = JOptionPane.showConfirmDialog(AdminUserInterfaceFrame.this,
							"确认是否删除" + table.getValueAt(rowindex, 1) + "?", "注意！", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {// 如果选中yes
						String id = (String) table.getValueAt(rowindex, 0);// 获取被选中第一行id值
						User del = new User();// 创建要被删除的用户对象
						del.setId(Integer.parseInt(id));// 设置用户id
						dao.deleteUser(del, MainFrame.getUser());// 数据库中删除此用户
						tableModel.removeRow(table.getSelectedRow());// 表格删除选中行
					}
				}
			}
		});

		addUser_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUserFrame addUser = new AddUserFrame(frame);
				addUser.setVisible(true);
				dispose();
			}
		});
	}
}
