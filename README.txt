Rapport TP1 


Nicolas Sabourin (1068459)
Dave Sanon-Abraham ( )




Il faut ajouter le projet à analyser dans le dossier “data”.
Les documents .csv seront sauvegardés dans le dossier “sauvegarde”.
Le programme débute dans le main() de la classe CalculMetrique().
Il parcourt tous les dossiers fournis pour chercher les fichiers ayant la même extension que celle fournis dans le fichier .properties.

.PROPERTIES:
Ce fichier contient :
* Les extensions des fichiers analysés.
* Les délimiteurs de commentaires. 
* Les predicats qui représentes les boucles et conditions pour le calcul du WMC.
* Les deux entêtes des fichiers de sauvegarde.
* Les noms des fichiers de sauvegarde.
* Les noms des dossiers “data” et “sauvegarde”.

FICHIER
Nous considérons chaque fichier Java comme une classe et ne prenons pas en calcul les classes imbriquées, pour le moment.

WMC:
Le calcul du WMC compte le nombre de méthode avec les {}.  Une interface nous retourne donc un WMC de 0 car les méthode
de celle-ci ne sont que des déclarations sans code intérieur.  Nous changeons donc la valeur de sont WMC à 1 pour nos calcul.
De cette façon la valeur montre bien qu'une interface est très simple à entretenir.
