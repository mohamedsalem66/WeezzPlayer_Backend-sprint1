node {
  stage('Cloning the project from Git') {
    git branch: 'main', url: 'https://github.com/mohamedsalem66/WeezzPlayer_Backend-sprint1.git'
  }
  stage('SonarQube Analysis') {
    def scannerHome = tool 'SonarServer';
    withSonarQubeEnv() { 
      
      sh "${sonnarHome}/bin/sonar-scanner \
      -Dsonar.login=admin \
      -Dsonar.login=FRS**sonar \
      -Dsonar.projectKey=WeeztestPip \
      -Dsonar.exclusions=vendor/**,resources/**,**/*.java \
      -Dsonar.host.url=http://192.168.152.131:9000/"
    }
  }
}
