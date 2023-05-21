pipeline {
  agent any
  stages {
    stage('Workspace Creation') {
      steps {
        // Add your Terraform workspace creation steps here
        echo "Creating Terraform workspace..."
        echo "Environment: ${params.EnvToDeploy}"
        echo "Branch Type: ${params.appBranchType}"
        echo "Branch Name: ${params.appBranchName}"
        echo "Invoked by Bitbucket: ${params.invokedbyBB}"
      }
    }
  }
}
