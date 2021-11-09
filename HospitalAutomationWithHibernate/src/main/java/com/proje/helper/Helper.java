package com.proje.helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "�ptal");
		UIManager.put("OptionPane.noButtonText", "Hay�r");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.okButtonText", "Tamam");
	}
	

	public static void showMessage(String str) {
		String message;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			message = "L�tfen t�m alanlar� doldurunuz!";
			break;

		case "success":
			message = "��lem ba�ar�l� !";
			break;
			
		default:
			message = str;
			break;
		}
		JOptionPane.showMessageDialog(null, message,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static boolean confirm(String str) {
		String message;
		optionPaneChangeButtonText();
		switch (str) {
		case "sure":
			message = "Bu i�lemi ger�ekle�tirmek istiyor musunuz?";
			break;

		default:
			message = str;
			break;
		}
		int res = JOptionPane.showConfirmDialog(null, message,"Mesaj",JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			return true;
		}else {
			return false;
		}
	}
}
