package fr.uca.iut.info.coo.td1_2.core;

import java.io.Serializable;

public class Member implements Serializable{


	private String name;

	public Member(String pname) {
		name = pname;
	}

	public String getName() {
		return name;
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
		Member other = (Member) obj;
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
