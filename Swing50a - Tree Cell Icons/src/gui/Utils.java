package gui;

import java.net.URL;

import javax.swing.ImageIcon;

public class Utils {
	
	public static String getFileExtension(String name) {
		
		int pointerIndex = name.lastIndexOf(",");
		
		if(pointerIndex == -1) {
			return null;
		} else if(pointerIndex == name.length()-1) {
			return null;
		}
		
		return name.substring(pointerIndex+1, name.length());
	}
	public static ImageIcon createIcon(String path) {
		URL url = System.class.getResource(path);
		
		if(url == null) {
			System.err.println("Unable to load image: " + path);
		}
		
		ImageIcon icon = new ImageIcon(url);
		
		return icon;
	}
}

