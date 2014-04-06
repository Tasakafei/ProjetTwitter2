package dataMining;
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Phase1_2 {
    private String csvPath;
    public Phase1_2(String csvPath) {
        this.csvPath = csvPath;
    }

    public void generateTransFile(String transPath) throws IOException {
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
            // on récupère ainsi notre ligne découpé par les ;
            // étant donné que toutes nos données ont des "text" il n'y a pas besoin de les traiter
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
}
