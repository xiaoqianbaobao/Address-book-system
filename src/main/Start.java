package main;

import javax.swing.UIManager;

import frame.LoginFrame;

/**
 * 程序运行入口类
 *
 */
public class Start {
	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();// 启用BeautyEye主题
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginFrame frame = new LoginFrame();// 登陆窗体
		frame.setVisible(true);// 窗体可见
	}
}
