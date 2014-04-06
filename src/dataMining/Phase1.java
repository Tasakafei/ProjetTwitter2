package dataMining;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Phase1 {
	public static String cleanText(String texte){
		// on remplace les espaces par ";" en sachant que l'on rajoutera un " au dernier
		texte = texte.replace(" ", "\";\"");
		return texte; 
	}
	public static void Translate(String source){
		try {
			String ligne;
			BufferedReader fichier = new BufferedReader(new FileReader(source));
			
			while((ligne = fichier.readLine()) != null){
				/*choix : passer par les hashset pour �viter les doublons et les null*/
				Set Dictionnaire = new HashSet(); // on cr�e notre Set
				String[] parts = ligne.split( ";" );
				System.out.println(parts[2]);
				/*Dictionnaire.add(new String("1")); // oups, je l'ai d�ja ajout�, la fonction g�re l'exception lev�e, et l'objet n'est pas ajout�
				Iterator i=Dictionnaire.iterator(); // on cr�e un Iterator pour parcourir notre HashSet
				while(i.hasNext()) // tant qu'on a un suivant
				{
					System.out.println(i.next()); // on affiche le suivant
				}
				/* Notez que l'it�ration se fait al�atoirement. */
				
			}
			fichier.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		/* test du nombre d'argument*/
		/*if ( 1 != args.length) {
			System.out.println("Erreur : nombre d'arguments invalide");
			return;
		}*/
		Translate("D://Jackass.csv");
	}
}
