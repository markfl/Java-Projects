package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import gui.FormEvent;
import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
	Database db = new Database();
	
	public List<Person> getPeople() {
		return db.getPeople();
	}
	
	public void removePerson(int index) {
		db.removePerson(index);
	}
	
	public void save() throws SQLException {
		db.save();
	}
	
	public void load() throws SQLException {
		db.load();
	}
	
	public void configure(int port, String user, String password) throws Exception {
		db.configure(port, user, password);
	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	public void addPerson(FormEvent ev) {
		String firstName = ev.getFirstName();
		String lastName = ev.getLastName();
		String occupation = ev.getOccupation();
		int ageCatId = ev.getAgeCategory();
		String empCatId = ev.getEmploymentCategory();
		boolean usCitizen = ev.isUsCitizen();
		String taxId = ev.getTaxId();
		String gender = ev.getGender();
		
		AgeCategory ageCategory = null;
		
		switch(ageCatId) {
		case 0:
			ageCategory = AgeCategory.child;
			break;
		case 1:
			ageCategory = AgeCategory.adult;
			break;
		case 2:
			ageCategory = AgeCategory.senior;
			break;
		}
		
		EmploymentCategory employmentCategory;
		
		if(empCatId.equals("employed")) {
			employmentCategory = EmploymentCategory.employed;
		} else if(empCatId.equals("self-employed")) {
			employmentCategory = EmploymentCategory.selfemployed;
		} else if(empCatId.equals("unemployed")) {
			employmentCategory = EmploymentCategory.unemployed;
		} else if(empCatId.equals("retired")) {
			employmentCategory = EmploymentCategory.retired;
		} else {
			employmentCategory = EmploymentCategory.other;
			System.err.println(empCatId);
		}
		
		Gender genderCat;
		
		if(gender.contentEquals("male")) {
			genderCat = Gender.male;
		} else {
			genderCat = Gender.female;
		}
		
		Person person = new Person(firstName, lastName, occupation, ageCategory, employmentCategory, usCitizen, taxId, genderCat);
		
		db.addPerson(person);
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
}