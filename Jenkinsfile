node {
    def WORKSPACE = "/var/lib/jenkins/workspace/spring-technica-test"
    def dockerImageTag = "spring-technica-test${BUILD_NUMBER}"

    try{
//          notifyBuild('STARTED')
         stage('Clone Repo') {
            // for display purposes
            // Get some code from a GitHub repository
            git url: 'https://github.com/just4fun147/spring-technica-test.git',
                credentialsId: 'deploy',
                branch: 'master'
         }
          stage('Build docker') {
                 dockerImage = docker.build("spring-technica-test:${BUILD_NUMBER}")
          }

          stage('Deploy docker'){
                  echo "Docker Image Tag Name: ${dockerImageTag}"
                  sh "docker stop spring-technica-test || true && docker rm spring-technica-test || true"
                  sh "docker run --name spring-technica-test -d -p 9001:8081 spring-technica-test:${BUILD_NUMBER}"
          }
    }catch(e){
//         currentBuild.result = "FAILED"
        throw e
    }finally{
//         notifyBuild(currentBuild.result)
    }
}

def notifyBuild(String buildStatus = 'STARTED'){

// build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'
  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def now = new Date()
  // message
  def subject = "${buildStatus}, Job: ${JOB_NAME} FRONTEND - Deployment Sequence: [${BUILD_NUMBER}] "
  def summary = "${subject} - Check On: (${BUILD_URL}) - Time: ${now}"
  def subject_email = "Spring boot Deployment"
  def details = """<p>${buildStatus} JOB </p>
    <p>Job: ${JOB_NAME} - Deployment Sequence: [${BUILD_NUMBER}] - Time: ${now}</p>
    <p>Check console output at "<a href="${BUILD_URL}">${JOB_NAME}</a>"</p>"""


  // Email notification
    emailext (
         to: "pauluswindito1@gmail.com",
         subject: subject_email,
         body: details,
         recipientProviders: [[$class: 'DevelopersRecipientProvider']]
       )
}
