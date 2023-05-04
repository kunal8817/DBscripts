job('SEED_JOB_TERRAFORM_DEPLOY') {
  properties {
    parameters {
      choiceParam(name: 'GIT_BRANCH', '', choices: ['main', 'feature/terraform'], description: 'Select the branch')
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
