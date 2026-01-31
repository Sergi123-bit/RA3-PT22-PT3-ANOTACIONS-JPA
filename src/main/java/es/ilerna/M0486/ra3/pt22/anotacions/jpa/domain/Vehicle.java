package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;
import javax.persistence.*;





@Entity
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.JOINED) // Herencia: cada subclase tendrá su tabla

public class Vehicle {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MUY IMPORTANTE LO DE GENERATION TYPE SINO AL REGISTRAR +1 EN LA BD, PETA
	@Column (name="id")
	private int id;
	
	@Column (name="brand")
	private String brand;
	
	@Column (name="year")
	private int year;
	
	@Column (name="price")
	private float price;
	
	
	// Relación: muchos vehículos a 1 persona
	@ManyToOne
	@JoinColumn(name = "person_id") // Columna FK que apunta a Person (Atento: Aqui se nombra como person, pero en la bd es la columna person_id)
	private Person person;
	
	public Vehicle() { }
	
	/**
	 * Constructor completo (sin id, sin persona)
	 * Necesario para usar en subclases como Car, Plane o Motorcycle
	 */
	public Vehicle(String brand, int year, float price) 
	{
		this.brand = brand;
		this.year = year;
		this.price = price;
	}
	
	// Getters y setters
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getBrand() { return brand; }
	public void setBrand(String brand) { this.brand = brand; }
	
	public int getYear() { return year; }
	public void setYear(int year) { this.year = year; }
	
	public float getPrice() { return price; }
	public void setPrice(float price) { this.price = price; }
	
	
	
	
	public Person getPerson() { return person; }
	public void setPerson(Person person) { this.person = person; }
	
	@Override
	public String toString() 
	{
		return "Vehicle [id: " + id + " | brand: " + brand + " | year: " + year + " | price: " + price + " | person_id: " + (person != null ? person.getId() : "null") + "]";
	}


}
