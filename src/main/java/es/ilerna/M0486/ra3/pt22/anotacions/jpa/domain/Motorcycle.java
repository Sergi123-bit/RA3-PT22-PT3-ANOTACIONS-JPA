package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;


import javax.persistence.*;



@Entity
@Table(name = "motorcycle") // Cada subclase tiene su propia tabla; no hace falta @PrimaryKeyJoinColumn porque JOINED hereda automáticamente el id de Vehicle
public class Motorcycle extends Vehicle {
	
		@Column (name="hasSidecar")
		private boolean hasSidecar;
		
		// Constructor vacío
		public Motorcycle() { }
		
		// Constructor completo (sin id)
		public Motorcycle(String brand, int year, float price, boolean hasSidecar) 
		{
			super(brand, year, price); // Llama al constructor de Vehicle
			this.hasSidecar = hasSidecar;
		}
		
		// Getters y setters.
		public boolean getHasSidecar() { return hasSidecar; }
		public void setHasSidecar(boolean hasSidecar) { this.hasSidecar = hasSidecar; }
		
		
		@Override
		public String toString() 
		{
			return "Motorcycle [id: " + getId() + " | brand: " + getBrand() + " | year: " + getYear() + " | price: " + getPrice() + " | hasSidecar: " + hasSidecar +  "]";
		}
		
}
