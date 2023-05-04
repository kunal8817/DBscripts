pipeline {
  agent any

  environment {
    TF_VAR_environment = params.environment
    TF_VAR_gitbranch = params.gitbranch

    // AWS credentials
    AWS_ACCESS_KEY_ID = credentials('aws-access-key-id')
    AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
  }

  stages {
    stage('Checkout') {
      steps {
        // Checkout the source code from the repository
        git branch: params.gitbranch, url: 'https://github.com/your/repo.git'
      }
    }

    stage('Terraform Init') {
      steps {
        // Initialize Terraform
        dir('terraform') {
          sh 'terraform init'
        }
      }
    }

    stage('Terraform Plan') {
      steps {
        // Generate and display an execution plan
        dir('Terraform') {
          sh 'terraform plan -var environment=${params.environment} -var gitbranch=${params.gitbranch}'
        }
      }
    }

    stage('Terraform Apply') {
  steps {
    // Deploy the infrastructure
    dir('Terraform') {
      withCredentials([string(credentialsId: 'aws-access-key-id', variable: 'AWS_ACCESS_KEY_ID'), string(credentialsId: 'aws-secret-access-key', variable: 'AWS_SECRET_ACCESS_KEY')]) {
        sh '''
          export TF_VAR_aws_access_key="${AWS_ACCESS_KEY_ID}"
          export TF_VAR_aws_secret_key="${AWS_SECRET_ACCESS_KEY}"
          terraform apply -auto-approve -var environment=${params.environment} -var gitbranch=${params.gitbranch}
        '''
      }
    }
  }
}
}
}
