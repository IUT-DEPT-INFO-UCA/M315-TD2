package fr.uca.iut.info.coo.td1_2.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class MemberManager {

	protected HashMap<String, Member> members = new HashMap<>();

	public MemberManager() {
		super();
	}

	public Member addMember(Member m) {
		 return members.computeIfAbsent(m.getName(), k-> m);
		//An "equivalent" but less efficient code
			/*
			 * Member x = members.get(m.getName()); 
			 * if (x==null) { 
			 *          members.put(m.getName(), m); 
			 *          return m; } 
			 * //else a member with this name is already registered else
			 * return x;
			 */
	}

	/**
	 * create a member and add it to the list of members
	 * @param name of the member to add
	 * @return the new ({@code Member} if there is no existing member with that name, otherwise the member already known by that name
	 */
	public Member addMember(String name) {
		return members.computeIfAbsent(name, Member::new);
	}
	
	/**
	 * @return the list of registered members
	 */
	public List<Member> getMembers() {
		List<Member> allMembers = new ArrayList<>();
		allMembers.addAll(members.values());
		return allMembers;
	}
	
	/**
	 * @param name of the member
	 * @return Member 
	 * the value to which the name is mapped, or null if there is no member with this name.
	 */
	public Member getMember(String name) {
		return members.get(name);
	}
	
	
	/**
	 * @return a Set of members' names.
	 */
	public Set<String> getMemberNames(){
		return members.keySet();
	}

}