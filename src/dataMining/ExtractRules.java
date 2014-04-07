package dataMining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ExtractRules {
	// déclarations
	HashMap<Integer, Double> hashMapFreqMotif = new HashMap<Integer,Double >();
 /*
  * DANS UN PREMIER TEMPS
  * On récupère les motifs fréquents de notre out
  */
    private void loadOutFile (File outFile) throws IOException{
    	BufferedReader brOut = new BufferedReader(new FileReader(outFile));
        int nbTweets = NbTweet(outFile);
        System.out.println("nombre de tweet dans out : " + nbTweets);
        String [] tabFreqMotifs;
        int cpt = 0;
        String line;
        while ((line = brOut.readLine()) != null){
            tabFreqMotifs = line.replace(")", "").split("(");
            hashMapFreqMotif.put(tabFreqMotifs[0], Integer.parseInt(tabFreqMotifs[1])/nbTweets);
            
        }
    }
    private double getLift(double confiance, double frequence) {
        return confiance / frequence;
    }
    private int NbTweet (File file) throws IOException{
    	BufferedReader reader = new BufferedReader(new FileReader(file));
    	int lines = 0;
    	while (reader.readLine() != null) lines++;
    	reader.close();
    	return lines;
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
