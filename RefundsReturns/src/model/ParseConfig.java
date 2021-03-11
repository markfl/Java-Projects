package model;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ParseConfig {

	public static void main(String[] args) {

		Database db = new Database();
		
		String output[] = db.parseXMLConfig();
		System.out.println(output[0] + "\n" + output[1] + "\n" + output[2]);
		
 
	}
}