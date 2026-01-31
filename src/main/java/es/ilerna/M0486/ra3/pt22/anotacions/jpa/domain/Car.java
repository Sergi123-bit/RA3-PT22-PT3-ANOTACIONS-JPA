package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;
import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car extends Vehicle {

	@Column (name="doors")
	private int doors;
	
	@Column (name="seats")
	private int seats;

	
	
	public Car() { }
	
	/**
	 * Constructor completo (sin id, sin persona)
	 * Necesario para usar en subclases como Car, Plane o Motorcycle
	 */
	public Car(String brand, int year, float price, int doors, int seats) 
	{
		super(brand, year, price); //llama al constructor de vehicle, por eso no pusimos person alla, pq sino habia que meterlo aqui y no hace falta.
		this.doors = doors;
		this.seats = seats;
	}	
	
	// Getters y setters
	public int getDoors() { return doors; }
	public void setDoors(int doors) { this.doors = doors; }
	
			
	public int getSeats() { return seats; }
	public void setSeats(int seats) { this.seats = seats; }

	
	@Override
	public String toString() 
	{
		return "Car [id: " + getId() + " | brand: " + getBrand() + " | year: " + getYear() + " | price: " + getPrice() + " | doors: " + doors + " | seats: " + seats + "]";
	}


}
