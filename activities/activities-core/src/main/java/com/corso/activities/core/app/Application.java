package com.corso.activities.core.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.corso.activities.core.dao.EmployeeDao;
import com.corso.activities.core.dao.jpa.JpaEmployeeDao;
import com.corso.activities.core.model.Activity;
import com.corso.activities.core.model.Customer;
import com.corso.activities.core.model.Employee;
import com.corso.activities.core.model.TimeSpent;

public class Application {

	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("activities"); 
		
		EntityManager em = emf.createEntityManager(); //voglio mantenere i metodi connessi
		EmployeeDao employeeDao = new JpaEmployeeDao(em);
		printEmployeeReport(employeeDao.findAll());
		
		em.close();
		
//		populate(emf);
//		printEmployeeReport(getEmployeesForTesting());
//		printEmployeeReportFromDb(emf.createEntityManager());
		emf.close();
		
	}
	
/*	private static void printEmployeeReportFromDb(EntityManager em) {
		em.getTransaction().begin();
		Query query = em.createQuery("select e from Employee e");
		List resultList= query.getResultList();
		printEmployeeReport(resultList);
		em.getTransaction().commit();
		em.close();
	}*/

	private static void populate(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Customer customer = new Customer("First Consulting");
		
		Activity javaActivity = new Activity("Teaching Java");
		customer.addActivity(javaActivity);
		Activity sqlActivity = new Activity("Teaching SQL");
		customer.addActivity(sqlActivity);
		Activity jpaActivity = new Activity("Teaching JPA");
		customer.addActivity(jpaActivity);
		
		
		Employee employee1 = new Employee ("Luca", "Scevola");
		Employee employee2 = new Employee ("Irene", "Bonardi");
		
		TimeSpent timeSpent = new TimeSpent();
		timeSpent.addEmployee(employee1);
		timeSpent.addEmployee(employee2);
		timeSpent.setStart(2018, 11 , 20, 9, 0);
		timeSpent.setEnd(2018, 11 , 20, 13, 0);
		javaActivity.addTimeSpent(timeSpent);
		
		timeSpent = new TimeSpent();
		timeSpent.addEmployee(employee1);
		timeSpent.setStart(2018, 11 , 20, 14, 0);
		timeSpent.setEnd(2018, 11 , 20, 18, 0);
		javaActivity.addTimeSpent(timeSpent);
		
		timeSpent = new TimeSpent();
		timeSpent.addEmployee(employee2);
		timeSpent.setStart(2018, 11 , 21, 14, 0);
		timeSpent.setEnd(2018, 11 , 21, 18, 0);
		javaActivity.addTimeSpent(timeSpent);
		em.persist(customer);
		
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static Collection<Employee> getEmployeesForTesting() {
		
		Customer customer = new Customer("First Consulting");
		
		Activity javaActivity = new Activity("Teaching Java");
		customer.addActivity(javaActivity);
		Activity sqlActivity = new Activity("Teaching SQL");
		customer.addActivity(sqlActivity);
		Activity jpaActivity = new Activity("Teaching JPA");
		customer.addActivity(jpaActivity);
		
		
		Employee employee1 = new Employee ("Luca", "Scevola");
		Employee employee2 = new Employee ("Irene", "Bonardi");
		
		TimeSpent timeSpent = new TimeSpent();
		timeSpent.addEmployee(employee1);
		timeSpent.addEmployee(employee2);
		timeSpent.setStart(2018, 11 , 20, 9, 0);
		timeSpent.setEnd(2018, 11 , 20, 13, 0);
		javaActivity.addTimeSpent(timeSpent);
		
		timeSpent = new TimeSpent();
		timeSpent.addEmployee(employee1);
		timeSpent.setStart(2018, 11 , 20, 14, 0);
		timeSpent.setEnd(2018, 11 , 20, 18, 0);
		javaActivity.addTimeSpent(timeSpent);
		
		timeSpent = new TimeSpent();
		timeSpent.addEmployee(employee2);
		timeSpent.setStart(2018, 11 , 21, 14, 0);
		timeSpent.setEnd(2018, 11 , 21, 18, 0);
		javaActivity.addTimeSpent(timeSpent);
		
		List<Employee> result = new ArrayList();
		result.add(employee1);
		result.add(employee2);
		return result;
	}
	
	public static void printEmployeeReport(Collection<Employee> employees) {
		for(Employee employee: employees) {
			
			System.out.println("L'impiegato: " + employee.getFirstName() + " " + employee.getLastName());
		for(TimeSpent timeSpent: employee.getTimeSpents() ){
			/*System.out.println("Ha iniziato alle ore: " + timeSpent.getStart() + " e finito alle: " + timeSpent.getEnd() + 
					  " \n con il cliente: " +timeSpent.getActivity().getCustomer().getName() 
					  + " facendo: " + timeSpent.getActivity().getDescription());*/
			System.out.printf("\t %tT -> %tT (%s - %s)\n",
					timeSpent.getStart(),
					timeSpent.getEnd(),
					timeSpent.getActivity().getCustomer().getName(),
					timeSpent.getActivity().getDescription());
			
			}
		}
	}
}
