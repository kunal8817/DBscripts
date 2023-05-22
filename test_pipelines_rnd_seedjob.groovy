def dslScripts = "*.groovy*"

switch(gitBranchType) {
    case "main":
        gitBranch = "main"
        break
    case "develop":
        gitBranch = "develop"
        break
    default:
        gitBranch = "${gitBranchType}/${gitBranchName}"
        break
}

pipeline {
          agent any
          stages {
            stage('Checkout') {
                steps {
                    deleteDir()
                    checkout scmGit(branches: [[name: 'gitBranch']], extensions: [], userRemoteConfigs: [[url: 'gitUrl']])
              }
            }
            stage('Job DSL') {
              steps {
                echo "Refresshing all joob definition using pattern ${dslScripts} based on branch ${gitBranch} "
                jobDsl scriptText: 'dslScripts'
              }
            }
          }
        }