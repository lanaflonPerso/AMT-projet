# Setup Serveur

Le script [server-setup.sh](server-setup.sh) **à executer en sudo**, permet de setup le serveur.

Le script installe Tomcat 9 et permet de créer un administrateur pout le manager web. Celui-ci est utilisé pour le deployment.

Il permet aussi de définir l'ip autorisée à manage le serveur (ici la DMZ).

Le script install aussi MariaDB et lance son setup.
