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
	private final static int MAX =101;
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
		try {
			long maxID = -1;
			int nbTweets = 0;
			File file = new File(Sujet + ".csv");
			System.out.println("fichier crée");
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			for (int queryNumber=0;queryNumber < MAX; queryNumber++) {
				
				Query query = new Query(Sujet);
				query.setCount(100);
				query.setLang("fr");
				if (maxID != -1) { query.setMaxId(maxID - 1); }
				QueryResult result = twitter.search(query);
				if (result.getTweets().size() == 0) { break;}
				
				for(Status tweet : result.getTweets()){
					if (maxID == -1 || tweet.getId() < maxID) { maxID = tweet.getId(); }
					String monTweet;
					++nbTweets;
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
				}
		

			}
				fw.flush();
				fw.close();
				// on affiche le nombre de tweet que l'on a récolté dans contenu 
				System.out.println("Count : " + nbTweets) ;
		} catch (TwitterException e) {

			e.printStackTrace();
		}
	}

}
