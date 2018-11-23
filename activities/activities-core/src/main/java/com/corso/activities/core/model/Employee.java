package com.corso.activities.core.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
//import javax.persistence.Table;

@Entity
// @Table(name="employeeTbl") questo per mappare DB esistenti MA è sconsigliato!!!
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;   //il wrapper non il tipo primitivo, questo perchè a volte dovremo portarlo a null
	private String firstName;
	private String lastName;
	@ManyToMany(mappedBy="employees")
	private List<TimeSpent> timeSpents;  
	
	
	public Employee() {
	}
	
	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<TimeSpent> getTimeSpents() {
		return timeSpents;
	}

	public void setTimeSpents(List<TimeSpent> timeSpents) {
		this.timeSpents = timeSpents;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if(id==null && other.id==null && this != other) {
			return false;
		}
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Employee [" + (id != null ? "id=" + id + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName : "") + "]";
	}
	
	
}
