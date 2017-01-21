package com.lexim.console;

import java.time.LocalDate;
import java.time.Month;

import com.lexim.dao.PersonDAO;
import com.lexim.dao.PersonDAOImpl;
import com.lexim.model.Gender;
import com.lexim.model.Person;

public class PersonDAOTester {

	public static void main(String[] args) {
		PersonDAO personDAO = new PersonDAOImpl();
		personDAO.savePerson(new Person(6, "Junald", "Bayog", LocalDate.of(1996, Month.MAY, 12), Gender.MALE));
		System.out.println("Person inserted successfully.");
		
		personDAO.deletePerson(6);
		System.out.println("Person delete successfully.");
		
		System.out.println("List of Persons");
		for(Person person: personDAO.getAllPersons()) {
			System.out.println(person);
		}
		
		personDAO.updatePerson(new Person(1, "Junald", "Bayog", LocalDate.of(1996, Month.APRIL, 12), Gender.MALE));
		System.out.println("Person updated successfully.");
	}
}
