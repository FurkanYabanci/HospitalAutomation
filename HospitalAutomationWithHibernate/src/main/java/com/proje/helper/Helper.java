package com.proje.helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.okButtonText", "Tamam");
	}
	

	public static void showMessage(String str) {
		String message;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			message = "Lütfen tüm alanlarý doldurunuz!";
			break;

		case "success":
			message = "Ýþlem baþarýlý !";
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
			message = "Bu iþlemi gerçekleþtirmek istiyor musunuz?";
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
