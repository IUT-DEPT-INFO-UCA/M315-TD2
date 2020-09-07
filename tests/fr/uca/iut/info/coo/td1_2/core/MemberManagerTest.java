package fr.uca.iut.info.coo.td1_2.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberManagerTest {

	MemberManager manager;
	
	@BeforeEach
	public void setUp()  {
		 manager = new MemberManager();
		
	}
	
	@Test
	void testInit() {
		assertTrue(manager.getMembers().isEmpty());
	}
	
	@Test
	void testAddMember() {
		Member john = new Member("John");
		Member x = manager.addMember(john);
		assertTrue(manager.getMembers().contains(john));
		assertTrue(x==john);
		
		Member john2 = new Member("John");
		x = manager.addMember(john2);
		assertTrue(x==john);
		assertTrue(manager.getMembers().contains(john));
		assertEquals(1, manager.getMembers().size());
		
		Member mary = new Member("mary");
		x = manager.addMember(mary);
		assertTrue(x==mary);
		assertTrue(manager.getMembers().contains(mary));
		assertEquals(2, manager.getMembers().size());
		
	}
	
	
	

	@Test
	void testgetMember() {
		Member john = new Member("John");
		manager.addMember(john);
		assertEquals(john, manager.getMember("John"));
		assertEquals(null, manager.getMember("Mary"));
	}
	
	@Test
	void testgetMemberNames() {
		Member john = new Member("John");
		manager.addMember(john);
		Member mary = new Member("mary");
		manager.addMember(mary);
		Set<String> names = manager.getMemberNames();
		assertEquals(2, names.size());
		assertTrue(names.contains("mary"));
		
	}
	
}
