// CORRECCIÓ 1: El paquet ha de coincidir amb la carpeta física que es veu a la teva imatge
package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

// CORRECCIÓ 2: Importacions ajustades a la teva estructura de dominis real (pt22.domain)
import es.ilerna.M0486.ra3.pt22.domain.Car;
import es.ilerna.M0486.ra3.pt22.domain.Motorcycle;
import es.ilerna.M0486.ra3.pt22.domain.Person;
import es.ilerna.M0486.ra3.pt22.domain.Plane;
import es.ilerna.M0486.ra3.pt22.domain.Student;
import es.ilerna.M0486.ra3.pt22.domain.Teacher;
import es.ilerna.M0486.ra3.pt22.domain.Vehicle;

public class Main {

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
				case 1: fase1(); break;
				case 2: fase2(); break;
				case 3: fase3(); break;
				case 0: System.out.println("Fins aviat!"); break;
				default: System.out.println("Opció incorrecta.");
			}
		} while (opcio != 0);

		sc.close();
		// Tanca la factoria en sortir per netejar la BD (create-drop) [cite: 5]
		HibernateSession.getSessionFactory().close();
	}

	private static void fase1() {
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			// Fase 1: Omplir dades segons les taules del PDF [cite: 8, 9, 10]
			// Estudiants i Professors [cite: 12]
			Student s1 = new Student(1, "Anna", "Lopez", "111.111.111", "STU001");
			Student s2 = new Student(2, "Jordi", "Martinez", "222.222.222", "STU002");
			Student s3 = new Student(3, "Clara", "Sanchez", "333.333.333", "STU003");
			Teacher t1 = new Teacher(4, "Joan", "Perez", "444.444.444", "TEA001");
			Teacher t2 = new Teacher(5, "Maria", "Gomez", "555.555.555", "TEA002");
			Teacher t3 = new Teacher(6, "Pere", "Ruiz", "666.666.666", "TEA003");

			// Vehicles amb assignació de propietaris [cite: 14]
			Car c1 = new Car(1, "Toyota", 18000.0, 2020, 5, 5);
			c1.setOwner(s1); // person_id 1

			Car c2 = new Car(2, "Ford", 15000.0, 2019, 3, 5);
			c2.setOwner(t2); // person_id 5

			Plane p1 = new Plane(3, "Cessna", 120000.0, 2015, 1, "11.111");
			p1.setOwner(t1); // person_id 4

			Plane p2 = new Plane(4, "Boeing", 900000.0, 2010, 0, "22.222");
			p2.setOwner(s3); // person_id 3

			Motorcycle m1 = new Motorcycle(5, "Yamaha", 9000.0, 2021, 1);
			m1.setOwner(s2); // person_id 2

			Motorcycle m2 = new Motorcycle(6, "Harley-Davidson", 20000.0, 2018, 0);
			m2.setOwner(t3); // person_id 6

			// Guardar a la base de dades
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
			// Fase 2: Vehicle 1 perd l'associació amb persona 1 [cite: 34, 35]
			Vehicle v = session.get(Vehicle.class, 1);
			if (v != null) {
				v.setOwner(null); // person_id = NULL [cite: 37]
				session.update(v);
			}
			tx.commit();
			System.out.println("Fase 2 completada.");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static void fase3() {
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			// Fase 3: Modificar dades del vehicle 1 [cite: 41, 42]
			Vehicle v = session.get(Vehicle.class, 1);
			if (v != null) {
				v.setBrand("Seat");      // [cite: 44]
				v.setPrice(19999.0);     // [cite: 44]
				v.setYear(2022);         // [cite: 44]
				session.update(v);
			}
			tx.commit();
			System.out.println("Fase 3 completada.");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}