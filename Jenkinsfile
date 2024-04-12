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
                git branch: 'master', credentialsId: 'github', url: 'https://github.com/just4fun147/spring-technica-tes'
            }
        }
        stage('Clone Project') {
                    git url: 'https://github.com/just4fun147/spring-technica-tes.git',
                        credentialsId: 'github',
                        branch: 'master'
                 }

         stage("Build Maven") {
              steps {
                     sh "mvn clean package"
                }
          }
    }
}