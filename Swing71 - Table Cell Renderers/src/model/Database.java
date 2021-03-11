package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
	
	private List<Person> people;
	
	Connection con;
	
	private int port;
	private String user;
	private String password;
	
	public Database() {
		people = new LinkedList<Person>();
	}
	
	public void configure(int port, String user, String password) throws Exception {
		
		this.port = port;
		this.user = user;
		this.password = password;
		
		if(con != null) {
			disconnect();
			connect();
		}
	}
	
	public void connect() throws Exception {
			
		if (con != null) return;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String URL = "jdbc:mysql://localhost:3306/webshop";
		con = DriverManager.getConnection(URL, user, password);
		System.out.println("Database connected: " + con);
		
	}
	
	public void disconnect() {
		if(con != null) {
			try {
				con.close();
				System.out.println("Database disconnected.");
			} catch (SQLException e) {
				System.out.println("Database can't disconnected.");
			}
		}
	}
	
	public void save() throws SQLException {
		
		String checkSql = "Select count(*) as count from people where id=?";
		PreparedStatement checkStmt = con.prepareStatement(checkSql);
		
		String insertSql = "insert into people (id, first_name, last_name, occupation, age, employment_status, us_citizen, taxid, gender) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		
		String updateSql = "update people set first_name = ?, last_name = ?, occupation = ?, age = ?, employment_status = ?, us_citizen = ?, taxid = ?, gender = ? where id = ?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);
		
		for(Person person: people) {
			int id = person.getId();
			String firstName = person.getFirstName();
			String lastName = person.getLastName();
			String occupation = person.getOccupation();
			AgeCategory age = person.getAgeCategory();
			EmploymentCategory emp = person.getEmploymentCategory();
			String taxId = person.getTaxId();
			boolean isUs = person.isUsCitizen();
			Gender gender = person.getGender();
			
			checkStmt.setInt(1, id);
			
			ResultSet checkResult = checkStmt.executeQuery();
			checkResult.next();
			
			int count = checkResult.getInt(1);
			
			if(count == 0 ) {
				System.out.println("Inserting person with ID " + id);
				
				int col = 1;
				insertStatement.setInt(col++, id);
				insertStatement.setString(col++, firstName);
				insertStatement.setString(col++, lastName);
				insertStatement.setString(col++, occupation);
				insertStatement.setString(col++, age.name());
				insertStatement.setString(col++, emp.name());
				insertStatement.setBoolean(col++, isUs);
				insertStatement.setString(col++, taxId);
				insertStatement.setString(col++, gender.name());
				
				insertStatement.executeUpdate();
				
			} else {
				System.out.println("Updating person with ID " + id);
				
				int col = 1;
				updateStatement.setString(col++, firstName);
				updateStatement.setString(col++, lastName);
				updateStatement.setString(col++, occupation);
				updateStatement.setString(col++, age.name());
				updateStatement.setString(col++, emp.name());
				updateStatement.setBoolean(col++, isUs);
				updateStatement.setString(col++, taxId);
				updateStatement.setString(col++, gender.name());
				updateStatement.setInt(col++, id);
				
				updateStatement.executeUpdate();
			}
		}
		
		checkStmt.close();
		insertStatement.close();
		updateStatement.close();
	}
	
	public void load() throws SQLException {
		people.clear();
		
		String sql = "select id, first_name, last_name, occupation, age, employment_status, us_citizen, taxid, gender from people order by first_name";
		Statement selectStatement = con.createStatement();
		
		ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next()) {
			int id = results.getInt("id");
			String firstName = results.getString("first_name");
			String lastName = results.getString("last_name");
			String occupation = results.getString("occupation");
			String age = results.getString("age");
			String emp = results.getString("employment_status");
			boolean isUs = results.getBoolean("us_citizen");
			String taxId = results.getString("taxid");
			String gender = results.getString("gender");
			
			Person person = new Person(id, firstName, lastName, occupation, AgeCategory.valueOf(age), EmploymentCategory.valueOf(emp), isUs, taxId, Gender.valueOf(gender));
			people.add(person);
			
			System.out.println(person);
			
		}
		
		selectStatement.close();
	}
	
	public void addPerson(Person person) {
		people.add(person);
	}
	
	public void removePerson(int index) {
		people.remove(index);
	}
	
	public List<Person> getPeople() {
		return Collections.unmodifiableList(people);
	}
	
	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Person[] persons = people.toArray(new Person[people.size()]);
		
		oos.writeObject(persons);
		
		oos.close();
	}
	
	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Person[] persons = (Person[])ois.readObject();
			
			people.clear();
			
			people.addAll(Arrays.asList(persons));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ois.close();
	}
}