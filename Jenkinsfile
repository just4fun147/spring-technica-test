pipeline {
    agent any
    options{
        skipDefaultCheckout(true)
    }
    tools{
        maven 'maven'
    }
    stages{
        stage("Checkout from SCM"){
            steps {
                git branch: 'master', credentialsId: 'github', url: 'https://github.com/just4fun147/spring-technica-test'
            }
        }
        stage("Clone Project") {
             steps{
                 git url: 'https://github.com/just4fun147/spring-technica-test.git',
                                         credentialsId: 'github',
                                         branch: 'master'
                  }
             }
         stage("Build Maven") {
              steps {
                     sh "mvn clean package -DskipTests"
                }
         }
         stage("Build Image") {
             steps {
                 script{
                    sh 'docker build -t just4fun147/spring-technica-test .'
                    withCredentials([string(credentialsId: 'docker', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u just4fun147 -p ${dockerhubpwd}'}
                    sh 'docker push just4fun147/spring-technica-test'
                 }
             }
         }

    }
}