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
 * �û��������
 *
 */
public class AdminUserInterfaceFrame extends JDialog {
	private JFrame frame;// ������
	private FixedTable table;// �û��б���
	private DefaultTableModel tableModel;// �������ģ��
	private Dao dao;// ���ݿ�ӿ�
	private JButton addUser_btn;// ����û���ť
	private JButton updateUser_btn;// �޸��û���ť
	private JButton delUser_btn;// ɾ���û���ť
	private JButton back_btn;// �رհ�ť

	/**
	 * ���췽��
	 * 
	 * @param frame
	 *            - ������
	 */
	public AdminUserInterfaceFrame(JFrame frame) {
		super(frame, true);// ���ø��๹�췽��������������
		this.frame = frame;
		dao = DaoFactory.getDao();

		setTitle("�û�����	");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(frame.getX() + 5, frame.getY() + 5, 520, 291);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel("�û��б�");
		panel.add(label, BorderLayout.NORTH);

		List<User> list = dao.selectUsableUser();// ��ȡ������Ч�û���Ϣ
		int userCount = list.size();
		String[] columnNames = { "id", "�û���", "Ȩ��" }; // ��������������
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

		addUser_btn = new JButton("������û�");
		southPanel.add(addUser_btn);

		updateUser_btn = new JButton("�޸��û���Ϣ");
		southPanel.add(updateUser_btn);

		delUser_btn = new JButton("ɾ���û�");
		southPanel.add(delUser_btn);

		back_btn = new JButton("�ر�");
		southPanel.add(back_btn);

		addAction();
	}

	/**
	 * ��Ӽ���
	 */
	private void addAction() {
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// ���ٴ���
			}
		});

		updateUser_btn.addActionListener(new ActionListener() {// �޸��û���Ϣ��ť����¼�����
			public void actionPerformed(ActionEvent e) {// �����ʱ
				int rowindex = table.getSelectedRow();// ��ȡѡ���е�����
				if (rowindex != -1) {// �����ѡ�е���
					String id = (String) table.getValueAt(rowindex, 0);// ��ȡ��һ���û�id
					User update = dao.selectUser(Integer.parseInt(id));// ����id��ó־û��û�����
					// �����޸��û�����
					UpdateUserframe updateFrame = new UpdateUserframe(update, frame);
					updateFrame.setVisible(true);// �޸��û�����ɼ�
					dispose();// ����������
				}
			}
		});

		delUser_btn.addActionListener(new ActionListener() {// ɾ���û���ť����¼�����
			public void actionPerformed(ActionEvent e) {// �����ʱ
				int rowindex = table.getSelectedRow();// ��ȡѡ���е�����
				if (rowindex != -1) {// �����ѡ�е���
					// ������ʾ
					int i = JOptionPane.showConfirmDialog(AdminUserInterfaceFrame.this,
							"ȷ���Ƿ�ɾ��" + table.getValueAt(rowindex, 1) + "?", "ע�⣡", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {// ���ѡ��yes
						String id = (String) table.getValueAt(rowindex, 0);// ��ȡ��ѡ�е�һ��idֵ
						User del = new User();// ����Ҫ��ɾ�����û�����
						del.setId(Integer.parseInt(id));// �����û�id
						dao.deleteUser(del, MainFrame.getUser());// ���ݿ���ɾ�����û�
						tableModel.removeRow(table.getSelectedRow());// ���ɾ��ѡ����
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
