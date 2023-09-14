package jpa;


import dao.DaoAppointment;
import dao.DaoStudent;
import dao.DaoTeacher;
import domain.Appointment;
import domain.Person;
import domain.Student;
import domain.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JpaTest {
	private EntityManager manager;
	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();
		JpaTest test = new JpaTest(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		// Create all DAO
		DaoTeacher teacherDao = new DaoTeacher();
		DaoStudent studentDao = new DaoStudent();
		DaoAppointment appointmentDao = new DaoAppointment();

		// Create entities
		Person teach1 = new Teacher("INRIA");
		Person teach2 = new Teacher("INRIA");
		Person teach3 = new Teacher("IRISA");
		teach1.setName("Olivier BARRAIS");
		teach2.setName("Adrien LEROCH");
		teach3.setName("Fabrice LAMARCHE");

		Person stud1 = new Student(20151511);
		Person stud2 = new Student(20458899);
		Person stud3 = new Student(20657435);
		stud1.setName("Walid ABDOU");
		stud2.setName("Aymerick LEBORGNE");
		stud3.setName("Florian ALPHONZAIR");

		Appointment appoint1 = new Appointment(new Date(2023,9,12), (Student) stud1, (Teacher) teach1);
		Appointment appoint2 = new Appointment(new Date(2023, 10, 16), (Student) stud2, (Teacher) teach2);
		Appointment appoint3 = new Appointment();
		Appointment appoint4 = new Appointment();

		List<Appointment> listApp1 = List.of(appoint1);
		List<Appointment> listApp2 = List.of(appoint2);
		((Student) stud1).setAppointments(listApp1);
		((Teacher) teach1).setAppointments(listApp1);
		((Student) stud2).setAppointments(listApp2);
		((Student) teach2).setAppointments(listApp2);

		try {
			// Make entities persistent
			teacherDao.save(teach1);
			teacherDao.save(teach2);
			teacherDao.save(teach3);

			studentDao.save(stud1);
			studentDao.save(stud2);
			studentDao.save(stud3);

			appointmentDao.save(appoint1);
			appointmentDao.save(appoint2);
			appointmentDao.save(appoint3);
			appointmentDao.save(appoint4);

			//	test.createEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		//test.listEmployees();
		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}
}
