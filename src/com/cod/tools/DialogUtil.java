package com.cod.tools;

import javax.swing.JOptionPane;

public class DialogUtil {
	public static void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, msg, JOptionPane.ERROR_MESSAGE);
	}
}
