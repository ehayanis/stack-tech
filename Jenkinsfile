pipeline {
    agent any

    tools {
        maven 'Maven 3.3.9'
        jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Maven Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install'
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage ('Docker Build') {
            steps {
                sh 'docker build -t ehayanis/stack-tech .'
            }
        }

        stage ('Run & Deploy Image') {
            steps {
                sh 'eval $(docker-machine env worker)'
                sh 'docker-compose up -d'
                sh 'eval $(docker-machine env -u)'
                sh 'echo "Deployment with Success!! You can login to you web app through this link"'
            }
        }
    }
}
