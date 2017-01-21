package com.codefries.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.codefries.model.Gender;
import com.codefries.model.Person;
import com.codefries.util.DatabaseUtil;
import com.codefries.util.DateUtils;

public class PersonDAOImpl implements PersonDAO {
	
	private Connection connection;
	
	public PersonDAOImpl() {
		this.connection = DatabaseUtil.getConnection();
	}

	public void savePerson(Person newPerson) {
		try {
			String query = "INSERT INTO person (id, firstName, lastName, gender, birthDate) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, newPerson.getId());
			preparedStatement.setString(2, newPerson.getFirstName());
			preparedStatement.setString(3, newPerson.getLastName());
			preparedStatement.setString(4, String.valueOf(newPerson.getGender()));
			preparedStatement.setDate(5, new Date(DateUtils.asDate(newPerson.getBirthDate()).getTime()));
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error inserting person.", e);
		}
	}

	public void deletePerson(long id) {
		try {
			String query = "DELETE FROM person WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error deleting person.", e);
		}
	}

	public void updatePerson(Person toBeUpdated) {
		try {
			final String query = "UPDATE person SET firstName = ?, lastName = ?, gender = ?, birthDate = ?  WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, toBeUpdated.getFirstName());
			preparedStatement.setString(2, toBeUpdated.getLastName());
			preparedStatement.setString(3, toBeUpdated.getGender().toString());
			preparedStatement.setDate(4, new Date(DateUtils.asDate(toBeUpdated.getBirthDate()).getTime()));
			preparedStatement.setLong(5, toBeUpdated.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch(SQLException e) {
			throw new RuntimeException("Error updating person.", e);
		}
	}

	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<Person>();
		try {
			String query = "SELECT * FROM person";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				long id = resultSet.getLong("id");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				Date birthDate = resultSet.getDate("birthDate");
				Gender gender = Gender.valueOf(resultSet.getString("gender"));
				Person person = new Person(id, firstName, lastName, DateUtils.asLocalDate(new java.util.Date(birthDate.getTime())), gender);
				persons.add(person);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error getting all persons.");
		}
		return persons;
	}

	public Person getPersonById(long id) {
		Person person = Person.EMPTY;
		try {
			final String query = "SELECT * FROM person WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				person = new Person(id);
				person.setFirstName(resultSet.getString("firstName"));
				person.setLastName(resultSet.getString("lastName"));
				person.setGender(Gender.valueOf(resultSet.getString("gender")));
				person.setBirthDate(DateUtils.asLocalDate(new java.util.Date(resultSet.getDate("birthDate").getTime())));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error getting person with id " + id, e);
		}
		return person;
	}
}
