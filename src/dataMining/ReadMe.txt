------------- PHASE 0 -------------
- Fonction clean : 
	Cr�ation du fonction clean nous servant a respecter le format demand�
	Elle remplace les espaces par les trois caract�res cons�cutifs ";"
	Elle sera utilis� pour traiter le corps du tweet et ainsi tronquer.

- twitter4J.properties
	Ce fichier contient nos diff�rentes clefs pour se connecter, il est � la racine.

- le main
	On teste si on a bien qu'un seul argument
	On le r�cup�re et le stocke dans une variable qui sera utilis� pour d�terminer le sujet des tweets
	On cr�e une instance de twitter.
	B�mol : twitter de prends que par paquet de 100
	Solution : une boucle qui prends les tweets tant qu'on n'en a pas 10000 on utilise donc addAll et query.setmaxId(result.getMaxId()-1)
	Pour �crire dans un fichier on passe par les filewriter (pour changer des buffers)
	On traite et on r�cup�re ce que l'on veut gr�ce au fonctions d�crites dans la doc


------------- PHASE 1 -------------