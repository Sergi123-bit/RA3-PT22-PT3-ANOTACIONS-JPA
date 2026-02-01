package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Car;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Motorcycle;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Person;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Plane;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Student;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Teacher;
import es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain.Vehicle;





public class Main 
{


	private static List<Person> people = new ArrayList<>();
	private static List<Vehicle> vehicles = new ArrayList<>();
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int opcio;

		do {
			System.out.println("\n===== MENÚ PRINCIPAL =====");
			System.out.println("1) Fase 1: Crear dades de prova");
			System.out.println("2) Fase 2: Treure vehicles de persones");
			System.out.println("3) Fase 3: Actualitzar un vehicle");
			System.out.println("0) Sortir");
			System.out.print("Escull una opció: ");

			opcio = sc.nextInt();
			sc.nextLine();

			switch (opcio) {
				case 1:
					fase1();
					break;
				case 2:
					fase2();
					break;
				case 3:
					fase3();
					break;
				case 0:
					System.out.println("Fins aviat!");
					break;
				default:
					System.out.println("Opció incorrecta.");
			}

		} while (opcio != 0);

		sc.close();
		HibernateSession.getSessionFactory().close();
	}
	
	private static void fase1() {
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			// Personas según tu formato bueno (Nom, Cognom, TelefonInt, Codi)
			Student s1 = new Student("Anna", "Lopez", 111111111, "STU001");
			Student s2 = new Student("Jordi", "Martinez", 222222222, "STU002");
			Student s3 = new Student("Clara", "Sanchez", 333333333, "STU003");
			Teacher t1 = new Teacher("Joan", "Perez", 444444444, "TEA001");
			Teacher t2 = new Teacher("Maria", "Gomez", 555555555, "TEA002");
			Teacher t3 = new Teacher("Pere", "Ruiz", 666666666, "TEA003");

			// Vehicles: Marca, Any, Preu (Atenció: el preu a Vehicle és float)
		    Car c1 = new Car("Toyota", 2020, 18000.0f, 5, 5);
		    c1.setPerson(s1); // ARA SÍ: uses el mètode que tens a Vehicle.java
		    
		    Car c2 = new Car("Ford", 2019, 15000.0f, 3, 5);
		    c2.setPerson(t2); // Corregit car2 -> c2 i setOwner -> setPerson

		    Plane p1 = new Plane("Cessna", 2015, 120000.0f, 11111, true); 
	        p1.setPerson(t1);
	        
	        Plane p2 = new Plane("Boeing", 2010, 900000.0f, 22222, false);
	        p2.setPerson(s3);
	        Motorcycle m1 = new Motorcycle("Yamaha", 2021, 9000.0f, true);
	        m1.setPerson(s2);
	        
	        Motorcycle m2 = new Motorcycle("Harley-Davidson", 2018, 20000.0f, false);
	        m2.setPerson(t3);

			// Guardar todo con session.save y nombres de variables correctos
			session.save(s1); session.save(s2); session.save(s3);
			session.save(t1); session.save(t2); session.save(t3);
			session.save(c1); session.save(c2); session.save(p1);
			session.save(p2); session.save(m1); session.save(m2);

			tx.commit();
			System.out.println("Fase 1 completada.");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static void fase2() {
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Vehicle v = session.get(Vehicle.class, 1);
			if (v != null) {
			    v.setPerson(null); // Posem la persona a null tal com demana l'exercici
			    session.update(v);
			}
			tx.commit();
			System.out.println("Fase 2: Propietari del vehicle 1 eliminat.");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			session.close();
		}
	}

	private static void fase3() {
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Vehicle v = session.get(Vehicle.class, 1);
			if (v != null) {
			    v.setBrand("Seat");
			    v.setPrice(19999.0f); // Important la 'f' perquè el teu camp és float
			    v.setYear(2022);
			    session.update(v); // Guardem els canvis
			}
			tx.commit();
			System.out.println("Fase 3: Vehicle 1 actualitzat a Seat.");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}