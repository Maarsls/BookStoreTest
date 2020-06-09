package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsersTest {

	public static void main(String[] args) {
		Users user1 = new Users();
		user1.setEmail("marcel.maffey2@gmail.com");
		user1.setFullname("Maffey Marcel2");
		user1.setPassword("marcel1234");
		
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entitymanager = managerFactory.createEntityManager();
		
		entitymanager.getTransaction().begin();
		
		entitymanager.persist(user1);
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		managerFactory.close();
		
		System.out.println("A Users object was created");
	}

}
