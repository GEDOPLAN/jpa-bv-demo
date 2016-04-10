package de.gedoplan.jpa_bv;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonTest {

	private static EntityManagerFactory entityManagerFactory;

	@BeforeClass
	public static void beforeClass() {
		PersonTest.entityManagerFactory = Persistence.createEntityManagerFactory("default");
	}

	private EntityManager entityManager;

	@Before
	public void before() {
		this.entityManager = entityManagerFactory.createEntityManager();
		this.entityManager.getTransaction().begin();
	}

	@After
	public void after() {
		EntityTransaction transaction = this.entityManager.getTransaction();
		if (transaction.isActive()) {
			if (!transaction.getRollbackOnly()) {
				System.out.println("commit");
				transaction.commit();
			} else {
				transaction.rollback();
			}
		}

		this.entityManager.close();
	}

	@Test
	public void test_01_persist() {
		System.out.println("----- test_01_persist -----");
		System.out.println("persist");
		this.entityManager.persist(new Person("Willi Wacker"));
	}

	@Test
	public void test_02_persistChange() {
		System.out.println("----- test_02_persistChange -----");
		Person person = new Person("Bob Baumeister");
		System.out.println("persist");
		this.entityManager.persist(person);
		System.out.println("change");
		person.setName(person.getName() + "+");
	}

	@Test
	public void test_03_persistChangeFlush() {
		System.out.println("----- test_03_persistChangeFlush -----");
		Person person = new Person("Bob Baumeister");
		System.out.println("persist");
		this.entityManager.persist(person);
		System.out.println("change");
		person.setName(person.getName() + "+");
		System.out.println("flush");
		this.entityManager.flush();
	}

	@Test
	public void test_04_change() throws Exception {
		System.out.println("----- test_04_change -----");
		System.out.println("query");
		List<Person> resultList = this.entityManager.createQuery("select p from Person p", Person.class)
		        .setMaxResults(1).getResultList();
		for (Person person : resultList) {
			System.out.println("change");
			person.setName(person.getName() + "+");
		}
	}

	@Test
	public void test_05_ChangeFlush() throws Exception {
		System.out.println("----- test_05_ChangeFlush -----");
		System.out.println("query");
		List<Person> resultList = this.entityManager.createQuery("select p from Person p", Person.class)
		        .setMaxResults(1).getResultList();
		for (Person person : resultList) {
			System.out.println("change");
			person.setName(person.getName() + "+");
			System.out.println("flush");
			this.entityManager.flush();
		}
	}
}
