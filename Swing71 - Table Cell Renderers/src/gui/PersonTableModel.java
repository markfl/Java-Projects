package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.EmploymentCategory;
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

	@Override
	public boolean isCellEditable(int row, int col) {
		
		switch(col) {
		case 1:
			return true;
		case 2:
			return true;
		case 6:
			return true;
		default:
			return false;
		}
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {

		if(db == null) return;
		
		Person person = db.get(row);
		switch(col) {
		case 1:
			person.setFirstName((String)value);
			break;
		case 2:
			person.setLastName((String)value);
			break;
		case 6:
			person.setUsCitizen((boolean)value);
			break;
		default:
			return;
		}
	}

	@Override
	public Class<?> getColumnClass(int col) {
		
		switch(col) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return EmploymentCategory.class;
		case 6:
			return Boolean.class;
		case 7:
			return String.class;
		case 8:
			return String.class;
		default:
			return null;
		}
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