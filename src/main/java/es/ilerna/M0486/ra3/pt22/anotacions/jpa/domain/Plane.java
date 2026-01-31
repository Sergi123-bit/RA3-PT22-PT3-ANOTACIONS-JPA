package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.*;

@Entity
@Table(name = "plane") // Cada subclase tiene su propia tabla
public class Plane extends Vehicle {
		
		@Column (name="tailNumber")
		private int tailNumber;
		
		@Column (name="autopilot")
		private boolean autopilot;

		// Constructor vacío
		public Plane() { }
		
		// Constructor completo (sin id)
		public Plane(String brand, int year, float price, int tailNumber, boolean autopilot)
		{
			super(brand, year, price); 
			this.tailNumber = tailNumber;
			this.autopilot = autopilot;
		}
		
		// Getters y setters.
		public int getTailNumber() { return tailNumber; }
		public void setTailNumber(int tailNumber) { this.tailNumber = tailNumber; }
		
		
		public boolean getAutopilot() { return autopilot; }
		public void setAutopilot(boolean autopilot) { this.autopilot = autopilot; }
		
		@Override
		public String toString() 
		{
			return "Plane [id: " + getId() + " | brand: " + getBrand() + " | year: " + getYear() + " | price: " + getPrice() + " | tailNumber: " + tailNumber + " | autopilot: " + autopilot + "]";
		}
		
		
}
