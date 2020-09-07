package fr.uca.iut.info.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryTest {


	private Object o;
	private NamedObjectList list;

	private String fileName = "test0";
	
	//The directory with its contents created during test execution will be deleted once the test methods have finished execution.
	@TempDir
	Path tempDir;
	 
	 

	public static class NamedObject implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String name = "inconnu";

		NamedObject(String nom){
			name = nom;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NamedObject other = (NamedObject) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} 
			else 
				if (!name.equals(other.name))
					return false;
			return true;
		}

	}

	public static class NamedObjectList implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<NamedObject> list = new ArrayList<>();

	    public List<NamedObject> getList() {
			return list;
		}

		public void setList(List<NamedObject> list) {
			this.list = list;
		}

		public void addElem(NamedObject no) {
	    	list.add(no);
	    }
	    
	  

	}
	
	@BeforeEach
	public void setUp() throws IOException {
		assertTrue(Files.isDirectory(this.tempDir));
		Files.createFile(
                tempDir.resolve(fileName));
		o = new NamedObject("nom");
		Memory.save(o, fileName);
		list = new NamedObjectList();
		list.addElem((NamedObject) o);
	}


	@Test
	public void testRead() throws IOException, ClassNotFoundException {
		Object object = Memory.read(fileName);
		assertEquals(o, object);
	}
	
	@Test
	public void testReadList() throws IOException, ClassNotFoundException {
		Memory.save(list, fileName);
		NamedObjectList objectList = (NamedObjectList) Memory.read(fileName);
		assertTrue(objectList.getList() != null);
		assertEquals(1, objectList.getList().size());
	}
	
	
	@Test
	public void testReadlongerList() throws IOException, ClassNotFoundException {
		list.addElem(new NamedObject("nombis"));
		Memory.save(list, fileName);
		
		NamedObjectList objectList = (NamedObjectList) Memory.read(fileName);
		assertTrue(objectList.getList() != null);
		assertEquals(2, objectList.getList().size());
		assertTrue(objectList.getList().contains(o));
	}

	
	@Test
	public void testSaveNewFile() throws IOException, ClassNotFoundException {
		final String fileName2 = "test2";
		Files.createFile(
                tempDir.resolve(fileName2));
		Object newObject = new NamedObject(fileName2);
		Memory.save(newObject, fileName2);
		Object object = Memory.read(fileName2);
		assertEquals(newObject, object);
	}

	@Test
	public void testSaveInExistingFile() throws IOException, ClassNotFoundException {
		Object newObject = new NamedObject("nom2");
		Memory.save(newObject, fileName);
		Object object = Memory.read(fileName);
		assertEquals(newObject, object);
	}

}