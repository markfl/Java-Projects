package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FixMySQLScript {

	public static void main(String[] args) {
		
		StringBuilder text = new StringBuilder();
		
		int counter = 0;
		String line;
		String inputFileName = "F:\\Users Shared Folders\\markfl\\Documents\\My Development\\My SQL Source\\DSC Logistics\\createmy.old.sql";
		
		try (BufferedReader in = new BufferedReader(new 
				InputStreamReader(new FileInputStream(inputFileName), "UTF-8"))) {
			while ((line  = in.readLine()) != null ) {
				
				counter++;
				if (counter == 2175) {
					// System.out.println(line);
				}
				
				int a = line.indexOf("char(");
				int b = line.indexOf(")", a);
				if (a > 0 && b > 0) {
					String lengthOfChar = line.substring(a+5, b);
 					int lengthOfCharInt = Integer.parseInt(lengthOfChar);
 					if (lengthOfCharInt > 255) {
 						line = line.replace("char", "text");
 					}
				}
				
				a = line.indexOf("lines char");
				if (a >= 1) line = line.replace("lines", "lines_");
				
				
				System.out.println(counter + " " + line);
				
				text.append(line + "\n");

				try (FileOutputStream out = new FileOutputStream(new File("F:\\\\Users Shared Folders\\\\markfl\\\\Documents\\\\My Development\\\\My SQL Source\\\\DSC Logistics\\\\createmy.sql"))) {
					out.write(text.toString().getBytes());
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
