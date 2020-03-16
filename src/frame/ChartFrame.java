package frame;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.mr.contact.jfree.ConstellationBarChart;
import com.mr.contact.jfree.ConstellationPieChart;
import com.mr.contact.jfree.RoleBarChart;
import com.mr.contact.jfree.RolePieChart;
import com.mr.contact.jfree.SexBarChart;
import com.mr.contact.jfree.SexPieChart;

/**
 * ͼ����
 *
 */
public class ChartFrame extends JDialog {
	public static final int BAR_FRAME = 0x5231;
	public static final int PIE_FRAME = 0x9842;
	private JPanel contentPane;// ���������

	public ChartFrame(JFrame frame, int chartType) {
		super(frame, true);// ���ø��๹�췽��������������
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(frame.getX() + 5, frame.getY() + 5, frame.getWidth() - 15, frame.getHeight() - 15);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		switch (chartType) {
		case BAR_FRAME:
			createBar();
			break;
		case PIE_FRAME:
			createPie();
			break;
		}

	}

	private void createBar() {
		setTitle("��״ͼ");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);// ��ǩ���
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		SexBarChart sexBar = new SexBarChart();// �����Ա�ͳ����״ͼ
		tabbedPane.add(sexBar.getChartPanel());
		tabbedPane.setTitleAt(0, "�Ա�");

		RoleBarChart rolebBar = new RoleBarChart();// �����Ա�ͳ����״ͼ
		tabbedPane.add(rolebBar.getChartPanel());
		tabbedPane.setTitleAt(1, "ְλ");

		ConstellationBarChart constellationBar = new ConstellationBarChart();// ��������ͳ������״ͼ
		tabbedPane.add(constellationBar.getChartPanel());
		tabbedPane.setTitleAt(2, "����");

	}

	private void createPie() {
		setTitle("��״ͼ");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);// ��ǩ���
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		SexPieChart sexBar = new SexPieChart();// �����Ա�ͳ�Ʊ�״ͼ
		tabbedPane.add(sexBar.getChartPanel());
		tabbedPane.setTitleAt(0, "�Ա�");

		RolePieChart roleBar = new RolePieChart();// ����ְλͳ�Ʊ�״ͼ
		tabbedPane.add(roleBar.getChartPanel());
		tabbedPane.setTitleAt(1, "ְλ");

		ConstellationPieChart constellationPie = new ConstellationPieChart();// ��������ͳ�Ʊ�״ͼ
		tabbedPane.add(constellationPie.getChartPanel());
		tabbedPane.setTitleAt(2, "����");
	}

}
