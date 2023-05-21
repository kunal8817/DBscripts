def EnvToDeploy = []
def appbranch
import groovy.json.JsonSlurper

pipeline {
    agent any
    stages {
        stage('Listing_version') {
          steps {
            echo 'Loading...'
            sh 'terraform --version' // Listing the terraform version
          }
        }
        stage('Create Workspace in the required environment') {
          steps {
            echo "Deploy for ${EnvToDeploy}"
            sh "mkdir ${envDir}"
            sh "terraform workspace new ${EnvToDeploy}"
          }
        }
    }
}
