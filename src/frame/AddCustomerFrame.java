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
 * 添加客户通讯信息
 * 
 */
public class AddCustomerFrame extends CustomerFrame {
	private MainFrame frame;// 父窗体
	private DefaultTableModel tableModel; // 通讯信息表格数据模型
	private JTable table;// 通讯信息表格
	private Dao dao;// 数据库接口
	private JTextField nameText;// 姓名输入框
	private JTextField workUnitText;// 工作单位输入框
	private JTextField roleText;// 职位输入框
	private JTextField workAddressText;// 工作地点输入框
	private JTextField homeText;// 家庭住址输入框
	private JTextField birthText;// 生日输入框
	private JButton cancelBtn;// 关闭按钮
	private JButton saveBtn;// 保存按钮
	private JComboBox<String> sexCombo;// 性别下拉框
	private JButton addRowBtn;// 添加行按钮
	private JButton delRowBtn;// 删除行按钮

	/**
	 * 构造方法
	 * 
	 * @param frame
	 *            - 父窗体
	 */
	public AddCustomerFrame(JFrame frame) {
		// 调用父类创建“添加”窗口的构造方法
		super(frame, CustomerFrame.ADD);
		this.frame = (MainFrame) frame;// 记录父窗体
		setTitle("添加客户资料");// 设置窗体标题
		dao = DaoFactory.getDao();// 初始化数据库接口

		tableModel = getTableModel();// 获取表格数据模型
		table = getTable();// 获取表格
		nameText = getNameText();// 获取姓名输入框
		sexCombo = getSexCombo();// 获取性别下拉列表
		birthText = getBirthText();// 获取出生日期输入框
		workUnitText = getWorkUnitText();// 获取工作单位
		workAddressText = getWorkAddressText();// 获取工作地点
		homeText = getHomeText();// 获取家庭住址
		roleText = getRoleText();// 获取职位
		JPanel btnPanel = new JPanel();// 创建按钮面板
		// 按钮面板使用1行2列的网格布局
		btnPanel.setLayout(new GridLayout(1, 2));
		// 将按钮面板放到主容器的南部
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		FlowLayout p4layout = new FlowLayout();// 创建流布局
		p4layout.setHgap(20);// 水平间隔20像素
		
		JPanel leftButtonPanel = new JPanel();// 创建左侧按钮面板
		leftButtonPanel.setLayout(p4layout);// 左侧按钮面板使用创建的流布局
		btnPanel.add(leftButtonPanel);// 南部面板添加左侧按钮面板

		delRowBtn = new JButton("删除行");// 实例化删除行按钮
		leftButtonPanel.add(delRowBtn);// 左侧按钮面板添加删除行按钮

		addRowBtn = new JButton("添加行");// 实例化添加行按钮
		leftButtonPanel.add(addRowBtn);// 左侧面板添加添加行按钮

	
		JPanel rightButtonPanel = new JPanel();// 创建右侧按钮面板
		rightButtonPanel.setLayout(p4layout);// 右侧按钮面板使用创建的流布局
		btnPanel.add(rightButtonPanel);// 南部面板添加右侧按钮面板

		saveBtn = new JButton("保存");// 实例化保存按钮
		rightButtonPanel.add(saveBtn);// 右侧按钮面板添加保存按钮

		cancelBtn = new JButton("关闭");// 实例化关闭按钮
		rightButtonPanel.add(cancelBtn);// 右侧按钮面板添加关闭按钮

		addAction();// 添加组件监听
		
	}// AddCustomerFrame()结束

	/**
	 * 添加组件监听
	 */
	private void addAction() {
		cancelBtn.addActionListener(new ActionListener() {// 取消按钮添加动作监听
					public void actionPerformed(ActionEvent e) {// 当点击时
						dispose();// 销毁窗体
					}// actionPerformed()结束
				});// cancelBtn.addActionListener()结束

		delRowBtn.addActionListener(new ActionListener() {// 删除行按钮添加动作监听
					public void actionPerformed(ActionEvent e) {// 当点击时
						int selectedRow = table.getSelectedRow();// 获得被选中行的索引
						if (selectedRow != -1) // 判断是否存在被选中行
							// 从表格模型当中删除指定行
							tableModel.removeRow(selectedRow);
					}// actionPerformed()结束
				});// delRowBtn.addActionListener()结束

		addRowBtn.addActionListener(new ActionListener() {// 添加行按钮添加动作监听
					public void actionPerformed(ActionEvent e) {// 当点击时
						String[] add = { "", "", "", "" };// 创建空数组
						tableModel.addRow(add);// 表格模型添加一行
					}// actionPerformed()结束
				});// addRowBtn.addActionListener()结束

		saveBtn.addActionListener(new ActionListener() {// 保存按钮添加动作监听
			public void actionPerformed(ActionEvent e) {// 当点击时
				if (table.isEditing()) {// 如果表格正在编辑
					table.getCellEditor().stopCellEditing();// 让表格停止编辑状态
				}// if结束
				if (checkInfo()) {// 如果所有信息通过校验
					// 创建客户对象
					Customer updateCust = new Customer(nameText.getText()
							.trim(),// 姓名
							(String) sexCombo.getSelectedItem(),// 性别
							birthText.getText().trim(),// 出生日期
							workUnitText.getText().trim(),// 工作单位
							workAddressText.getText().trim(),// 工作地点
							homeText.getText().trim(),// 家庭住址
							roleText.getText().trim());// 职位
					// 向数据库添加此用户数据
					dao.addCustomer(updateCust, MainFrame.getUser());

					int rowcount = table.getRowCount();// 获取表格的行数
					for (int i = 0; i < rowcount; i++) {// 遍历所有行
						// 第0列编号被隐藏，此处不做操作
						String offic_num = (String) table.getValueAt(i, 1);// 获取第一列的办公电话
						String mobile_num = (String) table.getValueAt(i, 2);// 获取第二列的移动电话
						String email_str = (String) table.getValueAt(i, 3);// 获取第三列电子邮箱
						String qq_num = (String) table.getValueAt(i, 4);// 获取第四列QQ号
						// 创建通讯信息对象
						Communication updateCom = new Communication(updateCust,
								offic_num, mobile_num, email_str, qq_num);
						// 向数据库添加此通讯信息数据
						dao.addCommunication(updateCom, MainFrame.getUser());
					}// for结束
						// 弹出对话框
					JOptionPane.showMessageDialog(AddCustomerFrame.this,
							"添加成功！");
					dispose();// 销毁窗体
				}// if结束
			}// actionPerformed()结束
		});// saveBtn.addActionListener()结束
	}// addAction()结束

	/**
	 * 校验信息
	 * 
	 * @return 校验是否成功
	 */
	private boolean checkInfo() {
		boolean result = true;// 校验结果变量，默认校验成功
		StringBuilder sb = new StringBuilder();// 提示信息字符串
		String name = nameText.getText();// 获取名字输入框的内容
		if ("".equals(name) || null == name) {// 如果名字是空的
			result = false;// 校验结果为不通过
			sb.append("姓名不能为空！\n");// 记录错误日志
		}// if结束

		String sex = (String) sexCombo.getSelectedItem();// 获取性别下拉框中的文本
		if ("".equals(sex) || null == sex) {// 如果性别是空的
			result = false;// 校验结果为不通过
			sb.append("性别不能为空！\n");// 记录错误日志
		}// if结束

		String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";// 创建日期正则表达式
		String birth = birthText.getText();// 获取输入的日期内容
		if (!birth.matches(regex)) {// 如果日期不符合正则表达式
			result = false;// 校验结果为不通过
			sb.append("日期格式错误！\n");// 记录错误日志
		}// if结束

		int rowcount = table.getRowCount();// 获取表格中的行数
		for (int i = 0; i < rowcount; i++) {// 遍历表格所有行
			// 获取第一列的办公电话
			String offic_num = (String) table.getValueAt(i, 1);
			// 获取第二列的移动电话
			String mobile_num = (String) table.getValueAt(i, 2);
			// 获取第三列电子邮箱
			String email_str = (String) table.getValueAt(i, 3);
			// 获取第四列QQ号
			String qq_num = (String) table.getValueAt(i, 4);
			// 如果办公电话不是数字并且不为空
			if (!offic_num.matches("[0-9]+") && !offic_num.equals("")) {
				result = false;// 校验结果为不通过
				sb.append("办公电话必须都是数字！\n");// 记录错误日志
			}// if结束
				// 如果移动电话不是数字并且不为空
			if (!mobile_num.matches("[0-9]+") && !mobile_num.equals("")) {
				result = false;// 校验结果为不通过
				sb.append("移动电话必须都是数字！\n");// 记录错误日志
			}// if结束
				// 如果电子邮箱不符合邮箱格式并且不为空
			if (!email_str.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}")
					&& !email_str.equals("")) {
				result = false;// 校验结果为不通过
				sb.append(email_str+"电子邮箱格式错误！\n");// 记录错误日志
			}// if结束
				// 如果QQ号不是5-11为的数组并且不为空
			if (!qq_num.matches("[0-9]{5,11}") && !qq_num.equals("")) {
				result = false;// 校验结果为不通过
				sb.append(qq_num+"QQ号码存在错误内容！\n");// 记录错误日志
			}// if结束
		}// for结束

		if (!result) {// 如果校验失败
			// 弹出对话框，展示日志信息
			JOptionPane.showMessageDialog(this, sb.toString());
		}// if结束
		return result;
	}// checkInfo()结束

	/**
	 * 销毁窗体
	 */
	public void dispose() {
		super.dispose();// 调用父类窗体销毁方法
		frame.initTable();// 父窗体更新表格数据
	}// dispose()结束
}
