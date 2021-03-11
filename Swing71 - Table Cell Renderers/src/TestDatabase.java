import java.sql.SQLException;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class TestDatabase {

	public static void main(String[] args) {
		System.out.println("Running database test.");
		
		Database db = new Database();
		try {
			db.connect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		db.addPerson(new Person("Mark", "Flores", "Programmer", AgeCategory.adult, EmploymentCategory.other, true, "567457844", Gender.male));
		db.addPerson(new Person("Barbara", "Hendersen", "Nurse", AgeCategory.adult, EmploymentCategory.retired, true, "123456789", Gender.female));
		db.addPerson(new Person("David", "Flores", "Tester", AgeCategory.adult, EmploymentCategory.employed, true, "111111111", Gender.male));
		db.addPerson(new Person("Nancy", "Bielman", "Nurse", AgeCategory.adult, EmploymentCategory.retired, true, "1112223333", Gender.female));
		
		try {
			db.save();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			db.load();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		db.disconnect();
	}
}