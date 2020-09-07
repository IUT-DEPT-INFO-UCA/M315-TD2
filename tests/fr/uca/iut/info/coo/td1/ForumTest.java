package fr.uca.iut.info.coo.td1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.uca.iut.info.coo.td1.Forum;
import fr.uca.iut.info.coo.td1.Member;
import fr.uca.iut.info.coo.td1.Message;

class ForumTest {

	@Test
	void testMainScenario() throws InterruptedException {
		//L'agence “oogle-stade” (Administrateur) crée un forum “OGCN”.
		Forum ogcn = new Forum("OGCN");
		
		//Initialisation du Forum avec les membres
		Member mario = new Member("Mario");
		Member walter = new Member("Walter");
		Member alban = new Member("Alban");
		ogcn.addMember(mario);
		ogcn.addMember(walter);
		ogcn.addMember(alban);
		List<Member> members = ogcn.getMembers();
		assertEquals(3, members.size());
		
		//Mario (Membre) poste un message WaitAndSee: “a quoi cela sert de courir?” sur le forum “OGCN”. 
		Message wait = new Message("a quoi cela sert de courir?", mario);
		ogcn.addMessage(wait);
		assertEquals(1, ogcn.getAllMessages().size());
	
		//Walter (Membre) demande s'il y a de nouvelles informations sur le forum et obtient le message WaitAndSee. 
		//Il pose la même question un peu plus tard, et le système lui répond qu'il n'y a pas de nouveaux messages. 
		//Walter demande à lire tous les messages. 
		//Walter poste un message Yes : “Tout à fait d'accord!”. 
		List<Message> messages = ogcn.getNewMessages(walter);
		assertTrue(messages.contains(wait));
		messages = ogcn.getNewMessages(walter);
		assertEquals(0,messages.size());
		assertEquals(1, ogcn.getAllMessages().size());
		Message yes = new Message( "Tout à fait d'accord!", walter);
		ogcn.addMessage(yes);
		

		//Alban (Membre) demande s'il y a de nouveaux messages et obtient les messages WaitAndSee and Yes.
		messages = ogcn.getNewMessages(alban);
		assertTrue(messages.contains(wait));
		assertTrue(messages.contains(yes));
		
		//Youcef s'inscrit sur le forum puis poste un message PFFF : “Vous rigolez?”. 
		Member youcef = new Member("Youcef");
		ogcn.addMember(youcef);
		Message pfff = new Message( "Vous rigolez?", youcef);
		ogcn.addMessage(pfff);
		messages = ogcn.getNewMessages(youcef);
		assertEquals(3, messages.size());
		messages = ogcn.getNewMessages(youcef);
		assertEquals(0, messages.size());
		
		//Walter demande à lire les nouveaux messages.
		messages = ogcn.getNewMessages(walter);
		assertEquals(2, messages.size());
		
		//Walter demande à effacer le message réalisé par Youcef, il n'a pas le droit, cela ne fait rien.
		messages = ogcn.getAllMessages();
		int numberOfMessages = messages.size();
		
		boolean removed = ogcn.remove(pfff, walter);
		assertFalse(removed);
		messages = ogcn.getAllMessages();
		assertEquals(numberOfMessages,messages.size());
		
		//Youcef efface son message
		removed = ogcn.remove(pfff, youcef);
		assertTrue(removed);
		messages = ogcn.getAllMessages();
		assertEquals(numberOfMessages-1,messages.size(),"le message a bien été effacé");
		
		assertFalse(pfff.isOutOfDate(2));
		Thread.sleep(2001);
		assertTrue(pfff.isOutOfDate(2));
		//Les messages postés il y a plus de 10mn (adapté la durée pour les tests) sont détruits par “oogle-stade”.
	}

}
