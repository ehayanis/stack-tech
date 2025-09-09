Chronologie détaillée de l’incident (exemple avec heures fictives à adapter)

Jour J (date de la mise à jour)

09h00 – Début de la montée de version Xray 3.96.1 → 3.111.24.

09h30 – Redémarrage des services, mise à jour terminée. Aucun message d’erreur constaté.

10h30 – Premières alertes internes : lenteur inhabituelle sur les scans.

11h00 – Constat : certains scans affichent des temps estimés de plusieurs jours (jusqu’à 5 jours).

12h00 – Signalements d’incidents par les utilisateurs (blocage des workflows dépendant des scans).

13h30 – Vérification des métriques : CPU base de données à 100% en continu.

14h00 – Première action corrective : redémarrage du service Xray. Résultat : aucune amélioration.

15h30 – Application d’un patch correctif sur la version 3.111.24. Résultat : problème persistant.

16h30 – Multiples redémarrages supplémentaires pour tenter de stabiliser le service.

17h00 – Situation inchangée : lenteurs critiques, incidents utilisateurs en augmentation.

18h00 – Décision de rollback vers version 3.96.1 pour rétablir le service.

18h30 – Rollback effectué avec succès. Retour des performances normales.

19h00 – Validation par les équipes : service stabilisé, incidents utilisateurs résorbés.

2️⃣ Rapport d’incident – Post-Mortem
📌 Contexte

Projet : Mise à jour de JFrog Xray.

Version cible : 3.111.24 (depuis 3.96.1).

Objectif : bénéficier des correctifs et améliorations de la version récente.

📌 Impact

Durée de l’incident : ~9h (09h30 – 18h30).

Services impactés :

Scans Xray (analyse des artefacts).

Workflows dépendants des scans bloqués.

Utilisateurs impactés : l’ensemble des équipes utilisant Xray.

Gravité : Haute (dégradation majeure du service, interruption de production).

📌 Causes identifiées

Anomalie de performance introduite avec la version 3.111.24.

Saturation CPU de la base de données à 100% en continu, entraînant des temps de traitement excessifs.

Les actions correctives tentées (redémarrages, patch correctif) n’ont pas résolu le problème.

📌 Actions correctives

Redémarrage du service Xray (sans effet).

Application d’un patch 3.111.24 (sans effet).

Multiples tentatives de stabilisation dans l’après-midi.

Rollback vers la version 3.96.1 à 18h00 → retour à la normale.

📌 Recommandations / Next Steps

Planifier un test en environnement de pré-production avant toute montée de version Xray.

Analyser les logs Xray et BD pour identifier précisément la cause du CPU élevé.

Contacter le support JFrog pour vérifier s’il existe un correctif spécifique ou une version stable recommandée.

Mettre en place une procédure de rollback documentée pour accélérer la décision et l’exécution en cas d’incident similaire.

Surveiller en continu la charge CPU BD après toute évolution de version.



export SBT_OPTS="-Dsbt.override.build.repos=true -Dsbt.repository.config=/home/youruser/.sbt/repositories"
export JAVA_OPTS="-Dsbt.override.build.repos=true -Dsbt.repository.config=/home/youruser/.sbt/repositories"


php -r 'require "vendor/autoload.php";
if (class_exists(\Composer\InstalledVersions::class)) {
  echo \Composer\InstalledVersions::getPrettyVersion("symfony/security-http") . PHP_EOL;
} else {
  fwrite(STDERR, "Composer InstalledVersions not available\n");
}'
