pipelineJob('Terraform_Create_Workspace') {
  description("Deploy Workspace in Terraform")
  logRotator(-1, 300)
  parameters {
    choiceParam('EnvToDeploy', ['dev', 'prod'], 'Create new workspace in Terraform')
    choiceParam('appBranchType', ['main', 'feature'], 'Select the branch')
    stringParam('appBranchName', '')
    booleanParam('invokedbyBB', true, 'uncheck to disable tests')
  }

  definition {
    cps {
      script(readFileFromWorkspace('Terraform_workspace.groovy'))
    }
  }
}
