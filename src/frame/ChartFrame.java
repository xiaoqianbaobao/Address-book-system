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
 * 图表窗口
 *
 */
public class ChartFrame extends JDialog {
	public static final int BAR_FRAME = 0x5231;
	public static final int PIE_FRAME = 0x9842;
	private JPanel contentPane;// 主容器面板

	public ChartFrame(JFrame frame, int chartType) {
		super(frame, true);// 调用父类构造方法，阻塞父窗体
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
		setTitle("柱状图");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);// 标签面板
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		SexBarChart sexBar = new SexBarChart();// 创建性别统计柱状图
		tabbedPane.add(sexBar.getChartPanel());
		tabbedPane.setTitleAt(0, "性别");

		RoleBarChart rolebBar = new RoleBarChart();// 创建性别统计柱状图
		tabbedPane.add(rolebBar.getChartPanel());
		tabbedPane.setTitleAt(1, "职位");

		ConstellationBarChart constellationBar = new ConstellationBarChart();// 创建星座统计柱子状图
		tabbedPane.add(constellationBar.getChartPanel());
		tabbedPane.setTitleAt(2, "星座");

	}

	private void createPie() {
		setTitle("饼状图");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);// 标签面板
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		SexPieChart sexBar = new SexPieChart();// 创建性别统计饼状图
		tabbedPane.add(sexBar.getChartPanel());
		tabbedPane.setTitleAt(0, "性别");

		RolePieChart roleBar = new RolePieChart();// 创建职位统计饼状图
		tabbedPane.add(roleBar.getChartPanel());
		tabbedPane.setTitleAt(1, "职位");

		ConstellationPieChart constellationPie = new ConstellationPieChart();// 创建星座统计饼状图
		tabbedPane.add(constellationPie.getChartPanel());
		tabbedPane.setTitleAt(2, "星座");
	}

}
