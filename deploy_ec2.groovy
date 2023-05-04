pipelineJob('Deploy_job') {
  definition {
    cps {
      script("""
        pipeline {
          agent any
    
          environment {
            TF_VAR_environment = $environment
            TF_VAR_gitbranch = $gitbranch
    
            AWS_ACCESS_KEY_ID = credentials('aws-access-key-id')
            AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
          }
    
          stages {
            stage('Checkout') {
              steps {
                git branch: $gitbranch, url: 'https://github.com/kunal8817/DBscripts.git'
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
                  sh 'terraform plan -var environment=$environment -var gitbranch=$gitbranch'
                }
              }
            }
    
            stage('Terraform Apply') {
              steps {
                dir('Terraform') {
                  sh 'terraform apply -auto-approve -var environment=$environment -var gitbranch=$gitbranch'
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
