pipeline {
    agent any
    tools{
        maven 'maven'
    }

    stages{
        stage('Clone Repo') {
                    git url: 'https://github.com/just4fun147/spring-technica-test.git',
                        credentialsId: 'github',
                        branch: 'main'
         }
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/just4fun147/spring-technica-test']]])
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t just4fun147/spring-technica-test .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'docker', variable: 'docker')]) {
                   sh 'docker login -u just4fun147 -p ${docker}'

}
                   sh 'docker push just4fun147/devops-integration'
                }
            }
        }
    }
}