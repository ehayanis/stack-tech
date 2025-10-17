FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/stack-tech-0.1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]



Sysdig ne se limite pas au CVSS “nu”.
Il calcule une note “Effective Vulnerability Severity” (EVS), en combinant :

les métadonnées du CVE (CVSS),

la présence du package vulnérable dans une image active,

le chemin d’exécution (est-ce dans un process exposé au réseau ? dans un conteneur root ?),

la capacité d’exploitation (exploit public, PoC, vulnérabilité reachable),

le contexte runtime (par exemple, sqlite3 utilisé par une appli web exposée, ou tournant avec privilèges élevés).

Exemple :

CVSS NVD : 6.9 → “Medium” car attaque complexe, utilisateur requis.

Mais si Sysdig détecte :

SQLite vulnérable chargé dans un conteneur exposé à Internet,

avec l’extension FTS5 activée,

dans un binaire s’exécutant avec des privilèges root,

et une version non patchée utilisée en production →
➜ la note contextuelle monte à 9.0 (High).
