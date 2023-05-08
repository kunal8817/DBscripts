import groovy.json.JsonSlurper

pipelineJob('Terraform_Create_Workspace') {
  definition {
    cps {
      script("""
        pipeline {
          agent any
          parameters {
            choiceParam('${EnvToDeploy}', ['dev', 'qaat'], 'Environment to deploy')
          }
          stages {
            stage('Listing_version') {
              steps {
                echo 'Loading...'
                sh 'terraform --version' // Listing the terraform version
              }
            }
            stage('Creating Workspace') {
              steps {
                echo 'Deploy for ${EnvToDeploy}'
                sh 'terraform workspace new ${EnvToDeploy}'
              }
            }
          }
        }
      """)
    }
  }
}