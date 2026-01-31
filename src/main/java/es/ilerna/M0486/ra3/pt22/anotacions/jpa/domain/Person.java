
package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;



@Entity
@Table (name="person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@DiscriminatorColumn( //columna para saber si es Student o Teacher
		name = "person_type",
		discriminatorType = DiscriminatorType.STRING
	)

public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private int id;
	
	@Column (name="name")
	private String name;
	
	@Column (name="surname")
	private String surname;
	
	@Column (name="phoneNumber")
	private int phoneNumber;
	
	// Relación 1 persona a muchos vehiculos
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<Vehicle> vehicles = new ArrayList<>();
	
	public Person() { }
	
	public Person(String name, String surname, int phoneNumber) 
	{
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
	}
	
	
	
	public void addVehicle(Vehicle vehicle) 
	{
		vehicles.add(vehicle);
		vehicle.setPerson(this);
	}

	public void removeVehicle(Vehicle vehicle) 
	{
		vehicles.remove(vehicle);
		vehicle.setPerson(null);
	}
	
	
	
	
	
	// Getters y setters
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getSurname() { return surname; }
	public void setSurname(String surname) { this.surname = surname; }
	
	public int getPhoneNumber() { return phoneNumber; }
	public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }

	@Override
	public String toString() 
	{
		return "Person [id: " + id + " | name: " + name + " | surname: " + surname + " | phoneNumber: " + phoneNumber + "]";
	}
	


}
