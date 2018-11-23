package com.corso.activities.core.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Activity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;   //il wrapper non il tipo primitivo, questo perchè a volte dovremo portarlo a null
	@Lob
	private String description;
	@ManyToOne
	private Customer customer;
	
	@OneToMany(mappedBy="activity", cascade=CascadeType.ALL)
	private Set<TimeSpent> timeSpents;
	
	public Activity() {}
	
	public Activity(String description) {
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
		if (!(obj instanceof Activity))
			return false;
		Activity other = (Activity) obj;
		if(id==null && other.id==null && this != other) {
			return false;
		}
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Activity [" + (id != null ? "id=" + id + ", " : "")
				+ (description != null ? "description=" + description : "") + "]";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<TimeSpent> getTimeSpent() {
		return timeSpents;
	}

	public void setTimeSpent(Set<TimeSpent> timeSpents) {
		this.timeSpents = timeSpents;
	}
	
	public void addTimeSpent(TimeSpent timeSpents){
		if(this.timeSpents == null) {
			this.timeSpents = new HashSet<>();
		}
		this.timeSpents.add(timeSpents);
		timeSpents.setActivity(this);
	}
	
}
