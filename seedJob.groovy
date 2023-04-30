pipelineJob('MyParameterizedPipeline') {
  definition {
    cps {
      script("""
        properties {
          parameters {
            stringParam('ENVIRONMENT', 'dev', 'Environment')
            booleanParam('CLEAN_BUILD', false, 'Perform clean build?')
          }
        }
        pipeline {
          agent any
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
                echo 'Deploying to ${ENVIRONMENT} environment...'
                // Example deployment steps based on the chosen environment
                sh '''
                  if [ "${ENVIRONMENT}" == "dev" ]; then
                    echo "Deploying to Dev environment..."
                    # Add your deployment commands here for the dev environment
                  elif [ "${ENVIRONMENT}" == "prod" ]; then
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
