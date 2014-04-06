package dataMining;
import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Phase1_CSVtoTrans {
    public static void generateTransFile(String csvPath, String dicoPath, String transPath) throws IOException {
        // notre CSV
    	File file = new File(csvPath + ".csv");
        BufferedReader bufferedReader = new BufferedReader( new FileReader(file) );
        // notre trans
        File transFile = new File(transPath + ".trans");
        if(!transFile.exists()) {
            transFile.createNewFile();
        }
        BufferedWriter bwTrans = new BufferedWriter( new FileWriter(transFile) );
		// notre dictionnaire
		File dicoFile = new File(dicoPath + ".txt");
		System.out.println("fichier dico crée");
		BufferedWriter bwDico = new BufferedWriter( new FileWriter(dicoFile) );
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
        // on enregistre notre collection dans un fichier pour la réutiliser sagement
        for(Entry<String, Integer> entry : hashMap.entrySet()) {
            String cle = entry.getKey();
            Integer valeur = entry.getValue();
            bwDico.write(cle + ";" + valeur);
            bwDico.newLine();
        }
        bwDico.flush();
        bwDico.close();
        bwTrans.write(strTrans);
        bwTrans.flush();
        bwTrans.close();
        bufferedReader.close();
    }
    public static void main(String[] args) throws IOException {
    	if ( 3 != args.length) {
			System.out.println("Erreur : nombre d'arguments invalide\nArguments attendus : CheminInCSV CheminOutDico CheminOutTrans");
			return;
		}
    	String csvPath = args[0];
    	String dicoPath = args[1];
    	String transPath = args[2];
    	
    	generateTransFile(csvPath, dicoPath, transPath);
    	System.out.println("\n---\nFin du traitement\n");
    }
}
