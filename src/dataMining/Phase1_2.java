package dataMining;
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Phase1_2 {
    public static void generateTransFile(String transPath, String csvPath) throws IOException {
        File file = new File(csvPath);
        BufferedReader bufferedReader = new BufferedReader( new FileReader(file) );
        File transFile = new File(transPath);
		System.out.println("fichier crée");
		transFile.createNewFile();
		FileWriter fw = new FileWriter(transFile);
		String strTrans = new String();
        String line, token;
        StringTokenizer stringTokenizer;

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        while((line = bufferedReader.readLine()) != null) {
            stringTokenizer = new StringTokenizer(line, ";");
            /*
             * Récup des parties et "remplissage" du "dictionnaire"
             */
                while(stringTokenizer.hasMoreTokens()){
                token = stringTokenizer.nextToken().toUpperCase();
                if(!hashMap.containsKey(token)) {
                    hashMap.put(token, hashMap.size() + 1);
                }
                strTrans += hashMap.get(token) + " ";
            }
            strTrans += "\n";
        }

        if(!transFile.exists()) {
            transFile.createNewFile();
        }

        fw.write(strTrans);
        fw.flush();
        fw.close();
        bufferedReader.close();
    }
    public static void main(String[] args) throws IOException {
    	if ( 2 != args.length) {
			System.out.println("Erreur : nombre d'arguments invalide\nArguments attendus : CheminInCSV CheminOutTrans");
			return;
		}
    	String csvPath = args[0];
    	String transPath = args[1];
    	generateTransFile(transPath,csvPath);
    	
    }
}
