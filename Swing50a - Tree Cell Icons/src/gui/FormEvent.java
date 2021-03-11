package gui;
import java.util.EventObject;

public class FormEvent extends EventObject{

	private String firstName;
	private String lastName;
	private String occupation;
	private int ageCategory;
	private String employmentCategory;
	private boolean usCitizen;
	private String taxId;
	private String gender;
	
	public FormEvent(Object source) {	
		super(source);
	}
	
	public FormEvent(Object source, String firstName, String lastName, String occupation, int ageCat, String empCat, 
					 boolean usCit, String taxId, String gender) {	
		super(source);
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.occupation = occupation;
		this.ageCategory = ageCat;
		this.employmentCategory = empCat;
		this.usCitizen = usCit;
		this.taxId = taxId;
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(int ageCategory) {
		this.ageCategory = ageCategory;
	}

	public String getEmploymentCategory() {
		return employmentCategory;
	}

	public void setEmploymentCategory(String employmentCategory) {
		this.employmentCategory = employmentCategory;
	}

	public boolean isUsCitizen() {
		return usCitizen;
	}

	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}	
}