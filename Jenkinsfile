pipeline {
    def WORKSPACE = "/var/lib/jenkins/workspace/spring-technica-test"
    def dockerImageTag = "spring-technica-test${BUILD_NUMBER}"

    agent any
    tools{
        maven 'maven'
    }

    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/just4fun147/spring-technica-test']]])
            }
        }
        stage('Build docker image'){
            dockerImage = docker.build("spring-technica-test:${BUILD_NUMBER}")
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