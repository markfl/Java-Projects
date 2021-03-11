package model;

import java.io.Serializable;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static int count = 1;
	
	private int id;
	private String firstName;
	private String lastName;
	private String occupation;
	private AgeCategory ageCategory;
	private EmploymentCategory employmentCategory;
	private boolean usCitizen;
	private String taxId;
	private Gender gender;
	
	public Person (String firstName, String lastName, String occupation, AgeCategory ageCategory, 
					EmploymentCategory employmentCategory, boolean usCitizen, String taxId, Gender gender) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.employmentCategory = employmentCategory;
		this.usCitizen = usCitizen;
		this.taxId = taxId;
		this.gender = gender;
		
		this.id = count;
		count++;
	}
	
	public Person (int id, String firstName, String lastName, String occupation, AgeCategory ageCategory, 
			EmploymentCategory employmentCategory, boolean usCitizen, String taxId, Gender gender) {
		
		this(firstName, lastName, occupation, ageCategory, employmentCategory, usCitizen, taxId, gender);

		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}
	public EmploymentCategory getEmploymentCategory() {
		return employmentCategory;
	}
	public void setEmploymentCategory(EmploymentCategory employmentCategory) {
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
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String toString() {
		return id + ": " + firstName + " " + lastName;
	}
}