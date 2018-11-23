package com.corso.activities.core.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable=false)
	private String name;
	private String address;
	private String city;
	private String province;
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL) //CASCADE ci dice che le operazioni devono 
											  				 //essere su tutti i derviati e va messo
															 // su un unico lato
	
	private Set<Activity> activities; /*ci va sempre l'interfaccia della connection API
									   	MAI Array list o altri. Hybernate ci popola i campi
									  	con le sue classi */
	
	public Customer() {}
	
	public Customer(String name, String address, String city, String province) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.province = province;}

	public Customer(String name) {
		this.name = name;}

		
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
			if (!(obj instanceof Customer))
				return false;
			Customer other = (Customer) obj;
			if(id==null && other.id==null && this != other) {
				return false;
			}
			return Objects.equals(id, other.id);
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCity() {
			return city;
		}
		@Override
		public String toString() {
			return "Customer [" + (id != null ? "id=" + id + ", " : "") + (name != null ? "name=" + name + ", " : "")
					+ (address != null ? "address=" + address + ", " : "") + (city != null ? "city=" + city + ", " : "")
					+ (province != null ? "province=" + province : "") + "]";
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public Set<Activity> getActivities() {
			return activities;
		}
		public void setActivities(Set<Activity> activities) {
			this.activities = activities;
		}
		
		public void addActivity(Activity activity){
			if(this.activities == null) {
				this.activities = new HashSet<>();
			}
			this.activities.add(activity);
			activity.setCustomer(this);
		}
}
