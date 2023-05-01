pipelineJob('MyParameterizedPipeline') {
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
            stage('Deploy') {
              steps {
                echo "Deploying to \${params.ENVIRONMENT} environment..."
                sh '''
                  if [ "\${params.ENVIRONMENT}" == "dev" ]; then
                    echo "Deploying to Dev environment..."
                    # Add your deployment commands here for the dev environment
                  elif [ "\${params.ENVIRONMENT}" == "prod" ]; then
                    echo "Deploying to Prod environment..."
                    # Add your deployment commands here for the prod environment
                  else
                    echo "Invalid environment specified!"
                  fi
                '''
              }
            }
          }
        }
      """)
    }
  }
}
