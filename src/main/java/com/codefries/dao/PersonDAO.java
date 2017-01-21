package com.codefries.dao;

import java.util.List;

import com.codefries.model.Person;

public interface PersonDAO {
	
	void savePerson(Person newPerson);
	
	void deletePerson(long id);
	
	void updatePerson(Person toBeUpdated);
	
	List<Person> getAllPersons();
	
	Person getPersonById(long id);

}
