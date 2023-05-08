job('Seed Job') {
  steps {
    dsl {
      external('deploy_ec2.groovy')
    }
  }
}
