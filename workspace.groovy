pipelineJob('Terraform_Create_Workspace') {
  definition {
    cps {
      script("""
        pipeline {
          agent any
          parameters {
            choice(name: 'ENVIRONMENT', choices: 'dev', description: 'Environment')
            booleanParam(name: 'CLEAN_BUILD', defaultValue: false, description: 'Perform clean build?')
          }
          stages {
            stage('Listing_version') {
              steps {
                echo 'Loading...'
                sh 'terraform --version' // Listing the terraform version
              }
            }
            stage('Test') {
              steps {
                echo 'Testing...'
                sh 'terraform --version' // Example test step
              }
            }
          }
        }
      """)
    }
  }
