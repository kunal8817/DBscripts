pipelineJob('TerraTest') {
  definition {
    cps {
      script("""
        pipeline {
          agent any
    
          environment {
            TF_VAR_environment = params.environment
            TF_VAR_gitbranch = params.gitbranch
    
            AWS_ACCESS_KEY_ID = credentials('aws-access-key-id')
            AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
          }
    
          stages {
            stage('Checkout') {
              steps {
                git branch: params.gitbranch, url: 'https://github.com/kunal8817/DBscripts.git'
              }
            }
    
            stage('Terraform Init') {
              steps {
                dir('terraform') {
                  sh 'terraform init'
                }
              }
            }
    
            stage('Terraform Plan') {
              steps {
                dir('terraform') {
                  sh 'terraform plan -var environment=${params.environment} -var gitbranch=${params.gitbranch}'
                }
              }
            }
    
            stage('Terraform Apply') {
              steps {
                dir('terraform') {
                  sh 'terraform apply -auto-approve -var environment=${params.environment} -var gitbranch=${params.gitbranch}'
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
