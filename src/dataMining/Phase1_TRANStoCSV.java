package dataMining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Phase1_TRANStoCSV {
	public static String  getKeyByValue(HashMap<String, Integer> map,Integer value) {
	    for (Entry<String, Integer> entry : map.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	public static void generateCSVFileFromTrans(String transPath, String dicoPath, String csvPath) throws IOException {
		// notre CSV
    	File file = new File(csvPath + ".csv");
    	if(!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter bwcsv = new BufferedWriter( new FileWriter(file) );
        // notre trans
        File transFile = new File(transPath + ".trans");
        BufferedReader brTrans = new BufferedReader( new FileReader(transFile) );
		// notre dictionnaire
		File dicoFile = new File(dicoPath + ".txt");
		BufferedReader brDico = new BufferedReader( new FileReader(dicoFile) );
		
		String strCsv = new String();
        String line, token1;
		Integer token2;
        StringTokenizer stringTokenizer;
        // on va reremplir notre hashmap
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        while((line = brDico.readLine()) != null) {
        	stringTokenizer = new StringTokenizer(line, ";");
            token1 = stringTokenizer.nextToken();
            token2 = Integer.parseInt(stringTokenizer.nextToken());
            hashMap.put(token1, token2);
        } //collection remplie
        
        //comparons notre trans au dico pour transcrire en csv
        while((line = brTrans.readLine()) != null) {
            stringTokenizer = new StringTokenizer(line, " ");
            while(stringTokenizer.hasMoreTokens()){
                token2 = Integer.parseInt(stringTokenizer.nextToken());
                strCsv += getKeyByValue(hashMap,token2);
                if (stringTokenizer.hasMoreTokens())
                	strCsv += ";";
            }
           bwcsv.write(strCsv);
           bwcsv.newLine();
        }
        bwcsv.flush();
        bwcsv.close();
        brTrans.close();
        brDico.close();
		
	}
	public static void main(String[] args) throws IOException {
		if ( 3 != args.length) {
			System.out.println("Erreur : nombre d'arguments invalide\nArguments attendus : CheminInTrans CheminInDico CheminOutCSV");
			return;
		}
    	String csvPath = args[2];
    	String dicoPath = args[1];
    	String transPath = args[0];
    	
    	generateCSVFileFromTrans(transPath, dicoPath, csvPath);
    	System.out.println("\n---\nFin du traitement\n");
	}

}
