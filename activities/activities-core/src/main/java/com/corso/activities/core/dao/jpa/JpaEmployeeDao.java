package com.corso.activities.core.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.corso.activities.core.dao.EmployeeDao;
import com.corso.activities.core.model.Employee;

public class JpaEmployeeDao implements EmployeeDao {
	
	private EntityManager em;

	public JpaEmployeeDao(EntityManager em) {
		this.em = em;
	}

	@Override
	public Employee findById(Long id) {
		return em.find(Employee.class, id);
		
	}

	@Override
	public Employee findByFirstNameAndLastName(String firstName, String lastName) {
		TypedQuery<Employee> query = em.createQuery(
				"select e from Employee where e.firstName = :fn and e.lastName= :ln", 
				Employee.class);
		query.setParameter("fn", firstName);
		query.setParameter("ln", lastName);
		return query.getSingleResult();
		
	}

	@Override
	public List<Employee> findAll() {
		em.getTransaction().begin();
		TypedQuery<Employee> query = em.createQuery("select e from Employee e", Employee.class);
		List<Employee> resultList= query.getResultList();
		em.getTransaction().commit();
		return resultList;
	}

}
