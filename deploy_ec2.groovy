pipelineJob('Deploy_job') {
  definition {
    cps {
      script("""
        pipeline {
          agent any
    
          environment {
            TF_VAR_environment = $ENVIRONMENT
            TF_VAR_gitbranch = $GIT_BRANCH
    
            AWS_ACCESS_KEY_ID = credentials('aws-access-key-id')
            AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
          }
    
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
                  sh 'terraform plan -var environment=$ENVIRONMENT -var gitbranch=$GIT_BRANCH'
                }
              }
            }
    
            stage('Terraform Apply') {
              steps {
                dir('Terraform') {
                  sh 'terraform apply -auto-approve -var environment=$ENVIRONMENT -var gitbranch=$GIT_BRANCH'
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
