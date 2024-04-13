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
                git branch: 'master', credentialsId: 'github', url: '${GIT_URL}'
            }
        }
        stage("Clone Project") {
             steps{
                 git url: '${GIT_URL}',
                                         credentialsId: 'github',
                                         branch: 'master'
                  }
             }
         stage("Build Maven") {
              steps {
                     sh "mvn clean package -DskipTests"
                }
         }
         stage("Sonarqube Analysis") {
             steps {
                 script {
                     withSonarQubeEnv(credentialsId: 'jenkins-sonarqube-token') {
                         sh "mvn sonar:sonar"
                     }
                 }
             }
         }
         stage("Quality Gate") {
             steps {
                 script {
                     waitForQualityGate abortPipeline: false, credentialsId: 'jenkins-sonarqube-token'
                 }
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

         stage("Trivy Scan") {
             steps {
                 script {
                    sh ('docker run -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy image just4fun147/spring-technica-test --no-progress --scanners vuln  --exit-code 0 --severity HIGH,CRITICAL --format table')
                 }
             }

         }

    }
}
