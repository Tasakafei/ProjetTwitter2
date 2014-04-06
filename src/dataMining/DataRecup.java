/*package dataMining;
import twitter4j.*;
import twitter4j.auth.*;

import java.io.BufferedReader;
import java.util.List;
public class DataRecup {
	public static void main(String[] args) {
		
	   Twitter twitter = new Twitter();
	  
	   RequestToken requestToken = twitter.getOAuthRequestToken();
	   AccessToken accessToken = null;
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		
		// on vérifie nos arguments
        if (args.length < 1) {
            System.out.println("nombre d'arguments invalide");
            System.exit(-1);
        }
        
        // on crée l'instance de twitter
        Twitter twitter = new TwitterFactory().getInstance();
        
        // on va executer notre requete
        try {
            Query query = new Query(args[0]);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
            // tout c'est bien passé
            
        // gestion des exceptions twitter
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
}*/