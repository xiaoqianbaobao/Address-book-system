package main;

import javax.swing.UIManager;

import frame.LoginFrame;

/**
 * �������������
 *
 */
public class Start {
	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();// ����BeautyEye����
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginFrame frame = new LoginFrame();// ��½����
		frame.setVisible(true);// ����ɼ�
	}
}
