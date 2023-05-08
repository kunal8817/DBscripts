pipelineJob('Deploy_job') {
    parameters {
      stringParam('ENVIRONMENT', 'Environment (dev, prod)')
      stringParam('GIT_BRANCH', 'Git branch (main, feature/terraform)')
      credentialsParam('AWS_ACCESS_KEY_ID', 'AWS Access Key ID', 'aws-access-key-id')
      credentialsParam('AWS_SECRET_ACCESS_KEY', 'AWS Secret Access Key', 'aws-secret-access-key')
  }
  definition {
    cps {
      script("""
        pipeline {
          agent any
          stages {
            stage('Checkout') {
              steps {
                git branch: $GIT_BRANCH, url: 'https://github.com/kunal8817/DBscripts.git'
              }
            }
    
            stage('Terraform Init') {
              steps {
                dir('Terraform') {
                  sh 'terraform init'
                }
              }
            }
    
            stage('Terraform Plan') {
              steps {
                dir('Terraform') {
                  sh 'terraform plan -var gitbranch=$GIT_BRANCH'
                }
              }
            }
    
            stage('Terraform Apply') {
              steps {
                dir('Terraform') {
                  sh 'terraform apply -auto-approve -var gitbranch=$GIT_BRANCH'
                }
              }
            }
          }
        }
      """)
      sandbox()
    }
  }
}
