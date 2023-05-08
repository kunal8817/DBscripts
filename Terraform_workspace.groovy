def EnvToDeploy = params.EnvToDeploy
def envDir = "${EnvToDeploy}"
def appbranch
import groovy.json.JsonSlurper

if (params.invokedbyBB) {
    appbranch = "develop"
    appBranchName = "develop"
} else {
    switch(EnvToDeploy) {
        case "dev":
             switch(params.appBranchType) {
                case "main":
                     appbranch = "main"
                     break
                default:
                    appbranch = "feature/${params.appBranchName}"
                    break
             }
        case "prod":
             switch(appBranchType) {
                case "main":
                     appbranch = "main"
                     break
                default:
                    appbranch = "feature/${params.appBranchName}"
                    break
             }
    }
}


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
