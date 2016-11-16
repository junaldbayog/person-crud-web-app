package com.lexim.dao;

import java.util.List;

import com.lexim.model.Person;

public interface PersonDAO {
	
	void savePerson(Person newPerson);
	
	void deletePerson(long id);
	
	void updatePerson(Person toBeUpdated);
	
	List<Person> getAllPersons();
	
	Person getPersonById(long id);

}
