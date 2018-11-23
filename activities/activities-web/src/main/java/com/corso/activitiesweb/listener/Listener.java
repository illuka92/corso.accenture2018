package com.corso.activitiesweb.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {

    public Listener() {
    }

public void contextDestroyed(ServletContextEvent sce)  { 
    EntityManagerFactory emf= (EntityManagerFactory) 
    		sce.getServletContext().getAttribute("emf"); //cast
    emf.close(); 
}

	public void contextInitialized(ServletContextEvent sce)  { 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("activities");
		sce.getServletContext().setAttribute("emf", emf);
	}
}