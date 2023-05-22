def gitUrl = "https://github.com/kunal8817/DBscripts.git"
def jobDslScript = "test_pipelines_rnd_seedjob.groovy"

pipelineJob('rnd-seedjob-test') {
    description('Pipeline job for new rnd work')
    parameters{
        choiceParam('gitUrl', [gitUrl], 'Jenkins Git URL')
        choiceParam('gitBranchType', ['feature', 'main'])
        choiceParam('gitBranchName', 'main', 'Branch Name')
    }

    definition {
        cps {
            script(readFileFromWorkspace(jobDslScript))
        }
    }
}