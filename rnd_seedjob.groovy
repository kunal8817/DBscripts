def gitUrl = "https://github.com/kunal8817/DBscripts.git"
def jobDslScript = "test_pipelines_rnd_seedjob.groovy"

pipelineJob('rnd-seedjob-test') {
    description('Pipeline job for new rnd work')
    parameters{
        choice choices: ['https://github.com/kunal8817/DBscripts.git'], description: 'Jenkins Git URL', name: 'gitUrl'
        choice choices: ['main', 'feature'], name: 'gitBranchType'
        choice choices: ['main'], description: 'Branch Name', name: 'gitBranchName'
    }

    definition {
        cps {
            script(readFileFromWorkspace(jobDslScript))
        }
    }
}