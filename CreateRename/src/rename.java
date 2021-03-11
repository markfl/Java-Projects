import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class rename {

	public static void main(String[] args) {
		
		StringBuilder text = new StringBuilder();
		
		try (BufferedReader in = new BufferedReader(new 
					InputStreamReader(new FileInputStream("F:\\Users Shared Folders\\markfl\\Documents\\My Development\\iSeriesSource\\ITPurge\\it.txt"), "UTF-8"))) {
			String line;
			while ((line  = in.readLine()) != null ) {
				int a = line.indexOf(".");
				String srcType = line.substring(0, a);
				String srcName = line.substring(a+1);
				String lowerType = srcType.toLowerCase();
				String lowerName = srcName.toLowerCase();
				
				text.append("ren " + line + " " + lowerName + "." + lowerType + "\n");
				// System.out.print(text);
				
				/* try (BufferedWriter out = new BufferedWriter(new 
						OutputStreamWriter(new FileOutputStream("ren.bat"), "UTF-8"))) {
					out.writ
				} catch (IOException e) {
					e.printStackTrace();
				} */
				try (FileOutputStream out = new FileOutputStream(new File("F:\\Users Shared Folders\\markfl\\Documents\\My Development\\iSeriesSource\\ITPurge\\ren.bat"))) {
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