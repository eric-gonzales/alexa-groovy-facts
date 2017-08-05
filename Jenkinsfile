pipeline {
  agent any
  stages {
    stage('First') {
      steps {
        parallel(
          "First": {
            sh 'echo "Hey"'
            
          },
          "Testing": {
            echo 'Hello'
            
          }
        )
      }
    }
  }
}