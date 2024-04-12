pipeline {
    agent any
    tools{
        maven 'maven_3_5_0'
    }
    stages{
        stage('Clone Project') {
                git url: 'https://github.com/just4fun147/spring-technica-test.git',
                    credentialsId: 'github',
                    branch: 'master'
             }
    }
}