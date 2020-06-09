package com.bookstore.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	private static EntityManagerFactory managerFactory;
	private static EntityManager entitymanager;
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setupClass() {
		managerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entitymanager = managerFactory.createEntityManager();

		userDAO = new UserDAO(entitymanager);
	}
	
	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("marcel.maffey7@gmail.com");
		user1.setFullname("Maffey Marcel7");
		user1.setPassword("marcel1234");

		user1 = userDAO.create(user1);

		assertTrue(user1.getUserId() > 0);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);

		
	}
	
	@Test
	public void testUpdateUsers() {
		Users user=new Users();
		user.setUserId(3);
		user.setEmail("maffeym@gmx.at");
		user.setFullname("Marcel");
		user.setPassword("1234");
		
		user = userDAO.update(user);
		
		String expected="maffeym@gmx.at";
		String actual= user.getEmail();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testGetUsersFound() {
		Integer userId=44;
		Users user=userDAO.get(userId);
		if(user!=null) {
		System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId=1;
		Users user= userDAO.get(userId);
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId=3;
		userDAO.delete(userId);
		
		Users user = userDAO.get(userId);
		assertNull(user);
	}
	
	@Test(expected=Exception.class)
	public void testDeleteNonExistUsers() {
		Integer userId=3;
		userDAO.delete(userId);
	}
	
	@AfterClass
	public static void tearDownClass() {
		entitymanager.close();
		managerFactory.close();
	}
}
