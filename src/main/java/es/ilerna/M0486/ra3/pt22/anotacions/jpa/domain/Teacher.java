
package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.*;



@Entity
@DiscriminatorValue("TEACHER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Teacher extends Person {
	
	@Column (name="teacherCode")
	private String teacherCode;

	
	public Teacher() { }
	
	public Teacher(String name, String surname, int phoneNumber, String teacherCode) 
	{
		this.teacherCode = teacherCode;
	}
	
	// Getters y setters
	public String getTeacherCode() { return teacherCode; }
	public void setTeacherCode(String teacherCode) { this.teacherCode = teacherCode; }

	@Override
	public String toString() 
	{
		return "Student [id: " + getId() + " | name: " + getName() + " | surname: " + getSurname() + " | phoneNumber: " + getPhoneNumber() + " | teacherCode: " + teacherCode + "]";	
	}
	
}


