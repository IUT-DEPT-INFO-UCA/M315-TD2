package fr.uca.iut.info.coo.td2.interactions;


import static java.lang.System.exit;
import static java.lang.Thread.sleep;

import java.io.IOException;
import java.util.Set;

import fr.uca.iut.info.coo.td1_2.core.Member;
import fr.uca.iut.info.coo.td2.core.Channel;
import fr.uca.iut.info.coo.td2.core.Forum;
import fr.uca.iut.info.coo.td2.core.Gestionnaire;
import fr.uca.iut.info.coo.td2.core.Message;
import fr.uca.iut.info.util.Memory;

public class Controleur {

	private Gestionnaire registre = new Gestionnaire();
	private UserConsole ui = new UserConsole();

	private Member currentMember;

	public Controleur() throws ClassNotFoundException, IOException{
		String nom = ui.getNomMembre();
		currentMember = new Member(nom);
		Object o  = Memory.read("sauvegarde.txt");
		if (o instanceof Gestionnaire){
			registre = (Gestionnaire) o;}
	}


	public void start() throws Exception {
		String commande = ui.lireCommande();
		switch (commande.charAt(0)) {
		case UserConsole.COM_CREER_FORUM:
			creerForum();
			start();
			break;
		case UserConsole.COM_CREER_CANAL :
			creerCanal();
			start();
			break;
		case UserConsole.COM_CREER_CANAL_DE_BREVES :
			creerCanalDeBreves();
			start();
			break;
		case UserConsole.COM_POSTER_MESSAGES:
			posterMessage();
			start();
			break;
		case UserConsole.COM_LIRE_MESSAGES:
			lireMessage();
			start();
			break;
		case UserConsole.COM_CHANGER_MEMBRE:
			currentMember = new Member(ui.getNomMembre());
			start();
			break;
		case UserConsole.COM_INSCRIRE:
			inscrireMembreSurForum();
			start();
			break;
		case UserConsole.COM_STOP:
			ui.afficher("Au revoir ");
			Memory.save(registre, "sauvegarde.txt");
			exit(0);
			break;

		default :
			ui.afficher("La commande spécifiée n'existe pas\n");
			sleep(1000);
			start();

		}}

	private void inscrireMembreSurForum() {
		currentMember = new Member(ui.getNomMembre());
		Forum currentForum = registre.getForum(ui.getNomDuForum(registre.getNomForums()));
		currentForum.addMember(currentMember);
	}


	//Méthode utilitaire
	private Forum getForum() {
		String nomDuForum = ui.getNomDuForum(registre.getNomForums());
		if(!registre.exist(nomDuForum)){
			ui.afficher("Ce Forum n'existe pas");
			return null;
		}
		return registre.getForum(nomDuForum);
	}


	private void creerForum() {
		//Récupérer la liste des noms des forums
		Set<String> ensembleDesNomsDeForumsExistants = registre.getNomForums();
		String nomDuForum = 
				ui.getNomDuForum(ensembleDesNomsDeForumsExistants);
		Forum forumNouveau = registre.creerForum(nomDuForum);
		if (forumNouveau != null)
			ui.afficher("Forum " + nomDuForum + ":"+ forumNouveau);
		else
			ui.afficher("Pbme de creation Forum " + nomDuForum);
	}

	private void creerCanal(){
		Forum forum = getForum();
		if (forum != null) {
			//Récupérer les noms des canaux pré-existants
			Set <String> nomDesCanaux = forum.getChannelNames();
			String nomDeCanal = ui.getNomCanal(nomDesCanaux);
			if (forum.addChannel(nomDeCanal))
				ui.afficher("Canal créé  : "+ nomDeCanal);
			else
				ui.afficher("Canal non créé  : "+ nomDeCanal);
		} 
	}

	private void creerCanalDeBreves() {
		Forum forum = getForum();
		if (forum != null) {
			//Récupérer les noms des canaux pré-existants
			Set <String> nomDesCanaux = forum.getChannelNames();
			String nomDeCanal = ui.getNomCanal(nomDesCanaux);
			int maxSize = ui.getSize();
			if (forum.addChannelOfBriefs(nomDeCanal,maxSize ))
				ui.afficher("Canal de brève créé  : "+ nomDeCanal);
			else
				ui.afficher("Canal de brève non créé  : "+ nomDeCanal);
		} 
	}

	private void posterMessage() {
		Forum forum = getForum();
		if (forum == null) {
			return;
		}
		String nomDeCanal = ui.getNomCanal(forum.getChannelNames());
		String msg =  ui.getValeur("saisir le message : ");

		Message message = new Message(msg,currentMember );
		
		if(nomDeCanal.equals("*")){
			for(Channel c : forum.getChannels()){
				c.addMessage(message);
			}
			return;
		}
		Channel canal = forum.getChannel(nomDeCanal);
		if (canal == null)
			ui.afficher("Ce Canal n'existe pas");
		else {
			if (canal.addMessage(message))
				ui.afficher("Message envoyé");
			else
				ui.afficher("Probleme a l'envoi");
			ui.afficher("Messages dans " + nomDeCanal);
			ui.afficherMessages(canal.getMessages());
		}

	}

	private void lireMessage() {
		Forum forum = getForum();
		if (forum == null) {
			return;
		}
		String nomDeCanal = ui.getNomCanal(forum.getChannelNames());
		Channel canal = forum.getChannel(nomDeCanal);
		if (canal == null)
			ui.afficher("Ce Canal "+ nomDeCanal + " n'existe pas");
		else {
			ui.afficherMessages(canal.getNewMessages(currentMember));
		}
	}

}


