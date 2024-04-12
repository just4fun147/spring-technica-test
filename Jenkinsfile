pipeline {
    agent any
    options{
        skipDefaultCheckout(true)
    }
    tools{
        maven 'maven_3_5_0'
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
                    git url: 'https://github.com/just4fun147/spring-technica-test',
                        credentialsId: 'github',
                        branch: 'master'
                    withMaven {
                      sh "mvn clean verify"
                    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
              }
          }
    }
}