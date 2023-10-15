def jobDslScript = "test_pipelines_rnd_seedjob.groovy"

pipelineJob('rnd-seedjob-test') {
    description('Pipeline job for new rnd work')

    definition {
        cps {
            script(readFileFromWorkspace(jobDslScript))
        }
    }
}
