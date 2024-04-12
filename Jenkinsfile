pipeline {
    agent any
    options{
        skipDefaultCheckout(true)
    }
    tools{
        maven 'maven'
    }
    stages{
        stage('Clone Project') {
            steps{
                git url: 'https://github.com/just4fun147/spring-technica-test.git',
                    credentialsId: 'github',
                    branch: 'master'
            }
         }
         stage("Build Maven") {
              steps {
                     sh "mvn clean package"
                }
          }
    }
}