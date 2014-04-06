package dataMining;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterRecup {
// test !!!!	
	// la fonction clean text permet de respecter le format demande par nedseb
	public static String cleanText(String text){
		// on remplace les espaces par ";" en sachant que l'on rajoutera un " au dernier
		text = text.replaceAll("[\"';,\\.\\n\\r]", "");
		text = text.trim();
		text = text.replace(" ", "\";\"");
		return text; 
	}
	
	public static void main(String[] args) throws IOException {
		// Recuperation du sujet demande par l'utilisateur en argument
		if ( 1 != args.length) {
			System.out.println("Erreur : nombre d'arguments invalide");
			return;
		}
		String Sujet = args[0];
		
		// Creation d'un objet Twitter
		Twitter twitter = TwitterFactory.getSingleton();
		Query query = new Query(Sujet);
		query.setCount(100);
	    QueryResult result = null;
	    ArrayList<Status> Contenu = new ArrayList<Status>();
		try {
			
			while (Contenu.size()< 10000 ) {
				result = twitter.search(query);
				Contenu.addAll(result.getTweets());
				query=result.nextQuery();
				
			}
			File file = new File("D:\\" + Sujet + ".csv");
			System.out.println("fichier crée");
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			
			for(Status tweet : Contenu) {
				String monTweet;
				// On traite les données et on respect le format demande par nedseb
				monTweet = "\"" + tweet.getId() + "\";\"" 
						+ tweet.getCreatedAt() + "\";\"" 
						+ tweet.getUser().getLocation() + "\";\"@"
						+ tweet.getUser().getScreenName()+ "\";\""
						+ tweet.getFavoriteCount() + "\";\""
						+ tweet.getRetweetCount() + "\";\""
						+ cleanText(tweet.getText()) + "\"\n";
				
				// On ecrit dans le fichier via le filewriter qui l'a ouvert	
				fw.write(monTweet);
				// juste une petite verif de début de projet
				System.out.println("modification du fichier : " + file.getPath());
			}
		
			fw.flush();
			fw.close();
			// on affiche le nombre de tweet que l'on a récolté dans contenu (normalement 10000 si aucune erreur)
			System.out.println("Count : " + Contenu.size()) ;
		} catch (TwitterException e) {

			e.printStackTrace();
		}
	}
 
}
