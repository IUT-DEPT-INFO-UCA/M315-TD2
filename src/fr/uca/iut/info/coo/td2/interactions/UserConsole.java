package fr.uca.iut.info.coo.td2.interactions;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fr.uca.iut.info.coo.td2.core.Message;

public class UserConsole {
	Scanner sc = new Scanner(System.in);


	public static final char COM_CREER_FORUM = 'f';
	public static final char COM_CREER_CANAL = 'c';
	public static final char COM_LIRE_MESSAGES = 'l';
	public static final char COM_POSTER_MESSAGES = 'p';
	public static final char COM_STOP = 's';
	public static final char COM_CHANGER_MEMBRE = 'm';
	public static final char COM_INSCRIRE = 'i';
	public static final char COM_CREER_CANAL_DE_BREVES = 'b';
	
	//On pourra modifier facilement pour par exemple utiliser des logger.
	private final void sop(String s) {
		System.out.println(s);
	}
	
	public  String lireCommande() {
		sop("------- COMMANDES ----------");
		sop("creer Forum : " + COM_CREER_FORUM ) ;
		sop("creer Canal : " + COM_CREER_CANAL);
		sop("creer Canal de brèves: " + COM_CREER_CANAL_DE_BREVES);
		sop("lire messages : " + COM_LIRE_MESSAGES);
		sop("poster un nouveau message : " + COM_POSTER_MESSAGES);
		sop("changer de membre : " + COM_CHANGER_MEMBRE ) ;
		sop("s'inscrire : " + COM_INSCRIRE ) ;
		sop("stop : " + COM_STOP);
		sop("Que voulez-vous faire ?");
		String str = sc.nextLine().toLowerCase();
		sop("Vous avez saisi la commande: " + str);
		return str;
	}

	public void afficher(String str) {
		sop(str);
	}


	public String getNomMembre() {
		sop("quel est votre nom ?");
		return  sc.nextLine();
	}
	
	public void afficherMessages(List<Message> arrayList) {
		int i = 1;
		sop("\t messages : ");
		if (arrayList == null) {
			sop("\t Pas de messages ");
		} 
		else
			for (Message m : arrayList) {
				sop("\t" + i + "-\t" + m);
				i++;
			}
	}

	public String getNomDuForum(Set<String> nomsForum) {
		sop("--- Forum disponibles --- ");
		afficheListe(nomsForum);
		sop("==> Nom du Forum ?");
		return  sc.nextLine();
	}

	public void afficheListe(Set<String> noms) {
		for (String nom : noms)
			sop("\t"+ nom);
	}


	public String getNomCanal(Set<String> nomsCanaux) {
		sop("--- Canals disponibles --- ");
		afficheListe(nomsCanaux);
		sop("==> Nom du Canal ?");
		return sc.nextLine();
	}


	public String getValeur(String message) {
		sop(message);
		return sc.nextLine();
	}

	public String write(){
		return sc.nextLine();
	}

	public int getSize() {
		sop("===> quelle taille ? ");
		int i = sc.nextInt();
		sc.nextLine();
		return i;
	}
}
