pipelineJob('MyParameterizedPipeline') {
  definition {
    cps {
      script("""
        pipeline {
          agent any
          options {
            parameters {
              string(name: 'ENVIRONMENT', defaultValue: 'dev', description: 'Environment')
              booleanParam(name: 'CLEAN_BUILD', defaultValue: false, description: 'Perform clean build?')
            }
          }
          stages {
            stage('Build') {
              steps {
                echo 'Building...'
                sh 'mvn clean install' // Example build step
              }
            }
            stage('Test') {
              steps {
                echo 'Testing...'
                sh 'mvn test' // Example test step
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
