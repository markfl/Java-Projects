package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Person;

public class PersonTableModel extends AbstractTableModel {
	
	private List<Person> db;
	
	private String[] colNames = {"ID", "First Name", "Last Name", "Occupation", "Age Category", "Employment Category", "US Citizen", "Tax ID", "Gender"};
	
	public PersonTableModel() {
		
	}
	
	@Override
	public String getColumnName(int column) {
		
		return colNames[column];
	}

	public void setData(List<Person> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = db.get(row);
		
		switch(col) {
		case 0:
			return person.getId();
		case 1:
			return person.getFirstName();
		case 2:
			return person.getLastName();
		case 3:
			return person.getOccupation();
		case 4:
			return person.getAgeCategory();
		case 5:
			return person.getEmploymentCategory();
		case 6:
			return person.isUsCitizen();
		case 7:
			return person.getTaxId();
		case 8:
			return person.getGender();
		}
		
		return null;
	}
}