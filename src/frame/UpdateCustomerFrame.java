package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.mr.contact.dao.Dao;
import com.mr.contact.dao.DaoFactory;
import com.mr.contact.swing.CustomerFrame;
import com.mr.contact.swing.FixedTable;

import pojo.Communication;
import pojo.Customer;

/**
 * 修改客户信息窗口
 *
 */
public class UpdateCustomerFrame extends CustomerFrame {
	private final byte ALLSOUR = 0x5a;// 获取全部数据
	private final byte USABLESOUR = 0x1a;// 获取有效数据
	private Dao dao;// 数据库接口
	private FixedTable table;// 通讯信息表格
	private DefaultTableModel tableModel; // 通讯信息数据模型
	private JTextField nameText;// 姓名输入框
	private JTextField workUnitText;// 工作单位输入框
	private JTextField roleText;// 职位输入框
	private JTextField workAddressText;// 工作地址输入框
	private JTextField homeText;// 家庭住址输入框
	private JTextField birthText;// 出生日期输入框
	private Customer cust;// 被修改的客户
	private JButton cancelBtn;// 关闭按钮
	private JButton saveBtn;// 保存按钮
	private JComboBox<String> sexComboBox;// 性别下拉框
	private JButton delRowBtn;// 删除行按钮
	private JButton addRowBtn;// 添加行按钮
	private JCheckBox chckbxNewCheckBox;// 隐藏失效信息单选框
	private MainFrame frame;// 父窗体

	/**
	 * 构造方法
	 * 
	 * @param cust
	 *            - 被修改的客户
	 * @param frame
	 *            - 父窗体
	 */
	public UpdateCustomerFrame(Customer cust, JFrame frame) {
		// 调用父类创建“修改”窗口的构造方法
		super(frame, CustomerFrame.UPDATE);
		this.cust = cust;// 记录被修改的客户
		this.frame = (MainFrame) frame;// 记录父窗体
		setTitle("修改客户信息"); // 设置标题
		dao = DaoFactory.getDao();// 实例化数据库接口对象
		init();// 组件初始化
		addAction();// 添加监听
	}// UpdateCustomerFrame()结束

	/**
	 * 组件初始化
	 */
	private void init() {
		cust = dao.selectCustomer(cust.getId());// 将cust持久化
		setTableModelSource(USABLESOUR);// 获取表格数据
		table = getTable();// 创建指定表格模型的表格
		table.setModel(tableModel);// 加载表格数据
		table.setCellEditable(true);// 表格可以编辑
		table.hiddenFirstColumn();// 隐藏第一列

		nameText = getNameText();// 获取姓名输入框
		nameText.setText(cust.getName());// 姓名输入框展示客户名

		sexComboBox = getSexCombo();// 获取性别下拉框
		if (null == cust.getSex()) {// 如果性别为null
			sexComboBox.setSelectedIndex(0);// 选择第一项
		} else if (cust.getSex().equals("女")) {
			sexComboBox.setSelectedIndex(2);// 选择第三项
		} else {
			sexComboBox.setSelectedIndex(1);// 选择第二项
		}// else结束

		birthText = getBirthText();// 获取生日输入框
		if (null != cust.getBirth()) {// 如果生日不为空
			birthText.setText(cust.getBirth());// 生日输入框展示客户生日
		}// if结束

		workUnitText = getWorkUnitText();// 获取工作单位输入框
		if (null != cust.getWork_unit()) {// 如果工作单位不为空
			workUnitText.setText(cust.getWork_unit());// 工作单位输入框展示客户工作单位
		}// if结束

		roleText = getRoleText();// 获取职位输入框
		if (null != cust.getRole()) {// 如果职位不为空
			roleText.setText(cust.getRole());// 职位输入框展示客户职位
		}// if结束

		workAddressText = getWorkAddressText();// 获取工作地点输入框
		if (null != cust.getWork_addr()) {// 如果工作地点不为空
			workAddressText.setText(cust.getWork_addr());// 工作地点输入框展示客户工作地点
		}// if结束

		homeText = getHomeText();// 获取家庭住址输入框
		if (null != cust.getHome_addr()) {// 如果家庭住址不为空
			homeText.setText(cust.getHome_addr());// 家庭住址输入框展示客户家庭住址
		}// if结束

		chckbxNewCheckBox = getChckbxNewCheckBox();// 获取隐藏

		JPanel southPanel = new JPanel();// 创建南部面板
		southPanel.setLayout(new GridLayout(1, 2));// 面板使用1行2列的网格布局
		getContentPane().add(southPanel, BorderLayout.SOUTH);// 将次面板放到主容器在南部

		FlowLayout p4layout = new FlowLayout();// 创建流布局
		p4layout.setHgap(20);// 水平间隔20像素
		
		JPanel leftButtonPanel = new JPanel();// 创建左侧按钮面板
		leftButtonPanel.setLayout(p4layout);// 左侧按钮面板使用创建的流布局
		southPanel.add(leftButtonPanel);// 南部面板添加左侧按钮面板

		delRowBtn = new JButton("删除行");// 实例化删除行按钮
		leftButtonPanel.add(delRowBtn);// 左侧按钮面板添加删除行按钮

		addRowBtn = new JButton("添加行");// 实例化添加行按钮
		leftButtonPanel.add(addRowBtn);// 左侧面板添加添加行按钮

	
		JPanel rightButtonPanel = new JPanel();// 创建右侧按钮面板
		rightButtonPanel.setLayout(p4layout);// 右侧按钮面板使用创建的流布局
		southPanel.add(rightButtonPanel);// 南部面板添加右侧按钮面板

		saveBtn = new JButton("保存");// 实例化保存按钮
		rightButtonPanel.add(saveBtn);// 右侧按钮面板添加保存按钮

		cancelBtn = new JButton("取消");// 实例化取消按钮
		rightButtonPanel.add(cancelBtn);// 右侧按钮面板添加取消按钮
	}// init()结束

	/**
	 * 添加动作监听
	 */
	private void addAction() {
		cancelBtn.addActionListener(new ActionListener() {// 取消按钮
					public void actionPerformed(ActionEvent e) {
						dispose();// 销毁窗体
					}// actionPerformed()结束
				});// cancelBtn.addActionListener()结束

		addRowBtn.addActionListener(new ActionListener() {// 添加行按钮
					public void actionPerformed(ActionEvent e) {// 点击时
						Communication addCom = new Communication();// 创建空通讯信息
						addCom.setCust(cust);// 信息所属客户为cust
						addCom.setAvailable("Y");// 默认可用
						dao.addCommunication(addCom, MainFrame.getUser());// 数据库添加一个空记录
						String id = "" + addCom.getId();// 获取记录ID
						String[] add = { id, "", "", "", "", "Y" };// 创建空数据
						tableModel.addRow(add);// 表格数据模型添加一行
					}// actionPerformed()结束
				});// addRowBtn.addActionListener()结束

		delRowBtn.addActionListener(new ActionListener() {// 删除行按钮
					public void actionPerformed(ActionEvent e) {// 点击时
						int selectedRow = table.getSelectedRow();// 获得被选中行的索引
						if (selectedRow != -1) { // 判断是否存在被选中行
							// 获取表格选中行的第一列数据
							String id = (String) table.getValueAt(selectedRow,
									0);
							Communication del = new Communication();// 创建空通讯信息
							del.setId(Integer.parseInt(id));// 信息的编号为id
							// 调用数据库结果，将该行信息失效
							dao.deleteCommunication(del, MainFrame.getUser());
							tableModel.removeRow(selectedRow);// 表格数据模型删除选中行
						}// if结束
					}// actionPerformed()结束
				});// delRowBtn.addActionListener()结束

		saveBtn.addActionListener(new ActionListener() {// 保存按钮
			public void actionPerformed(ActionEvent e) {
				if (table.isEditing()) {// 让表格停止编辑状态
					table.getCellEditor().stopCellEditing();
				}// if结束
				if (checkInfo()) {// /如果所有信息校验合格
					// 根据输入框中的信息创建准备更新的客户对象
					Customer updateCust = new Customer(nameText.getText()
							.trim(), // 姓名
							(String) sexComboBox.getSelectedItem(),// 性别
							birthText.getText().trim(),// 出生日期
							workUnitText.getText().trim(),// 工作单位
							workAddressText.getText().trim(),// 工作地点
							homeText.getText().trim(),// 家庭住址
							roleText.getText().trim());// 职位
					updateCust.setId(cust.getId());// 设置ID
					updateCust.setAvailable("Y");// 设置可用
					dao.updateCustomer(updateCust, MainFrame.getUser());// 更新客户信息

					int rowcount = table.getRowCount();// 获取表格行数
					for (int i = 0; i < rowcount; i++) {// 遍历所有行
						String id = (String) table.getValueAt(i, 0);// 通讯信息ID
						String officNum = (String) table.getValueAt(i, 1);// 办公电话
						String mobileNum = (String) table.getValueAt(i, 2);// 移动电话
						String emailStr = (String) table.getValueAt(i, 3);// 电子邮箱
						String qqNum = (String) table.getValueAt(i, 4);// QQ号
						String available = (String) table.getValueAt(i, 5);// 是否有效
						// 根据表格中数据，创建通讯信息对象
						Communication updateCom = new Communication(cust,
								officNum, mobileNum, emailStr, qqNum);
						updateCom.setId(Integer.parseInt(id));// 设置通讯信息编号
						updateCom.setAvailable(available);// 设置通讯信息是否有效
						dao.updateCommunication(updateCom, MainFrame.getUser());// 更新通讯信息
					}// for结束
						// 弹出对话框
					JOptionPane.showMessageDialog(UpdateCustomerFrame.this,
							"保存成功！");
					dispose();// 销毁窗体
				}// if结束
			}// actionPerformed()结束
		});// saveBtn.addActionListener()结束

		chckbxNewCheckBox.addChangeListener(new ChangeListener() {// 是否隐藏失效信息单选框
					public void stateChanged(ChangeEvent e) {// 当选择时
						if (chckbxNewCheckBox.isSelected()) {// 如果单选框被选中
							setTableModelSource(USABLESOUR);// 获取有效数据
						} else {
							setTableModelSource(ALLSOUR);// 获取所有数据
						}// else结束
						table.setModel(tableModel);// 表格载入数据模型
					}// stateChanged()结束
				});// chckbxNewCheckBox.addChangeListener()结束
	}// addAction()结束

	/**
	 * 获取用户通讯信息
	 * 
	 * @param type
	 *            - 获取数据类型
	 * @return 用户通讯信息
	 */
	private void setTableModelSource(byte type) {
		if (tableModel == null) {// 如果表格数据模型不是空的
			// 定义表格列名数组
			String[] columnNames = { "编号", "办公电话", "移动电话", "电子邮箱", "QQ", "是否有效" };
			tableModel = new DefaultTableModel();// 实例化表格数据模型
			tableModel.setColumnIdentifiers(columnNames);// 载入表格列名
		}// if结束
		if (tableModel.getRowCount() > 0) {// 如果表格中有数据行数大于0
			tableModel.getDataVector().clear();// 清空表数据
			tableModel.fireTableDataChanged();// 重新绘制表格数据
		}// if结束

		List<Communication> usableList = null;// 创建用于展示的通讯信息集合
		if (type == ALLSOUR) {// 如果数据类型是所有数据
			// 获取此客户的所有数据信息
			usableList = dao.selectCustmerCommunicationAll(cust);
		} else if (type == USABLESOUR) {// 如果数据类型是有效数据
			// 获取此客户的有效数据信息
			usableList = dao.selectCustmerCommunicationUsable(cust);
		} else {// 如果是其他数据类型
			return;// 结束方法
		}// else结束
		int comCount = usableList.size();// 获取通讯信息集合长度
		String[] tableValues = new String[6];// 创建保存一行通讯信息数据的数组
		for (int i = 0; i < comCount; i++) {// 遍历通讯信息集合
			Communication com = usableList.get(i);// 获取集合中的通讯信息对象
			tableValues[0] = "" + com.getId();// 记录ID的字符串值
			tableValues[1] = com.getOffice_phone();// 记录办公电话
			tableValues[2] = com.getMobile_phone();// 记录移动电话
			tableValues[3] = com.getEmail();// 记录电子邮箱
			tableValues[4] = com.getQq();// 记录QQ
			tableValues[5] = com.getAvailable();// 记录是否有效
			tableModel.addRow(tableValues);// 表格数据模型添加一行数据
		}// for结束
	}// setTableModelSource()结束

	/**
	 * 检查用户信息是否符合格式
	 * 
	 * @return 是否合格
	 */
	private boolean checkInfo() {
		boolean result = true;// 创建检验结果变量，默认通过
		StringBuilder sb = new StringBuilder();// 错误日志字符串
		String name = nameText.getText();// 获取名字
		if ("".equals(name) || null == name) {// 如果名字是空的
			result = false;// 校验结果为不通过
			sb.append("姓名不能为空！\n");// 记录错误日志
		}// if结束

		// 获取用户性别
		String sex = (String) sexComboBox.getSelectedItem();
		if ("".equals(sex) || null == sex) {// 如果性别是空的
			result = false;// 校验结果为不通过
			sb.append("性别不能为空！\n");// 记录错误日志
		}// if结束
		String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";// 创建出生日期格式正则表达式
		String birth = birthText.getText();// 获取出生日期
		// 如果出生日期不为空，且不是日期格式
		if (!(birth.matches(regex) || birth.equals(""))) {
			result = false;// 校验结果为不通过
			sb.append("日期格式错误！\n");// 记录错误日志
		}// if结束

		int rowcount = tableModel.getRowCount();// 获取表格行数量
		for (int i = 0; i < rowcount; i++) {// 遍历表格中所有行
			// 获取第二列的办公电话
			String offic_num = (String) tableModel.getValueAt(i, 1);
			// 获取第三列的移动电话
			String mobile_num = (String) tableModel.getValueAt(i, 2);
			// 获取第四列的电子邮件
			String email_str = (String) tableModel.getValueAt(i, 3);
			// 获取第五列的QQ号码
			String qq_num = (String) tableModel.getValueAt(i, 4);
			// 获取第六列的有效标志
			String available = (String) tableModel.getValueAt(i, 5);
			// 如果办公电话不为空，且不是有效数字
			if (!(offic_num.matches("[0-9]+") || offic_num.equals(""))) {
				result = false;// 校验结果为不通过
				sb.append("办公电话存在错误内容！\n");// 记录错误日志
			}// if结束
				// 如果移动电话不为空，且不是有效数字
			if (!(mobile_num.matches("[0-9]+") || mobile_num.equals(""))) {
				result = false;// 校验结果为不通过
				sb.append("移动电话存在错误内容！\n");// 记录错误日志
			}// if结束
				// 如果电子邮箱地址不为空，且不是有效邮箱格式
			if (!(email_str.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}") || email_str
					.equals(""))) {
				result = false;// 校验结果为不通过
				sb.append("电子邮箱格式错误！\n");// 记录错误日志
			}// if结束
				// 如果QQ号码不为空，且不是有效QQ号码
			if (!(qq_num.matches("[0-9]{5,10}") || qq_num.equals(""))) {
				result = false;// 校验结果为不通过
				sb.append("QQ号码存在错误内容！\n");// 记录错误日志
			}// if结束
				// 如果有效标志不是Y也不是N
			if (!("Y".equals(available) || "N".equals(available))) {
				result = false;// 校验结果为不通过
				sb.append("信息有效性值只能选择Y或N！\n");// 记录错误日志
			}// if结束
		}// for结束
		if (!result) {// 校验结果为不通过
			// 弹出对话框，展示日志信息
			JOptionPane.showMessageDialog(null, sb.toString());
		}// if结束
		return result;
	}// checkInfo()结束

	/**
	 * 窗体销毁
	 */
	public void dispose() {
		super.dispose();// 调用父类窗体销毁方法
		frame.initTable();// 父窗体更新表格数据
	}// dispose()结束
}// UpdateCustomerFrame结束
