------------- PHASE 0 -------------
- Fonction clean : 
	Création du fonction clean nous servant a respecter le format demandé
	Elle remplace les espaces par les trois caractères consécutifs ";"
	Elle sera utilisé pour traiter le corps du tweet et ainsi tronquer.

- twitter4J.properties
	Ce fichier contient nos différentes clefs pour se connecter, il est à la racine.

- le main
	On teste si on a bien qu'un seul argument
	On le récupère et le stocke dans une variable qui sera utilisé pour déterminer le sujet des tweets
	On crée une instance de twitter.
	Bémol : twitter de prends que par paquet de 100
	Solution : une boucle qui prends les tweets tant qu'on n'en a pas 10000 on utilise donc addAll et query.setmaxId(result.getMaxId()-1)
	Pour écrire dans un fichier on passe par les filewriter (pour changer des buffers)
	On traite et on récupère ce que l'on veut grâce au fonctions décrites dans la doc


------------- PHASE 1 -------------