import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class fixsql {

	public static void main(String[] args) {
		
		StringBuilder text = new StringBuilder();
		Boolean addGo = false;
		
		try (BufferedReader in = new BufferedReader(new 
					InputStreamReader(new FileInputStream("F:\\Users Shared Folders\\markfl\\Documents\\My Development\\My SQL Source\\Hub Group\\viewtm.sql"), "UTF-8"))) {
			String line;
			while ((line  = in.readLine()) != null ) {
				int a = line.indexOf(";");
				if (a > 0) addGo = true;
				
				text.append(line + "\n");
				// System.out.print(text);
				if (addGo) text.append("Go\n");
				addGo = false;
				
				
				try (FileOutputStream out = new FileOutputStream(new File("F:\\Users Shared Folders\\markfl\\Documents\\My Development\\My SQL Source\\Hub Group\\view.sql"))) {
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
