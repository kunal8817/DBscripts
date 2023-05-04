job('Seed Job') {
  properties {
    parameters {
      stringParam('ENVIRONMENT', '', 'Environment (dev, prod)')
      stringParam('GIT_BRANCH', '', 'Git branch (main, feature)')
      credentialsParam('AWS_ACCESS_KEY_ID', '', 'AWS Access Key ID', 'aws-access-key-id')
      credentialsParam('AWS_SECRET_ACCESS_KEY', '', 'AWS Secret Access Key', 'aws-secret-access-key')
    }
  }
  steps {
    dsl {
      external('deploy_ec2.groovy')
    }
  }
}
