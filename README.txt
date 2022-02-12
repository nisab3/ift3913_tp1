Rapport TP1 


Nicolas Sabourin (1068459)
Dave Sanon-Abraham (20174672)

GIT:
https://github.com/nisab3/ift3913_tp1.git

UTILISATION:
Il faut ajouter le dossier du projet à analyser au même emplacement que tp1.jar.
Ce dossier sera pris en compte dans le nom de chaque paquet en étant le root.

Une classe seul doit être a la même place.  La sauvegarde se fera avec un chemin vide car elle n'a pas de paquet.

Nous commencons en appelant la commande "java -jar tp1.jar" dans une fenetre command prompt sous windows 
en étant dans le dossier ou ce trouve le .jar.

Le programme va demander le nom du dossier comtenant le projet à analyser, ou le nom de la classe (incluant l'extention).

Les documents .csv seront sauvegardés à coté de tp1.jar.

Le programme débute dans le main() de la classe CalculMetrique().
Il parcourt tous les dossiers fournis pour chercher les fichiers ayant extension .java fournis dans le .properties

.PROPERTIES:
Ce fichier contient :
* Les extensions des fichiers analysés.
* Les délimiteurs de commentaires. 
* Les predicats qui représentes les boucles et conditions pour le calcul du WMC.
* Les deux entêtes des fichiers de sauvegarde.
* Les noms des fichiers de sauvegarde.
* Les fichiers à ignorer

FICHIER
Nous considérons chaque fichier Java comme une classe et ne prenons pas en calcul les classes imbriquées, pour le moment.
Nous ignorons methode-info.java et package-info.java.

WMC:
Le calcul du WMC compte le nombre de méthode avec les {}.  Une interface nous retourne donc un WMC de 0 car les méthode
de celle-ci ne sont que des déclarations sans code intérieur.  Nous changeons donc la valeur de sont WMC à 1 pour nos calcul.
De cette façon la valeur montre bien qu'une interface est très simple à entretenir.
