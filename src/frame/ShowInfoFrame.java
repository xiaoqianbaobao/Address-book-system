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
 * 展示客户详细信息窗口
 *
 */
public class ShowInfoFrame extends CustomerFrame {
	private Dao dao;// 数据库接口
	private MainFrame frame;// 父窗体
	private Customer cust;// 要展示信息的客户
	private FixedTable table;// 通讯信息表格
	private JTextField nameText;// 姓名输入框
	private JTextField workUnitText;// 工作单位输入框
	private JTextField roleText;// 职位输入框
	private JTextField workAddressText;// 工作地点输入框
	private JTextField homeText;// 家庭住址输入框
	private JTextField birthText;// 生日输入框
	private JTextField sexText;// 性别输入框
	private DefaultTableModel tableModel; // 通讯信息表格数据模型

	/**
	 * 构造方法
	 * 
	 * @param cust
	 *            要展示信息的客户
	 * @param frame
	 *            - 父窗体
	 */
	public ShowInfoFrame(Customer cust, JFrame frame) {
		// 调用父类创建“展示”窗口的构造方法
		super(frame, CustomerFrame.SHOW);
		this.cust = cust;
		this.frame = (MainFrame) frame;
		setTitle("详细信息");
		dao = DaoFactory.getDao();// 实例化数据库接口对象

		table = getTable();// 获取窗体表格
		table.setCellEditable(false);// 表格不可编辑

		tableModel = getTableModel();// 获取窗体表格模型
		initTbleModel();// 初始化窗体表格数据

		nameText = getNameText();// 获取姓名输入框
		nameText.setText(cust.getName());// 姓名输入框展示客户名

		sexText = getSexText();// 获取性别输入框
		sexText.setText(cust.getSex());// 性别输入框展示客户性别

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

		FlowLayout btnPanelLayout = new FlowLayout(FlowLayout.RIGHT);// 右对齐流布局
		JPanel btnPanel = new JPanel(btnPanelLayout);// 采用流布局的按钮面板
		getContentPane().add(btnPanel, BorderLayout.SOUTH);// 按钮面板添加到

		JButton btnNewButton = new JButton("关闭");// 创建关闭按钮
		btnPanel.add(btnNewButton);// 按钮添加到按钮面板中
		btnNewButton.addActionListener(new ActionListener() {// 关闭按钮添加监听
					public void actionPerformed(ActionEvent e) {// 当点时
						dispose();// 销毁窗体
					}// actionPerformed()结束
				});// addActionListener()结束
	}// ShowInfoFrame()结束

	/**
	 * 表格数据初始化
	 */
	private void initTbleModel() {
		if (tableModel.getRowCount() > 0) {// 如果表格中行数大于0
			tableModel.getDataVector().clear();// 清空表数据
			tableModel.fireTableDataChanged();// 重新绘制表格数据
		}// if结束
			// 获取所有要展示的客户的所有的通讯讯息
		List<Communication> usableList = dao
				.selectCustmerCommunicationUsable(cust);
		// 创建保存通讯信息的字符串数组，用于给表格数据模型赋值
		String[] tableValues = new String[5];
		for (Communication com : usableList) {// 遍历通讯信息集合
			if (com.getAvailable().endsWith("Y")) {// 如果是有效数据
				tableValues[0] = "" + com.getId();// 记录ID的字符串值
				tableValues[1] = com.getOffice_phone();// 记录办公电话
				tableValues[2] = com.getMobile_phone();// 记录移动电话
				tableValues[3] = com.getEmail();// 记录电子邮箱
				tableValues[4] = com.getQq();// 记录QQ
				tableModel.addRow(tableValues);// 表格数据模型添加一行记录
			}// if结束
		}// for结束
	}// initTbleModel()结束
}// ShowInfoFrame结束
