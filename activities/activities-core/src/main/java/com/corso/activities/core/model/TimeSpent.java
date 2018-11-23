package com.corso.activities.core.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TimeSpent {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;   //il wrapper non il tipo primitivo, questo perchè a volte dovremo portarlo a null
	@Column(name="startDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	@Column(name="endDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	@Lob
	private String description;
	@ManyToOne
	private Activity activity;
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	//@JoinTable(name="Employee_TimeSpent", 
	//joinColumns= {@JoinColumn(table="employee", referencedColumnName="id")} ,
	//@JoinColumn(table="employee", referencedColumnName="id")})  questo se dovessi mappare da un DB esistente
	private List<Employee> employees;
	
	public TimeSpent() {
	}
	
	public TimeSpent(Date start, String description) {
		this.start = start;
		this.description = description;
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
		if (!(obj instanceof TimeSpent))
			return false;
		TimeSpent other = (TimeSpent) obj;
		if(id==null && other.id==null && this != other) {
			return false;
		}
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "TimeSpent [" + (id != null ? "id=" + id + ", " : "") + (start != null ? "start=" + start + ", " : "")
				+ (end != null ? "end=" + end + ", " : "") + (description != null ? "description=" + description : "")
				+ "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	
	public void setStart(int year, int month, int day, int hour, int minute) {
		setStart(java.sql.Timestamp.valueOf(LocalDateTime.of(year , month, day, hour, minute)));
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public void setEnd(int year, int month, int day, int hour, int minute) {
		setEnd(java.sql.Timestamp.valueOf(LocalDateTime.of(year , month, day, hour, minute)));
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee employee){
		if(this.employees == null) {
			this.employees = new LinkedList<>();
		}
		this.employees.add(employee);
		
		if(employee.getTimeSpents() == null) {
			employee.setTimeSpents(new LinkedList<>());
		}
		employee.getTimeSpents().add(this);
	}
	

}
