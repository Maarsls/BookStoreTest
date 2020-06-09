package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoryTest {

	public static void main(String[] args) {
		Category newCat= new Category("Java");
		
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entitymanager = managerFactory.createEntityManager();
		
		entitymanager.getTransaction().begin();
		
		entitymanager.persist(newCat);
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		managerFactory.close();
		
		System.out.println("A Category object was created");
	}

}
