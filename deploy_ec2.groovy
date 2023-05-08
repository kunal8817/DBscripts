pipelineJob('Deploy_job') {
    parameters {
      choiceParam('ENVIRONMENT', ['dev', 'prod'], '''Select the Environment.''')
      choiceParam('GIT_BRANCH', ['main', 'feature/terraform'], '''Select the branch''')
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
