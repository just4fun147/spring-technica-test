pipeline {
    agent any
    tools{
        maven 'maven_3_5_0'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/just4fun147/spring-technica-test']]])
                sh 'mvn clean install'
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