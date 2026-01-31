
package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.*;



@Entity
@DiscriminatorValue("STUDENT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Student extends Person {
	
	@Column (name="studentCode")
	private String studentCode;

	
	public Student() { }
	
	public Student(String name, String surname, int phoneNumber, String studentCode) 
	{
		this.studentCode = studentCode;
	}
	
	// Getters y setters
	public String getStudentCode() { return studentCode; }
	public void setStudentCode(String studentCode) { this.studentCode = studentCode; }

	@Override
	public String toString() 
	{
		return "Student [id: " + getId() + " | name: " + getName() + " | surname: " + getSurname() + " | phoneNumber: " + getPhoneNumber() + " | studentCode: " + studentCode + "]";	
	}
	
}


