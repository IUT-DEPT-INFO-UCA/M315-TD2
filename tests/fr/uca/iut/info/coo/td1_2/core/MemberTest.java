package fr.uca.iut.info.coo.td1_2.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemberTest {

	@Test
	void test() {
		Member m = new Member ("John");
		assertTrue(m != null);
		assertEquals("John",m.getName());
		Member m1 = new Member ("John");
		assertEquals(m,m1);
	}
	
	@Test
	void testEquals() {
		Member m = new Member ("John");
		Member m1 = new Member ("John");
		assertEquals(m,m1);
		assertFalse(m==m1);
	}

}
