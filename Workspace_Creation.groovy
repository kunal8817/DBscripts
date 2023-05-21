pipelineJob('Terraform_Create_Workspace_Seed_Job') {
  definition {
    cps {
      script("""
        properties([
          parameters([
            choiceParam(name: 'EnvToDeploy', choices: ['development', 'staging', 'production'], description: 'Select the environment to deploy'),
            choiceParam(name: 'appBranchType', choices: ['feature', 'bugfix', 'hotfix'], description: 'Select the branch type'),
            stringParam(name: 'appBranchName', defaultValue: 'master', description: 'Enter the branch name'),
            booleanParam(name: 'invokedbyBB', defaultValue: false, description: 'Check if invoked by Bitbucket')
          ])
        ])

        pipeline {
          agent any
          stages {
            stage('Workspace Creation') {
              steps {
                // Add your Terraform workspace creation steps here
                echo "Creating Terraform workspace..."
                echo "Environment: \${params.EnvToDeploy}"
                echo "Branch Type: \${params.appBranchType}"
                echo "Branch Name: \${params.appBranchName}"
                echo "Invoked by Bitbucket: \${params.invokedbyBB}"
              }
            }
          }
        }
      """)
    }
  }
}
