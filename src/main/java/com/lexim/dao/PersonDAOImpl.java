package com.lexim.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.lexim.model.Person;
import com.lexim.util.DatabaseUtil;
import com.lexim.util.DateUtils;

public class PersonDAOImpl implements PersonDAO {
	
	private Connection connection;
	
	public PersonDAOImpl(Connection connection) {
		this.connection = DatabaseUtil.getConnection();
	}

	public void savePerson(Person newPerson) {
		try {
			String query = "INSERT INTO person (firstName, lastName, gender, birthDate) values (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, newPerson.getFirstName());
			preparedStatement.setString(2, newPerson.getLastName());
			preparedStatement.setString(3, String.valueOf(newPerson.getGender()));
			preparedStatement.setDate(4, new Date(DateUtils.asDate(newPerson.getBirthDate()).getTime()));
		} catch (SQLException e) {
			throw new RuntimeException("Problem inserting person.", e);
		}
	}

	public void deletePerson(long id) {
		// TODO Implementation
	}

	public void updatePerson(Person toBeUpdated) {
		// TODO Implementation
	}

	public List<Person> getAllPersons() {
		// TODO Implementation
		return null;
	}

	public Person getPersonById(long id) {
		// TODO Implementation
		return null;
	}
}
