pipeline{
    agent any
    node {
  stage('SCM') {
    checkout scm
  }
   
       stage('Build'){
            steps{
                sh 'mvn clean package'
            }
         }
        stage('SonarQube analysis') {
//    def scannerHome = tool 'SonarScanner 4.0';
        steps{
        withSonarQubeEnv('sonarqube-8.9') { 
        def mvn = tool 'maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=Tester-WeezzPlayer_Backend"
      sh "mvn sonar:sonar"
    }
        }
        }
       
    }
}
