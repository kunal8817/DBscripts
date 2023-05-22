def jobDslScript = "test_pipelines_rnd_seedjob.groovy"

pipelineJob('rnd-seedjob-test') {
    description('Pipeline job for new rnd work')
    parameters{
        choice choices: ['main', 'feature'], name: 'gitBranchType'
        choice choices: ['main'], description: 'Branch Name', name: 'gitBranchName'
    }

    definition {
        cps {
            script(readFileFromWorkspace(jobDslScript))
        }
    }
}